package com.feedback.vlearning.branch;

import com.feedback.vlearning.user.*;
import com.feedback.vlearning.utility.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private BranchConverter branchConverter;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HashPassword hashPassword;

    @Autowired
    UserService userService;

    @Override
    public BranchDTO save(UserDTO userDto, BranchDTO branchDTO) {
        userDto.setUserType(UserType.ADMIN);
        userDto.setStatus(Status.ACTIVE);
        User user = userConverter.toEntity(userService.save(userDto));
        Branch branch = branchConverter.toEntity(branchDTO);
        branch.setUser(user);
        branch = branchRepository.save(branch);
        return branchConverter.toDto(branch);
    }

    @Override
    public BranchDTO findByBranchNameAndStatus(String name, Status status) {
        return branchConverter.toDto(branchRepository.findByBranchNameAndStatus(name, status));
    }

    @Override
    public List<BranchDTO> findAll() {
        return branchConverter.toDto(branchRepository.findByStatus(Status.ACTIVE));
    }

    @Override
    public BranchDTO findByUserAndStatus(User user) {
        return branchConverter.toDto(branchRepository.findByUserAndStatus(user, Status.ACTIVE));
    }

    @Override
    public BranchDTO edit(UserDTO userDTO, BranchDTO branchDTO) {
//        UserDTO userDTO1=userService.save(userDTO);
        Branch branch=branchConverter.toEntity(branchDTO);
        branch.setAddress(branchDTO.getAddress());
        branch.setBranchCode(branchDTO.getBranchCode());
        branch.setBranchName(branchDTO.getBranchName());
//        Branch branch=branchConverter.convertToEntity(branchDTO);
        User user=(branch.getUser());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(hashPassword.hashPassword(userDTO.getPassword()));
        userRepository.save(user);
        return branchConverter.toDto(branchRepository.save(branch));
    }

    @Override
    public BranchDTO findBranchById(Long id) {
        return branchConverter.toDto(branchRepository.findBranchById(id));
    }

}
