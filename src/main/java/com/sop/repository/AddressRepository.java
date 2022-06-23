package com.sop.repository;

import com.sop.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfejs AddressRepository to interfejs pozwalający na zarządzanie danych w bazie danych
 * poprzez obiekt {@link AddressEntity}
 * używa on wbudowanych funkcjonalności z {@link JpaRepository}
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}