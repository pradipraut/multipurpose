package com.feedback.vlearning.branch;

import com.feedback.vlearning.user.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BranchValidation {

    @Autowired
    BranchService branchService;

    public BranchError validateBranch(BranchDTO branchDTO, String type) {
        BranchError branchError = new BranchError();
        Boolean valid = true;
        if (type.equalsIgnoreCase("add")) {
            if (branchService.findAll().size() >= 1) {
                valid = false;
                branchError.setSize("Branch limit Exceeds!!!");
            }
        }
        if (branchDTO.getBranchName() == null ||
                branchDTO.getBranchName().equals("") ||
                branchDTO.getBranchName().replaceAll(" ", "").equals("") ||
                branchDTO.getBranchName().trim().equals("")) {
            valid = false;
            branchError.setBranchName("Invalid branch name!");
        } else if (branchDTO.getBranchName().replaceAll(" ", "").length() <= 4) {
            valid = false;
            branchError.setBranchName("Branch name too short!");
        } else if (branchDTO.getBranchName().replaceAll(" ", "").length() >= 20) {
            valid = false;
            branchError.setBranchName("Branch name too long!");
        }

        if (type.equalsIgnoreCase("add")) {
            if (branchService.findByBranchNameAndStatus(branchDTO.getBranchName(), Status.ACTIVE) != null) {
                valid = false;
                branchError.setBranchName("Branch already exists!");
            }
        }

        if (type.equalsIgnoreCase("edit")) {
            if (!branchService.findBranchById(branchDTO.getId()).getBranchName()
                    .equalsIgnoreCase(branchDTO.getBranchName())) {
                if (branchService.findByBranchNameAndStatus(branchDTO.getBranchName(), Status.ACTIVE) != null) {
                    valid = false;
                    branchError.setBranchName("Branch already exists!");
                }
            }
        }

        if (branchDTO.getBranchCode() == null ||
                branchDTO.getBranchCode().equals("") ||
                branchDTO.getBranchCode().replaceAll(" ", "").equals("") ||
                branchDTO.getBranchCode().trim().equals("")) {
            valid = false;
            branchError.setBranchCode("Invalid branch code!");
        } else if (branchDTO.getBranchCode().replaceAll(" ", "").length() <= 2) {
            valid = false;
            branchError.setBranchCode("Branch code too short!");
        } else if (branchDTO.getBranchCode().replaceAll(" ", "").length() >= 7) {
            valid = false;
            branchError.setBranchCode("Branch code too long!");
        }

        if (branchDTO.getAddress() == null ||
                branchDTO.getAddress().equals("") ||
                branchDTO.getAddress().replaceAll(" ", "").equals("") ||
                branchDTO.getAddress().trim().equals("")) {
            valid = false;
            branchError.setAddress("Invalid address code!");
        } else if (branchDTO.getAddress().replaceAll(" ", "").length() <= 2) {
            valid = false;
            branchError.setAddress("Address too short!");
        } else if (branchDTO.getAddress().replaceAll(" ", "").length() >= 15) {
            valid = false;
            branchError.setAddress("Address too long!");
        }

        branchError.setValid(valid);
        return branchError;
    }

}
