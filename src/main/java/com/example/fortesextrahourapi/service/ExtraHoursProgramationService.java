package com.example.fortesextrahourapi.service;

import com.example.fortesextrahourapi.domain.ExtraHoursProgramations;
import com.example.fortesextrahourapi.dto.*;
import com.example.fortesextrahourapi.enums.RoleEnum;
import com.example.fortesextrahourapi.enums.UnitEnum;
import com.example.fortesextrahourapi.exceptions.FortesException;
import com.example.fortesextrahourapi.repositories.ExtraHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExtraHoursProgramationService {
    @Autowired
    private ExtraHoursRepository extraHoursRepository;

    @Autowired
    private EmployeeService employeeService;

    private static final String SUCESSO = "Sucesso!";

    public String registerOrEditExtraHoursProgramation(RequestExtraHoursProgramationDTO dto, boolean editExtraHours) {
        ExtraHoursProgramations extraHoursProgramations;
        extraHoursProgramations = buildExtraHoursProgramations(dto);
        if (editExtraHours) {
            validationExtraHourProgramation(dto.getId());
            extraHoursProgramations.setId(dto.getId());
        }
        extraHoursRepository.save(extraHoursProgramations);
        return SUCESSO;
    }

    private ExtraHoursProgramations buildExtraHoursProgramations(RequestExtraHoursProgramationDTO dto) {
        ExtraHoursProgramations extraHoursProgramations = new ExtraHoursProgramations(dto.getStartTime(), dto.getEndTime(), dto.getProgramationDate());
        extraHoursProgramations.setFuncionarios(dto.getFuncionarios());
        extraHoursProgramations.setGestor(dto.getGestor());
        extraHoursProgramations.setTecnico(dto.getTecnico());
        extraHoursProgramations.setReason(dto.getReason());

        return extraHoursProgramations;
    }

    public ResponseExtraHoursProgramationDTO getResponseExtraHoursProgramationDTO() {
        ResponseExtraHoursProgramationDTO response = new ResponseExtraHoursProgramationDTO();

        response.setFuncionarios(employeeService.showEmployees(RoleEnum.FUNCIONARIO));
        response.setTecnico(employeeService.showEmployees(RoleEnum.TECNICO));
        response.setGestor(employeeService.showEmployees(RoleEnum.GESTOR));
        response.setUnits(UnitEnum.getUnitsDescription());
        return response;
    }

    public String changeStatusExtraHoursProgramation(RequestStatusExtraHoursProgramationDTO dto) {
        Optional<ExtraHoursProgramations> optionalExtraHoursProgramations = extraHoursRepository.findById(dto.getProgramationId());
        if (optionalExtraHoursProgramations.isPresent()) {
            optionalExtraHoursProgramations.get().setStatus(dto.getStatus());
            extraHoursRepository.save(optionalExtraHoursProgramations.get());
            return SUCESSO;
        }
        throw new FortesException("Programação não encontrada!", HttpStatus.NOT_FOUND);
    }

    public List<ResponseListExtraHoursProgramationDTO> responseListExtraHoursProgramationDTO() {
        List<ExtraHoursProgramations> extraHoursProgramationsList = extraHoursRepository.findAll();
        List<ResponseListExtraHoursProgramationDTO> responseList = new ArrayList<>();

        if (extraHoursProgramationsList.isEmpty()) {
            throw new FortesException("Não existe programações cadastradas!", HttpStatus.NOT_FOUND);
        }

        extraHoursProgramationsList.forEach(e -> {
            ResponseListExtraHoursProgramationDTO response = new ResponseListExtraHoursProgramationDTO();
            response.setId(e.getId());
            response.setProgramationDate(e.getProgramationDate());
            response.setStartTime(e.getStartTime());
            response.setEndTime(e.getEndTime());
            response.setTecnico(e.getTecnico());
            response.setGestor(e.getGestor());
            response.setStatus(e.getStatus());
            responseList.add(response);
        });
        return responseList;
    }

    public ResponseViewExtraHourProgramationDTO responseViewExtraHourProgramationDTO(String id) {
        ResponseViewExtraHourProgramationDTO response = new ResponseViewExtraHourProgramationDTO();
        validationExtraHourProgramation(id);
        ExtraHoursProgramations extraHoursProgramations = extraHoursRepository.findById(id).get();
        response.setId(extraHoursProgramations.getId());
        response.setProgramationDate(extraHoursProgramations.getProgramationDate());
        response.setStartTime(extraHoursProgramations.getStartTime());
        response.setEndTime(extraHoursProgramations.getEndTime());
        response.setTecnico(extraHoursProgramations.getTecnico());
        response.setGestor(extraHoursProgramations.getGestor());
        response.setStatus(extraHoursProgramations.getStatus());
        response.setFuncionarios(extraHoursProgramations.getFuncionarios());
        response.setReason(extraHoursProgramations.getReason());
        return response;
    }

    private void validationExtraHourProgramation(String id) {
        extraHoursRepository.findById(id)
                .orElseThrow(() -> new FortesException("Entidade não encontrada para o ID: " + id, HttpStatus.NOT_FOUND));
    }
}
