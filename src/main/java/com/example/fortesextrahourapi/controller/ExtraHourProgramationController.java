package com.example.fortesextrahourapi.controller;

import com.example.fortesextrahourapi.dto.BaseResponseDTO;
import com.example.fortesextrahourapi.dto.RequestExtraHoursProgramationDTO;
import com.example.fortesextrahourapi.dto.RequestStatusExtraHoursProgramationDTO;
import com.example.fortesextrahourapi.service.ExtraHoursProgramationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/extrahours")
public class ExtraHourProgramationController extends BaseController {
    @Autowired
    private ExtraHoursProgramationService extraHoursProgramationService;

    @PostMapping
    public ResponseEntity<BaseResponseDTO> registerExtraHourProgramation(@RequestBody RequestExtraHoursProgramationDTO dto) {
        return ok(extraHoursProgramationService.registerExtraHoursProgramation(dto));
    }

    @GetMapping
    public ResponseEntity<BaseResponseDTO> getExtraHourProgramation() {
        return ok(extraHoursProgramationService.getResponseExtraHoursProgramationDTO());
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponseDTO> getExtraHourProgramationList() {
        return ok(extraHoursProgramationService.responseListExtraHoursProgramationDTO());
    }

    @GetMapping("/id")
    public ResponseEntity<BaseResponseDTO> getExtraHourProgramationById(@RequestParam String id) {
        return ok(extraHoursProgramationService.responseViewExtraHourProgramationDTO(id));
    }

    @PatchMapping
    public ResponseEntity<BaseResponseDTO> changeStatusExtraHourProgramation(@RequestBody RequestStatusExtraHoursProgramationDTO dto) {
        return ok(extraHoursProgramationService.changeStatusExtraHoursProgramation(dto));
    }

    @PutMapping("/edit")
    public ResponseEntity<BaseResponseDTO> editExtraHourProgramation(@RequestBody RequestExtraHoursProgramationDTO dto) {
        return ok(extraHoursProgramationService.registerExtraHoursProgramation(dto));
    }
}
