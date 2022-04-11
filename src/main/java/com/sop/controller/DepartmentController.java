package com.sop.controller;

import com.sop.dto.DepartmentDto;
import com.sop.entity.DepartmentEntity;
import com.sop.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private final DepartmentService departmentService;

    @GetMapping
    public List<DepartmentDto> getDepartment() {
        return departmentService.getDepartment();
    }

    @PostMapping("/department/addDepartment")
    public DepartmentEntity addDepartment(@RequestBody DepartmentEntity departmentEntity)
    {
        return departmentService.saveDepartment(departmentEntity);
    }

    @PostMapping("/department/addDepartments")
    public List<DepartmentEntity> addDepartments(@RequestBody List<DepartmentEntity> departmentEntities)
    {
        return departmentService.saveDepartments(departmentEntities);
    }
    @GetMapping("/department/departmentById/{departmentId}")
    public DepartmentEntity findDepartmentById(@PathVariable long departmentId)
    {
        return departmentService.getDepartmentById(departmentId);
    }

    @GetMapping("/department/departments")
    public List<DepartmentEntity> findAllDepartments()
    {
        return departmentService.getDepartmests();
    }

    @PutMapping("/department/update")
    public DepartmentEntity updateDepartment(@RequestBody DepartmentEntity departmentEntity)
    {
        return departmentService.updateDepartment(departmentEntity);
    }

    @DeleteMapping("/department/dellete/{departmentId}")
    public void deleteDepartment(@PathVariable long departmentId)
    {
        departmentService.deleteDepartment(departmentId);
    }
}
