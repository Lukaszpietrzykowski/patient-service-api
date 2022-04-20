package com.sop.controller;

import com.sop.creators.DepartmentCreator;
import com.sop.dto.DepartmentDto;
import com.sop.entity.DepartmentEntity;
import com.sop.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/all")
    public List<DepartmentDto> getDepartments() {
        return departmentService.getDepartments();
    }

    @PostMapping("/add")
    public DepartmentEntity addDepartment(@RequestBody DepartmentCreator departmentCreator) {
        return departmentService.saveDepartment(departmentCreator);
    }

    @GetMapping("/{id}")
    public DepartmentEntity findDepartmentById(@PathVariable long id) {
        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/update/{id}")
    public DepartmentEntity updateDepartment(@RequestBody DepartmentCreator departmentCreator, @PathVariable long id) {
        return departmentService.updateDepartment(departmentCreator, id);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteDepartment(@PathVariable long id) {
        departmentService.deleteDepartment(id);
    }
}
