package com.example.fortesextrahourapi.service;

import com.example.fortesextrahourapi.domain.ExtraHoursProgramations;
import com.example.fortesextrahourapi.dto.RequestExtraHoursProgramationDTO;
import com.example.fortesextrahourapi.dto.ResponseExtraHoursProgramationDTO;
import com.example.fortesextrahourapi.enums.RoleEnum;
import com.example.fortesextrahourapi.enums.UnitEnum;
import com.example.fortesextrahourapi.repositories.ExtraHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ExtraHoursProgramationService {
    @Autowired
    private ExtraHoursRepository extraHoursRepository;

    @Autowired
    private EmployeeService employeeService;

    public String registerExtraHoursProgramation(RequestExtraHoursProgramationDTO dto) {
        ExtraHoursProgramations responseExtraHours = extraHoursRepository.save(buildExtraHoursProgramations(dto));
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
}
