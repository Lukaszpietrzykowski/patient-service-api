package com.sop.entity;


import com.sop.creators.UserCreator;
import com.sop.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "SOP_USER")
@AllArgsConstructor
@NoArgsConstructor
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
                .password(Objects.isNull(user.getPassword()) ? null : new BCryptPasswordEncoder().encode(user.getPassword()))
                .role(user.getRole())
                .build();
    }

    public UserEntity updateWith(UserEntity user, String oldPassword) {
        return UserEntity.builder()
                .id(this.id)
                .email(user.getEmail())
                .login(user.getLogin())
                .password(Objects.isNull(user.getPassword()) ? oldPassword : user.getPassword())
                .role(user.getRole())
                .build();
    }
}
