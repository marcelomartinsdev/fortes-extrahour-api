package com.example.fortesextrahourapi.service;

import com.example.fortesextrahourapi.domain.Employee;
import com.example.fortesextrahourapi.dto.RequestCreateEmployeeDTO;
import com.example.fortesextrahourapi.exceptions.FortesException;
import com.example.fortesextrahourapi.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Integer TIME = 1;

    private RequestCreateEmployeeDTO dto;

    @BeforeEach
    public void setUp() {
        startRequestCreateEmployeeDTO();
    }

    @Test
    public void createEmployeeTestSuccess() {
        when(employeeRepository.existsEmployeeByUsername(dto.getUsername())).thenReturn(false);
        when(employeeRepository.existsEmployeeByEmail(dto.getEmail())).thenReturn(false);
        when(bCryptPasswordEncoder.encode(any())).thenReturn("sadawfawofaw");
        when(employeeRepository.save(any())).thenReturn(new Employee());

        String response = employeeService.createEmployee(dto);

        verify(employeeRepository, times(TIME)).existsEmployeeByUsername(dto.getUsername());
        verify(employeeRepository, times(TIME)).existsEmployeeByEmail(dto.getEmail());
        verify(employeeRepository, times(TIME)).save(any());
        assertEquals(String.class, response.getClass()); // testa se é String
        assertEquals("Sucesso!", response); // valida a mensagem
    }

    @Test
    public void createEmployeeTestErrorExistsByUsername() {
        when(employeeRepository.existsEmployeeByUsername(dto.getUsername())).thenReturn(true);

        FortesException response = assertThrows(FortesException.class, () -> employeeService.createEmployee(dto));

        verify(employeeRepository, times(TIME)).existsEmployeeByUsername(dto.getUsername());

        assertEquals(FortesException.class, response.getClass());
        assertEquals("Nome de usuário já cadastrado!", response.getErrors().get("mensagem"));
        assertEquals(HttpStatus.CONFLICT.value(), Integer.parseInt(response.getErrors().get("status")));
    }

    @Test
    public void createEmployeeTestErrorExistsByEmail() {
        when(employeeRepository.existsEmployeeByUsername(dto.getUsername())).thenReturn(false);
        when(employeeRepository.existsEmployeeByEmail(dto.getEmail())).thenReturn(true);

        FortesException response = assertThrows(FortesException.class, () -> employeeService.createEmployee(dto));

        verify(employeeRepository, times(TIME)).existsEmployeeByUsername(dto.getUsername());
        verify(employeeRepository, times(TIME)).existsEmployeeByEmail(dto.getEmail());

        assertEquals(FortesException.class, response.getClass());
        assertEquals("Endereço de email já cadastrado!", response.getErrors().get("mensagem"));
        assertEquals(HttpStatus.CONFLICT.value(), Integer.parseInt(response.getErrors().get("status")));
    }

    private void startRequestCreateEmployeeDTO() {
        dto = new RequestCreateEmployeeDTO();
        dto.setUsername("gerente");
        dto.setEmail("teste");
        dto.setPassword("senha");
    }

}