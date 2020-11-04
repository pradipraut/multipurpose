package com.feedback.vlearning.controller.rest;

import org.springframework.stereotype.Component;

@Component
public class ResponseDetail {

    public ResponseDTO getResponseDTO(ResponseStatus status, String message, Object detail) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(status.getKey());
        responseDTO.setCode(status.getValue());
        responseDTO.setMessage(message);
        responseDTO.setDetails(detail);
        return responseDTO;
    }
}
