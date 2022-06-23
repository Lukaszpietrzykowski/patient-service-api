package com.sop.repository;

import com.sop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfejs UserRepository to interfejs pozwalający na zarządzanie danych w bazie danych
 * poprzez obiekt {@link UserEntity}
 * używa on wbudowanych funkcjonalności z {@link JpaRepository}
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Zwraca obiekt typu {@link UserEntity}pobierany z bazy danych,
     * przeszukując baze po username
     *
     * @param username zmienna przechowująca username czyli nazwę użytkownika
     * @return zwraca obiekt typu {@link UserEntity}
     */
    UserEntity findByLogin(String username);
}