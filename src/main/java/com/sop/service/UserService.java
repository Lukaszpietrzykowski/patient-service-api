package com.sop.service;

import com.sop.entity.UserEntity;

import java.util.List;

public interface UserService {
    public abstract List<UserEntity> getUsers();
}
