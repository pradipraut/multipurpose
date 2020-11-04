package com.feedback.vlearning.utility;

import com.feedback.vlearning.configuration.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @Autowired
    SecurityUtility securityUtility;

//    @ModelAttribute("currentUser")
//    public User getCurrentUser(Authentication authentication) {
//        return (authentication == null) ? null : securityUtility.getSecurity();
//    }
//
//
//    @ModelAttribute("currentRole")
//    public String getCurrentRole(Authentication authentication) {
//        if (authentication == null)
//            return null;
//        else {
//            String string = null;
//            if (securityUtility.getSecurity().getUserType().name().equalsIgnoreCase("SYSTEM_ADMIN"))
//                string = "systemadmin";
//            else if (securityUtility.getSecurity().getUserType().name().equalsIgnoreCase("ADMIN"))
//                string = "admin";
//            return string;
//        }
//    }
//
//    @ModelAttribute("currentBranch")
//    public String getCurrentBranch(Authentication authentication) {
//        if (authentication == null)
//            return null;
//        else {
//            if (securityUtility.getSecurity().getUserType().name().equalsIgnoreCase("ADMIN"))
//                return securityUtility.getCurrentBranch().getBranchName();
//            else
//                return null;
//        }
//    }

}
