package com.example.fortesextrahourapi.service;

import com.example.fortesextrahourapi.domain.Employee;
import com.example.fortesextrahourapi.dto.RequestCreateEmployeeDTO;
import com.example.fortesextrahourapi.enums.RoleEnum;
import com.example.fortesextrahourapi.dto.ShowEmployeeNameDTO;
import com.example.fortesextrahourapi.exceptions.FortesException;
import com.example.fortesextrahourapi.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public String createEmployee(RequestCreateEmployeeDTO dto) {
        Employee employee = toEmployee(dto);
        employee.setRegistrationDate(dateFormatter());
        if (employeeRepository.existsEmployeeByUsername(employee.getUsername())) {
            throw new FortesException("Nome de usuário já cadastrado!", HttpStatus.CONFLICT);
        }

        if (employeeRepository.existsEmployeeByEmail(employee.getEmail())) {
            throw new FortesException("Endereço de email já cadastrado!", HttpStatus.CONFLICT);
        }

        employee.setPassword(cryptoPassword(employee.getPassword()));
        employeeRepository.save(employee);
        return "Sucesso!";
    }

    private Employee toEmployee(RequestCreateEmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setCellphone(dto.getCellphone());
        employee.setActive(true);
        employee.setEmail(dto.getEmail());
        employee.setName(dto.getName());
        employee.setUsername(dto.getUsername());
        employee.setPassword(dto.getPassword());
        employee.setRole(dto.getRole());
        return employee;
    }

    private String dateFormatter() {
        LocalDate date = LocalDate.now();
        return date.format(dateTimeFormatter);
    }

    private String cryptoPassword(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }

    public List<ShowEmployeeNameDTO> showEmployees(RoleEnum employeeRole) {
        List<Employee> roles = employeeRepository.findAllByRole(employeeRole);
        return getShowEmployeeNameDTOS(roles);
    }

    public List<ShowEmployeeNameDTO> showAllEmployees() {
        List<Employee> roles = employeeRepository.findAll();
        return getShowEmployeeNameDTOS(roles);
    }

    private List<ShowEmployeeNameDTO> getShowEmployeeNameDTOS(List<Employee> roles) {
        List<ShowEmployeeNameDTO> responseShowEmployeeDTO = new ArrayList<>();
        if (roles.isEmpty()) {
            throw new FortesException("Não há nenhum funcionário cadastrado", HttpStatus.CONFLICT);
        }
        roles.forEach(e -> {
            ShowEmployeeNameDTO dto = new ShowEmployeeNameDTO();
            dto.setName(e.getName());
            dto.setUsername(e.getUsername());
            dto.setRole(e.getRole());
            responseShowEmployeeDTO.add(dto);
        });
        return responseShowEmployeeDTO;
    }
}
