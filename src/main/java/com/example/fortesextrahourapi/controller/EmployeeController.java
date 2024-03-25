package com.example.fortesextrahourapi.controller;


import com.example.fortesextrahourapi.domain.Employee;
import com.example.fortesextrahourapi.dto.BaseResponseDTO;
import com.example.fortesextrahourapi.repositories.EmployeeRepository;
import com.example.fortesextrahourapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController extends BaseController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<BaseResponseDTO> insertEmployee(@RequestBody Employee employee) {
        return ok(employeeService.createEmployee(employee));
    }
}
