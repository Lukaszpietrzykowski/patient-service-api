package com.sop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DEPARTMENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "department")
    private List<PatientEntity> patients = new ArrayList<>() ;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private HospitalEntity hospital;

    private long availableBeds;
}
