package com.feedback.vlearning.branch;

import com.feedback.vlearning.user.Status;
import com.feedback.vlearning.user.UserRepository;
import com.feedback.vlearning.utility.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchConverter implements EntityMapper<BranchDTO, Branch> {

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Branch toEntity(BranchDTO dto) {
        if (dto == null) {
            return null;
        }
        Branch entity;
        if (dto.getId() != null) {
            entity = branchRepository.findBranchById(dto.getId());
        } else {
            entity = new Branch();
            toEntity(dto, entity);
            entity.setStatus(Status.ACTIVE);
        }
        return entity;
    }

    public Branch convertToEntity(BranchDTO dto) {
        if (dto == null) {
            return null;
        }
        Branch entity = new Branch();
            toEntity(dto, entity);
            entity.setStatus(Status.ACTIVE);
        return entity;
    }

    @Override
    public Branch toEntity(BranchDTO dto, Branch entity) {
        if (entity == null || dto == null) {
            return null;
        }
        entity.setBranchName(dto.getBranchName());
        entity.setBranchCode(dto.getBranchCode());
        entity.setAddress(dto.getAddress());
        entity.setUser(userRepository.findUserById(dto.getUserId()));
        return entity;
    }

    @Override
    public BranchDTO toDto(Branch entity) {
        if (entity == null) {
            return null;
        }
        BranchDTO dto = new BranchDTO();
        dto.setId(entity.getId());
        dto.setBranchName(entity.getBranchName());
        dto.setBranchCode(entity.getBranchCode());
        dto.setAddress(entity.getAddress());
        dto.setStatus(entity.getStatus());
        dto.setUserId(entity.getUser().getId());
        dto.setUserLogin(entity.getUser().getUsername());
        return dto;
    }

    @Override
    public List<Branch> toEntity(List<BranchDTO> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return null;
        }
        return dtoList.parallelStream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<BranchDTO> toDto(List<Branch> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.parallelStream().map(this::toDto).collect(Collectors.toList());
    }
}
