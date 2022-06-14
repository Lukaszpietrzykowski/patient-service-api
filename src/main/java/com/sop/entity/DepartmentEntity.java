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

@Entity
@Table(name = "DEPARTMENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
    private List<PatientEntity> patients = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private HospitalEntity hospital;

    private long availableBeds;

    public static DepartmentEntity of(DepartmentCreator department) {
        return DepartmentEntity.builder()
                .name(department.getName())
                .patients(new ArrayList<>())
                .availableBeds(department.getAvailableBeds())
                .build();
    }

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
