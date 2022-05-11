package com.sop.service;

import com.sop.creators.DepartmentCreator;
import com.sop.dto.DepartmentDto;
import com.sop.entity.DepartmentEntity;
import com.sop.entity.HospitalEntity;
import com.sop.repository.DepartmentRepository;
import com.sop.repository.HospitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;

    public DepartmentDto createDepartment(DepartmentCreator departmentCreator) {
        HospitalEntity hospital = hospitalRepository.findById(departmentCreator.getHospitalId())
                .orElseThrow();

        DepartmentEntity department = DepartmentEntity.of(departmentCreator);
        department.setHospital(hospital);

        hospital.getDepartments().add(department);
        hospitalRepository.save(hospital);

        return DepartmentDto.of(departmentRepository.save(department));
    }

    public List<DepartmentDto> getDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(DepartmentDto::of)
                .toList();
    }

    public DepartmentDto getDepartment(Long id) {
        return DepartmentDto.of(departmentRepository.findById(id)
                .orElseThrow());
    }

    public DepartmentDto updateDepartment(Long id, DepartmentCreator departmentCreator) {
        return DepartmentDto.of(departmentRepository.findById(id)
                .map(oldDepartment -> {
                    HospitalEntity hospital = hospitalRepository.findById(departmentCreator.getHospitalId())
                            .orElseThrow();

                    DepartmentEntity updatedDepartment = oldDepartment.updateWith(DepartmentEntity.of(departmentCreator));
                    updatedDepartment.setHospital(hospital);

                    return departmentRepository.save(updatedDepartment);
                })
                .orElseThrow());
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
