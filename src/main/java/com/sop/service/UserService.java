package com.sop.service;

import com.sop.dto.UserDto;
import com.sop.entity.PatientEntity;
import com.sop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<UserDto> getUsers() {
        return repository.findAll()
                .stream()
                .map(UserDto::of)
                .toList();
    }

}
