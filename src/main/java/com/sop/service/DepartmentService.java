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

    private final DepartmentRepository repository;
    private final HospitalRepository hospitalRepository;

    public DepartmentEntity saveDepartment(DepartmentCreator departmentCreator) {
        HospitalEntity hospital = hospitalRepository.findById(departmentCreator.getHospitalId())
                .orElse(null);

        DepartmentEntity preparedDepartment = DepartmentEntity.builder()
                .hospital(hospital)
                .name(departmentCreator.getName())
                .build();

        return repository.save(preparedDepartment);
    }

    public DepartmentEntity getDepartmentById(long departmentId) {
        return repository.findById(departmentId)
                .orElse(null);
    }

    public void deleteDepartment(long departmentId) {
        repository.deleteById(departmentId);
    }

    public DepartmentEntity updateDepartment(DepartmentCreator departmentCreator, long id) {
        DepartmentEntity existingDepartment = repository.findById(id).get();
        HospitalEntity hospital = hospitalRepository.findById(departmentCreator.getHospitalId()).get();

        existingDepartment.setName(departmentCreator.getName());
        existingDepartment.setHospital(hospital);
        return repository.save(existingDepartment);
    }

    public List<DepartmentDto> getDepartments() {
        return repository.findAll()
                .stream()
                .map(DepartmentDto::of)
                .toList();
    }
}
