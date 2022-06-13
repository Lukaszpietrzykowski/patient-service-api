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

@Entity
@Table(name = "HOSPITAL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HospitalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.REMOVE)
    private List<DepartmentEntity> departments = new ArrayList<>();

    public static HospitalEntity of(HospitalCreator hospital) {
        return HospitalEntity.builder()
                .name(hospital.getName())
                .departments(new ArrayList<>())
                .build();
    }

    public HospitalEntity updateWith(HospitalEntity hospital) {
        return HospitalEntity.builder()
                .id(this.id)
                .name(hospital.getName())
                .address(hospital.getAddress())
                .departments(hospital.getDepartments())
                .build();
    }
}
