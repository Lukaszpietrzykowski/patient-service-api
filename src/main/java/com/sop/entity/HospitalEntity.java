package com.sop.entity;

import com.sop.creators.HospitalCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HOSPITAL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
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

    public static HospitalEntity of (HospitalCreator hospital)
    {
        return  HospitalEntity.builder()
                .name(hospital.getName())
                .address(hospital.getAddress())
                .departments(hospital.getDepartments())
                .build();

    }
    public HospitalEntity updateWith(HospitalEntity hospital)
    {
        return HospitalEntity.builder()
                .id(this.id)
                .name(hospital.getName())
                .address(hospital.getAddress())
                .departments(hospital.getDepartments())
                .build();
    }
}
