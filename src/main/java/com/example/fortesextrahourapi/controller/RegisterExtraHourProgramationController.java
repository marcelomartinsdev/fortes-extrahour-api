package com.example.fortesextrahourapi.controller;

import com.example.fortesextrahourapi.domain.Employee;
import com.example.fortesextrahourapi.domain.ExtraHoursProgramations;
import com.example.fortesextrahourapi.dto.BaseResponseDTO;
import com.example.fortesextrahourapi.dto.RequestExtraHoursProgramationDTO;
import com.example.fortesextrahourapi.service.ExtraHoursProgramationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/extrahours")
public class RegisterExtraHourProgramationController extends BaseController {
    @Autowired
    private ExtraHoursProgramationService extraHoursProgramationService;

    @PostMapping
    public ResponseEntity<BaseResponseDTO> registerExtraHourProgramation(@RequestBody RequestExtraHoursProgramationDTO dto) {
        return ok(extraHoursProgramationService.registerExtraHoursProgramation(dto));
    }
}
