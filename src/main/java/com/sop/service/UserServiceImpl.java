package com.sop.service;

import com.sop.entity.UserEntity;
import com.sop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public UserRepository repository;

    @Override
    public List<UserEntity> getUsers() {
        return repository.findAll();
    }
}
