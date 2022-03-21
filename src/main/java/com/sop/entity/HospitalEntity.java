package com.sop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HOSPITAL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HospitalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToMany(mappedBy = "hospital")
    private List<DepartmentEntity> departments = new ArrayList<>();

}
