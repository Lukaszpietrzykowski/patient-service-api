package com.sop.repository;

import com.sop.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfejs DepartmentRepository to interfejs pozwalający na zarządzanie danych w bazie danych
 * poprzez obiekt {@link DepartmentEntity}
 * używa on wbudowanych funkcjonalności z {@link JpaRepository}
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}