package com.feedback.vlearning.branch;

import com.feedback.vlearning.user.Status;
import com.feedback.vlearning.user.User;
import com.feedback.vlearning.user.UserDTO;

import java.util.List;

public interface BranchService {

    BranchDTO save(UserDTO userDto, BranchDTO branchDTO);

    BranchDTO findByBranchNameAndStatus(String name , Status status);

    List<BranchDTO> findAll();

    BranchDTO findByUserAndStatus(User user);

    BranchDTO edit(UserDTO userDTO,BranchDTO branchDTO);

    BranchDTO findBranchById(Long id);


}
