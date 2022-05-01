package com.sop.creators;

import com.sop.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class UserCreator {
    private String email;
    private String login;
    private String password;
    private RoleEnum role;
}
