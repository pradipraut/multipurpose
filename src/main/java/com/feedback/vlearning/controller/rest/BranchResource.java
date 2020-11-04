package com.feedback.vlearning.controller.rest;

import com.feedback.vlearning.branch.BranchDTO;
import com.feedback.vlearning.branch.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class BranchResource {

    @Autowired
    ResponseDetail responseDetail;

    @Autowired
    private BranchService branchService;

    @GetMapping("/branch")
    public ResponseEntity<ResponseDTO> getCategory() {
        ResponseDTO responseDTO;
        List<BranchDTO> branchDTOList = branchService.findAll();
        if (branchDTOList != null) {
            responseDTO = responseDetail.getResponseDTO(ResponseStatus.SUCCESS, "Successfully retrieved Branch list", branchDTOList);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
        } else {
            responseDTO = responseDetail.getResponseDTO(ResponseStatus.BAD_REQUEST, "Unable to retrieve Branch list", null);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }
}
