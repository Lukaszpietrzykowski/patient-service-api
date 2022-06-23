package com.sop.entity;

import com.sop.creators.DepartmentCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca oddziały (obiekty/rekordy) znajdujące się w bazie danych.
 */
@Entity
@Table(name = "DEPARTMENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DepartmentEntity {

    /**
     * Zmienna typu long przechowująca id oddziału.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Zmienna typu String przechowująca nazwę oddziału.
     */
    private String name;

    /**
     * Zmienna przechowująca listę pacjentów typu {@link PatientEntity}.
     */
    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
    private List<PatientEntity> patients = new ArrayList<>();

    /**
     * Zmienna typu {@link HospitalEntity} przechowująca szpital.
     */
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private HospitalEntity hospital;
    /**
     * Zmienna typu long przechowująca liczbę wszystkich łóżek w oddziale.
     */
    private long availableBeds;

    /**
     * Konwertuje obiekt klasy {@link DepartmentCreator} na obiekt klasy {@link DepartmentEntity}.
     *
     * @param department zmienna przechowujący oddział typu {@link DepartmentCreator} który chcemy przekonwertować.
     * @return zwraca przekonwertowany oddział typu {@link DepartmentEntity}.
     */
    public static DepartmentEntity of(DepartmentCreator department) {
        return DepartmentEntity.builder()
                .name(department.getName())
                .patients(new ArrayList<>())
                .availableBeds(department.getAvailableBeds())
                .build();
    }

    /**
     * Edytuje oddział.
     *
     * @param department zmienna przechowujący oddział typu {@link DepartmentEntity}, przekazany do edycji.
     * @return zwraca zedytowany oddział typu {@link DepartmentEntity}.
     */
    public DepartmentEntity updateWith(DepartmentEntity department) {
        return DepartmentEntity.builder()
                .id(this.id)
                .name(department.getName())
                .patients(this.patients)
                .hospital(this.hospital)
                .availableBeds(department.getAvailableBeds())
                .build();
    }
}