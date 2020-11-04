package com.feedback.vlearning.user;

import java.util.List;

public interface UserService {

    UserDTO save(UserDTO user);

    User findByUserName(String username);

    UserDTO findUserById(Long id);

    UserDTO changePassword(ChangePasswordDTO changePasswordDTO);

    List<UserDTO> findAll();

}
