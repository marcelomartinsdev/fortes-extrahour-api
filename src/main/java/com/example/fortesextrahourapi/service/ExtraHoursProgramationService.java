package com.example.fortesextrahourapi.service;

import com.example.fortesextrahourapi.domain.ExtraHoursProgramations;
import com.example.fortesextrahourapi.dto.RequestExtraHoursProgramationDTO;
import com.example.fortesextrahourapi.dto.RequestStatusExtraHoursProgramationDTO;
import com.example.fortesextrahourapi.dto.ResponseExtraHoursProgramationDTO;
import com.example.fortesextrahourapi.enums.ExtraHoursProgramationStatusEnum;
import com.example.fortesextrahourapi.enums.RoleEnum;
import com.example.fortesextrahourapi.enums.UnitEnum;
import com.example.fortesextrahourapi.exceptions.FortesException;
import com.example.fortesextrahourapi.repositories.ExtraHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExtraHoursProgramationService {
    @Autowired
    private ExtraHoursRepository extraHoursRepository;

    @Autowired
    private EmployeeService employeeService;

    public String registerExtraHoursProgramation(RequestExtraHoursProgramationDTO dto) {
        extraHoursRepository.save(buildExtraHoursProgramations(dto));
        return "Sucesso!";
    }

    private ExtraHoursProgramations buildExtraHoursProgramations(RequestExtraHoursProgramationDTO dto) {
        ExtraHoursProgramations extraHoursProgramations = new ExtraHoursProgramations();
        extraHoursProgramations.setProgramationDate(dto.getProgramationDate());
        extraHoursProgramations.setFuncionarios(dto.getFuncionarios());
        extraHoursProgramations.setGestor(dto.getGestor());
        extraHoursProgramations.setTecnico(dto.getTecnico());
        extraHoursProgramations.setReason(dto.getReason());
        extraHoursProgramations.setStartTime(dto.getStartTime());
        extraHoursProgramations.setEndTime(dto.getEndTime());
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
        if(optionalExtraHoursProgramations.isPresent()){
            optionalExtraHoursProgramations.get().setStatus(dto.getStatus());
            extraHoursRepository.save(optionalExtraHoursProgramations.get());
            return "Sucesso!";
        }
        throw new FortesException("Programacao nao encontrada!", HttpStatus.NOT_FOUND);
    }
}
