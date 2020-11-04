package com.feedback.vlearning.configuration;

import com.feedback.vlearning.branch.Branch;
import com.feedback.vlearning.branch.BranchRepository;
import com.feedback.vlearning.user.Status;
import com.feedback.vlearning.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityUtility {

    @Autowired
    BranchRepository branchRepository;


//    public User getCurrentUser() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) auth.getPrincipal();
//        return user;
//    }

    public User getSecurity() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getUser();
        }
        return null;
    }

    public Branch getCurrentBranch() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof CustomUserDetails) {
            User user=((CustomUserDetails) principal).getUser();
            Branch branch = branchRepository.findByUserAndStatus(user, Status.ACTIVE);
            return branch;
        }
        return null;
    }

}
