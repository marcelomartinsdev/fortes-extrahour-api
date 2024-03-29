package com.example.fortesextrahourapi.controller;

import com.example.fortesextrahourapi.dto.BaseResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected static final String ERROR = "error";
    protected static final String SUCCESS = "success";

    protected ResponseEntity<BaseResponseDTO> ok(Object data) {
        return effective(HttpStatus.OK.value(), data);
    }

    protected ResponseEntity<BaseResponseDTO> accepted(Object data) {
        return effective(HttpStatus.ACCEPTED.value(), data);
    }

    protected ResponseEntity<BaseResponseDTO> err(Object data) {
        return err(HttpStatus.INTERNAL_SERVER_ERROR.value(), data);
    }

    protected ResponseEntity<BaseResponseDTO> effective(Integer codeStatus, Object data) {
        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode(codeStatus);
        response.setData(data);
        response.setMessage(SUCCESS);
        return ResponseEntity.ok(response);
    }
    
    private ResponseEntity<BaseResponseDTO> err(Integer codeStatus, Object data) {
        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode(codeStatus);
        response.setData(data);
        response.setMessage(ERROR);
        return ResponseEntity.status(codeStatus).body(response);
    }
}