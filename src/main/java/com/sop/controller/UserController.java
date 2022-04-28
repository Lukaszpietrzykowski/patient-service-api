package com.sop.controller;

import com.sop.creators.AddressCreator;
import com.sop.creators.UserCreator;
import com.sop.dto.AddressDto;
import com.sop.dto.UserDto;
import com.sop.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @GetMapping("/all")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PostMapping("/add")
    public UserDto createUser(@RequestBody UserCreator user) {
        return userService.createUser(user);
    }

    @PutMapping("/update/{id}")
    public UserDto updateUser(@PathVariable long id, @RequestBody UserCreator user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteAddress(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
