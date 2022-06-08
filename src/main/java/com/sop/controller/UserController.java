package com.sop.controller;

import com.sop.creators.UserCreator;
import com.sop.dto.UserDto;
import com.sop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable long id) {
        return userService.getUser(id);
    }
    @GetMapping("/details")
    public UserDto getUserByUsername(@RequestParam String login) {
        return userService.getUserByUsername(login);
    }

    @PostMapping("/add")
    public UserDto createUser(@RequestBody UserCreator user) {
        return userService.createUser(user);
    }

    @PutMapping("/update/{id}")
    public UserDto updateUser(@PathVariable long id, @RequestBody UserCreator user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
