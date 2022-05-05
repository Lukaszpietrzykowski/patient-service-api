package com.sop.entity;


import com.sop.creators.UserCreator;
import com.sop.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SOP_USER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public static UserEntity of(UserCreator user) {
        return UserEntity.builder()
                .email(user.getEmail())
                .login(user.getLogin())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public UserEntity updateWith(UserEntity user) {
        return UserEntity.builder()
                .id(this.id)
                .email(user.getEmail())
                .login(user.getLogin())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
