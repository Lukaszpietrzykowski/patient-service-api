package com.sop.dto;

import com.sop.entity.UserEntity;
import com.sop.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserDto {

    private long id;
    private String email;
    private String login;
    private RoleEnum role;

    public static UserDto of(UserEntity user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .login(user.getLogin())
                .role(user.getRole())
                .build();
    }
}
