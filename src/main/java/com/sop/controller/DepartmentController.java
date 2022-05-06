package com.sop.controller;

import com.sop.creators.DepartmentCreator;
import com.sop.dto.DepartmentDto;
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

    @GetMapping("/{id}")
    public DepartmentDto findDepartmentById(@PathVariable long id) {
        return departmentService.getDepartment(id);
    }

    @PostMapping("/add")
    public DepartmentDto addDepartment(@RequestBody DepartmentCreator department) {
        return departmentService.createDepartment(department);
    }

    @PutMapping("/update/{id}")
    public DepartmentDto updateDepartment(@PathVariable long id, @RequestBody DepartmentCreator department) {
        return departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDepartment(@PathVariable long id) {
        departmentService.deleteDepartment(id);
    }
}
