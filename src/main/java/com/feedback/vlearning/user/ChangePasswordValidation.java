package com.feedback.vlearning.user;

import com.feedback.vlearning.configuration.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordValidation {

    @Autowired
    SecurityUtility securityUtility;

    public ChangePasswordError validateChangePassword(ChangePasswordDTO changePasswordDTO) {
        ChangePasswordError changePasswordError = new ChangePasswordError();
        Boolean valid = true;

        if (changePasswordDTO.getOldPassword()== null ||
                changePasswordDTO.getOldPassword().equals("") ||
                changePasswordDTO.getOldPassword().trim().equals("")) {
            valid = false;
            changePasswordError.setOldPassword("Invalid password!");
        }else if (!BCrypt.checkpw(changePasswordDTO.getOldPassword(),securityUtility.getSecurity().getPassword())){
            valid = false;
            changePasswordError.setOldPassword("Old password did not match!");
        }

        if (changePasswordDTO.getNewPassword() == null ||
                changePasswordDTO.getNewPassword().equals("") ||
                changePasswordDTO.getNewPassword().trim().equals("")) {
            valid=false;
            changePasswordError.setNewPassword("Invalid password");
        }else if(!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())){
            valid=false;
            changePasswordError.setConfirmPassword("Confirm Password not matching");
        }else if (changePasswordDTO.getNewPassword().replaceAll(" ","").length()<8){
            valid=false;
            changePasswordError.setNewPassword("Password too small");
        }

        if (changePasswordDTO.getConfirmPassword() == null ||
                changePasswordDTO.getConfirmPassword().equals("") ||
                changePasswordDTO.getConfirmPassword().trim().equals("")) {
            valid=false;
            changePasswordError.setConfirmPassword("Invalid password");
        }else if (changePasswordDTO.getConfirmPassword().replaceAll(" ","").length()<8){
            valid=false;
            changePasswordError.setConfirmPassword("Password too small");
        }

        changePasswordError.setValid(valid);
        return changePasswordError;
    }
}
