package com.sop.entity;

import com.sop.creators.HospitalCreator;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca szpitale (obiekty/rekordy) znajdujące się w bazie danych.
 */
@Entity
@Table(name = "HOSPITAL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HospitalEntity {

    /**
     * Zmienna typu long przechowująca id szpitala.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     *  Zmienna typu String przechowująca nazwę szpitala.
     */
    private String name;

    /**
     * Zmienna typu AddressEntity przechowująca adres szpitala.
     */
    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    /**
     *  Lista przechowująca oddziały typu DepartmentEntity.
     */
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.REMOVE)
    private List<DepartmentEntity> departments = new ArrayList<>();

    /**
     * Konwertuje obiekt klasy HospitalCreator na obiekt klasy HospitalEntity.
     * @param hospital zmienna przechowujący szpital typu HospitalCreator który chcemy przekonwertować..
     * @return zwraca przekonwertowany szpital typu HospitalEntity.
     */
    public static HospitalEntity of(HospitalCreator hospital) {
        return HospitalEntity.builder()
                .name(hospital.getName())
                .departments(new ArrayList<>())
                .build();
    }

    /**
     * Edutuje szpital.
     * @param hospital zmienna przechowujący szpital typu HospitalEntity, przekazany do edycji.
     * @return zwraca zedytowany szpital typu HospitalEntity.
     */
    public HospitalEntity updateWith(HospitalEntity hospital) {
        return HospitalEntity.builder()
                .id(this.id)
                .name(hospital.getName())
                .address(hospital.getAddress())
                .departments(hospital.getDepartments())
                .build();
    }
}
