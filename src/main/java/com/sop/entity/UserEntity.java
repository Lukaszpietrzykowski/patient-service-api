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

/**
 * Klasa reprezentująca użytkowników (obiekty/rekordy) znajdujące się w bazie danych.
 */
@Entity
@Table(name = "SOP_USER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserEntity {

    /**
     * Zmienna typu long przechowująca id użytkownika.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Zmienna typu String przechowująca adres mialowy użytkownika.
     */
    private String email;
    /**
     * Zmienna typu String przechowująca login użytkownika.
     */
    private String login;
    /**
     * Zmienna typu String przechowująca hasło użytkownika.
     */
    private String password;

    /**
     * Zmienna typu {@link RoleEnum} przechowująca role użytkownika.
     */
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    /**
     * Konwertuje obiekt klasy {@link UserCreator} na obiekt klasy {@link UserEntity}.
     *
     * @param user zmienna przechowująca użytkownika typu {@link UserCreator} który chcemy przekonwertować.
     * @return zwraca przekonwertowanego użytkownika typu {@link UserEntity}.
     */
    public static UserEntity of(UserCreator user) {
        return UserEntity.builder()
                .email(user.getEmail())
                .login(user.getLogin())
                .password(Objects.isNull(user.getPassword()) ? null : new BCryptPasswordEncoder().encode(user.getPassword()))
                .role(user.getRole())
                .build();
    }

    /**
     * Edytuje pacjenta.
     *
     * @param user        zmienna przechowujący użytkownika typu {@link PatientEntity}, przekazany do edycji.
     * @param oldPassword stare hasło użytkownika.
     * @return zwraca zedytowanego użytkownika typu {@link UserEntity}.
     */
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