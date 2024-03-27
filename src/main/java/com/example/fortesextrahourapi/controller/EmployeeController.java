package com.example.fortesextrahourapi.controller;


import com.example.fortesextrahourapi.domain.Employee;
import com.example.fortesextrahourapi.enums.RoleEnum;
import com.example.fortesextrahourapi.dto.BaseResponseDTO;
import com.example.fortesextrahourapi.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController extends BaseController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<BaseResponseDTO> insertEmployee(@RequestBody @Valid Employee employee) {
        return ok(employeeService.createEmployee(employee));
    }

    @GetMapping
    public ResponseEntity<BaseResponseDTO> showEmployees(@RequestParam String role) {
        return ok(employeeService.showEmployees(RoleEnum.getRoleEnum(role)));
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponseDTO> showAllEmployees() {
        return ok(employeeService.showAllEmployees());
    }
}
