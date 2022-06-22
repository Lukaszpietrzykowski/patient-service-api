package com.sop.repository;

import com.sop.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfejs PatientRepository to interfejs pozwalający na zarządzanie danych w bazie danych
 * poprzez obiekt {@link PatientEntity}
 * używa on wbudowanych funkcjonalności z JpaRepository
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    /**
     * Zwraca obiekt typu {@link PatientEntity} pobierany z bazy danych,
     * przeszukując baze po peselu
     *
     * @param pesel zmienna przechowująca pesel
     * @return zwraca obiekt typu {@link PatientEntity}
     */
    PatientEntity getPatientEntityByPesel(String pesel);
}

