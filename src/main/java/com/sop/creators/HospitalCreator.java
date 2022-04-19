package com.sop.creators;

import com.sop.entity.DepartmentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HospitalCreator {

    private String name;
    private AddressCreator address;

    private List<DepartmentEntity> departments = new ArrayList<>();
}
