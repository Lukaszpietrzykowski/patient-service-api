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

/**
 * Klasa DepartmentController to klasa zawierająca funkcjonalności wykorzystywane
 * w tworzeniu REST API umożliwijąca wszelkie operacje związane z oddziałem szpitala.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    /**
     * Przechowuję obiekt typu {@link DepartmentService}.
     */
    private final DepartmentService departmentService;

    /**
     * Zwraca listę wszystkich dostępnych oddziałów typu {@link DepartmentDto}.
     *
     * @return zwraca listę wszystkich oddziałów.
     */
    @GetMapping("/all")
    public List<DepartmentDto> getDepartments() {
        return departmentService.getDepartments();
    }

    /**
     * Zwraca obiekt typu {@link DepartmentDto} na podstawie podanego adresu id.
     *
     * @param id oddziału.
     * @return zwraca znaleziony oddział typu {@link DepartmentDto}.
     */
    @GetMapping("/{id}")
    public DepartmentDto findDepartmentById(@PathVariable long id) {
        return departmentService.getDepartment(id);
    }

    /**
     * Tworzy oddział.
     *
     * @param department obiekt typu {@link DepartmentCreator} przechowujący oddział.
     * @return zwraca dodany oddział typu {@link DepartmentDto}.
     */
    @PostMapping("/add")
    public DepartmentDto addDepartment(@RequestBody DepartmentCreator department) {
        return departmentService.createDepartment(department);
    }

    /**
     * Edytuje oddział o podanym id.
     *
     * @param id         oddziału.
     * @param department obiekt typu {@link DepartmentCreator} przechowujący oddział.
     * @return zwraca zedytowany oddział typu {@link DepartmentDto}.
     */
    @PutMapping("/update/{id}")
    public DepartmentDto updateDepartment(@PathVariable long id, @RequestBody DepartmentCreator department) {
        return departmentService.updateDepartment(id, department);
    }

    /**
     * Usuwa oddział szpitalu na podstawie podanego id.
     *
     * @param id oddziału.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteDepartment(@PathVariable long id) {
        departmentService.deleteDepartment(id);
    }
}