package com.sop.service;

import com.sop.dto.DepartmentDto;
import com.sop.entity.DepartmentEntity;
import com.sop.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {
    @Autowired
    private final DepartmentRepository repository;

    public DepartmentEntity saveDepartment(DepartmentEntity departmentEntity)
    {
        return repository.save(departmentEntity);
    }

    public List<DepartmentEntity> saveDepartments(List<DepartmentEntity> departmentEntities)
    {
        return repository.saveAll(departmentEntities);
    }

    public List<DepartmentEntity> getDepartmests()
    {
        return repository.findAll();
    }

    public DepartmentEntity getDepartmentById(long departmentId)
    {
        return repository.findById(departmentId).orElse(null);
    }

    public void deleteDepartment(long departmentId)
    {
        repository.deleteById(departmentId);
    }

    public DepartmentEntity updateDepartment(DepartmentEntity departmentEntity)
    {
        DepartmentEntity existingDepartment = repository.findById(departmentEntity.getId()).orElse(null);
        existingDepartment.setName(departmentEntity.getName());
        existingDepartment.setHospital(departmentEntity.getHospital());
        existingDepartment.setAvailableBeds(departmentEntity.getAvailableBeds());

        return repository.save(existingDepartment);
    }

    public List<DepartmentDto> getDepartment() {
        return repository.findAll()
                .stream()
                .map(DepartmentDto::of)
                .toList();
    }
}
