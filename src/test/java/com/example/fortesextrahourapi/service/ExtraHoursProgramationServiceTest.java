package com.example.fortesextrahourapi.service;

import com.example.fortesextrahourapi.domain.ExtraHoursProgramations;
import com.example.fortesextrahourapi.dto.RequestExtraHoursProgramationDTO;
import com.example.fortesextrahourapi.dto.RequestStatusExtraHoursProgramationDTO;
import com.example.fortesextrahourapi.dto.ResponseExtraHoursProgramationDTO;
import com.example.fortesextrahourapi.dto.ShowEmployeeNameDTO;
import com.example.fortesextrahourapi.enums.ExtraHoursProgramationStatusEnum;
import com.example.fortesextrahourapi.exceptions.FortesException;
import com.example.fortesextrahourapi.repositories.ExtraHoursRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ExtraHoursProgramationServiceTest {

    @InjectMocks
    private ExtraHoursProgramationService extraHoursProgramationService;

    @Mock
    private ExtraHoursRepository extraHoursRepository;

    @Mock
    private EmployeeService employeeService;

    private RequestExtraHoursProgramationDTO requestExtraHoursProgramationDTO;

    private ExtraHoursProgramations extraHoursProgramations;

    private ResponseExtraHoursProgramationDTO responseExtraHoursProgramationDTO;

    @BeforeEach
    public void setUp() {
        startRequestExtraHoursProgramationDTO();
        startExtraHoursProgramations();
        startResponseExtraHoursProgramationDTO();
    }

    @Test
    void registerExtraHourProgramationTestSuccess() {
        when(extraHoursRepository.save(any())).thenReturn(new ExtraHoursProgramations());

        String response = extraHoursProgramationService.registerOrEditExtraHoursProgramation(requestExtraHoursProgramationDTO, Boolean.FALSE);

        assertEquals("Sucesso!", response);
        assertNotNull(response);
        assertEquals(String.class, response.getClass());
    }

    @Test
    void registerExtraHourProgramationTestEditSuccess() {
        requestExtraHoursProgramationDTO.setId("1213131");
        when(extraHoursRepository.findById(any())).thenReturn(Optional.ofNullable(extraHoursProgramations));
        when(extraHoursRepository.save(any())).thenReturn(new ExtraHoursProgramations());

        String response = extraHoursProgramationService.registerOrEditExtraHoursProgramation(requestExtraHoursProgramationDTO, Boolean.TRUE);

        assertEquals("Sucesso!", response);
        assertNotNull(response);
        assertEquals(String.class, response.getClass());
    }

    @Test
    void errorWhenNotFindProgramationId() {
        requestExtraHoursProgramationDTO.setId("1213131");
        when(extraHoursRepository.findById(any())).thenReturn(Optional.empty());

        FortesException response = assertThrows(FortesException.class,
                () -> extraHoursProgramationService.registerOrEditExtraHoursProgramation(requestExtraHoursProgramationDTO, Boolean.TRUE));

        assertEquals(FortesException.class, response.getClass());
        assertEquals("Entidade não encontrada para o ID: " + requestExtraHoursProgramationDTO.getId(), response.getErrors().get("mensagem"));
        assertEquals(HttpStatus.NOT_FOUND.value(), Integer.parseInt(response.getErrors().get("status")));
    }

    @Test
    void getResponseExtraHoursProgramationDTOTest() {
        ResponseExtraHoursProgramationDTO response = extraHoursProgramationService.getResponseExtraHoursProgramationDTO();

        assertNotNull(response);
        assertEquals(ResponseExtraHoursProgramationDTO.class, response.getClass());
    }

    @Test
    void changeStatusExtraHoursProgramationTestSuccess() {
        RequestStatusExtraHoursProgramationDTO requestStatusExtraHoursProgramationDTO = new RequestStatusExtraHoursProgramationDTO();
        requestStatusExtraHoursProgramationDTO.setStatus(ExtraHoursProgramationStatusEnum.APPROVED);

        when(extraHoursRepository.findById(any())).thenReturn(Optional.ofNullable(extraHoursProgramations));
        when(extraHoursRepository.save(any())).thenReturn(new ExtraHoursProgramations());

        String response = extraHoursProgramationService.changeStatusExtraHoursProgramation(requestStatusExtraHoursProgramationDTO);

        assertEquals("Sucesso!", response);
        assertNotNull(response);
        assertEquals(String.class, response.getClass());
    }

    @Test
    void changeStatusExtraHoursProgramationTestError() {
        when(extraHoursRepository.findById(any())).thenReturn(Optional.empty());

        FortesException response = assertThrows(FortesException.class,
                () -> extraHoursProgramationService.changeStatusExtraHoursProgramation(new RequestStatusExtraHoursProgramationDTO()));

        assertEquals(FortesException.class, response.getClass());
        assertEquals("Programação não encontrada!", response.getErrors().get("mensagem"));
        assertEquals(HttpStatus.NOT_FOUND.value(), Integer.parseInt(response.getErrors().get("status")));
    }

    void startRequestExtraHoursProgramationDTO() {
        requestExtraHoursProgramationDTO = new RequestExtraHoursProgramationDTO();

        requestExtraHoursProgramationDTO.setProgramationDate(LocalDate.of(2024, 4, 8));
        requestExtraHoursProgramationDTO.setReason("Motivo de exemplo");
        requestExtraHoursProgramationDTO.setTecnico("Nome do técnico");
        requestExtraHoursProgramationDTO.setGestor("Nome do gestor");
        requestExtraHoursProgramationDTO.setStartTime(LocalTime.of(9, 0));
        requestExtraHoursProgramationDTO.setEndTime(LocalTime.of(17, 0));
    }

    void startExtraHoursProgramations() {
        extraHoursProgramations = new ExtraHoursProgramations();

        extraHoursProgramations.setProgramationDate("10/05/24");
        extraHoursProgramations.setReason("Motivo de exemplo");
        extraHoursProgramations.setTecnico("Nome do técnico");
        extraHoursProgramations.setGestor("Nome do gestor");
        extraHoursProgramations.setStartTime("5:00");
        extraHoursProgramations.setEndTime("10:00");
    }

    void startResponseExtraHoursProgramationDTO() {
        responseExtraHoursProgramationDTO = new ResponseExtraHoursProgramationDTO();
        List<ShowEmployeeNameDTO> showEmployeeNameDTOList = new ArrayList<>();
        responseExtraHoursProgramationDTO.setGestor(showEmployeeNameDTOList);
        responseExtraHoursProgramationDTO.setFuncionarios(showEmployeeNameDTOList);
        responseExtraHoursProgramationDTO.setTecnico(showEmployeeNameDTOList);
        responseExtraHoursProgramationDTO.setUnits(new ArrayList<>());
    }

}