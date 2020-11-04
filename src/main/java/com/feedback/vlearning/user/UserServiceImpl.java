package com.feedback.vlearning.user;

import com.feedback.vlearning.configuration.SecurityUtility;
import com.feedback.vlearning.utility.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HashPassword hashPassword;

    @Autowired
    UserConverter userConverter;

    @Autowired
    SecurityUtility securityUtility;

    @Override
    public UserDTO save(UserDTO userDto) {
        String newPassword = hashPassword.hashPassword(userDto.getPassword());
        userDto.setPassword(newPassword);
        User user = userConverter.convertToEntity(userDto);
        user = userRepository.save(user);
        return userConverter.toDto(user);
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsernameAndStatus(username,Status.ACTIVE);
    }

    @Override
    public UserDTO findUserById(Long id) {
        return userConverter.toDto(userRepository.findUserById(id));
    }

    @Override
    public UserDTO changePassword(ChangePasswordDTO changePasswordDTO) {
        User user=securityUtility.getSecurity();
        user.setPassword(hashPassword.hashPassword(changePasswordDTO.getNewPassword()));
        return userConverter.toDto(userRepository.save(user));
    }

    @Override
    public List<UserDTO> findAll() {
        return userConverter.toDto(userRepository.findByStatus(Status.ACTIVE));
    }

}
