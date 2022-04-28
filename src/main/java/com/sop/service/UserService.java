package com.sop.service;

import com.sop.creators.AddressCreator;
import com.sop.creators.UserCreator;
import com.sop.dto.AddressDto;
import com.sop.dto.UserDto;
import com.sop.entity.AddressEntity;
import com.sop.entity.UserEntity;
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

    public UserDto createUser(UserCreator userCreator) {
        return UserDto.of(repository.save(UserEntity.of(userCreator)));
    }
    public UserDto getUser(Long id) {
        return UserDto.of(repository.findById(id)
                .orElseThrow());
    }

    public UserDto updateUser(Long id, UserCreator userCreator) {
        return UserDto.of(repository.findById(id)
                .map(oldUser -> {
                    UserEntity updatedUser = oldUser.updateWith(UserEntity.of(userCreator));
                    return repository.save(updatedUser);
                })
                .orElseThrow());
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

}
