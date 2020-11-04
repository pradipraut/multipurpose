package com.feedback.vlearning.user;

import com.feedback.vlearning.utility.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConverter implements EntityMapper<UserDTO, User> {

    @Autowired
    UserRepository userRepository;

    @Override
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User entity;
        if (dto.getId() != null) {
            entity = userRepository.findUserById(dto.getId());
        } else {
            entity = new User();
            toEntity(dto, entity);
            entity.setStatus(Status.ACTIVE);
        }
        return entity;
    }

    public User convertToEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User entity = new User();
        toEntity(dto, entity);
        entity.setStatus(Status.ACTIVE);
        return entity;
    }

    @Override
    public User toEntity(UserDTO dto, User entity) {
        if (entity == null || dto == null) {
            return null;
        }
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setUserType(dto.getUserType());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    public UserDTO toDto(User entity) {
        if (entity == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        dto.setUserType(entity.getUserType());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public List<User> toEntity(List<UserDTO> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return null;
        }
        return dtoList.parallelStream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> toDto(List<User> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.parallelStream().map(this::toDto).collect(Collectors.toList());
    }
}
