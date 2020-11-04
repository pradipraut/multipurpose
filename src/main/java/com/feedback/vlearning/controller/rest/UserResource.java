package com.feedback.vlearning.controller.rest;

import com.feedback.vlearning.user.UserDTO;
import com.feedback.vlearning.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserResource {

    @Autowired
    UserService userService;

    @Autowired
    ResponseDetail responseDetail;

    @GetMapping("/user")
    public ResponseEntity<ResponseDTO> getCategory() {
        ResponseDTO responseDTO;
        List<UserDTO> userDTOList = userService.findAll();
        if (userDTOList != null) {
            responseDTO = responseDetail.getResponseDTO(ResponseStatus.SUCCESS, "Successfully retrieved User list", userDTOList);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
        } else {
            responseDTO = responseDetail.getResponseDTO(ResponseStatus.BAD_REQUEST, "Unable to retrieve User list", null);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }
}
