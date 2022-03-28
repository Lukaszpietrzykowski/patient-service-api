package com.sop.service;

import com.sop.dto.DepartmentDto;
import com.sop.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepository repository;

    public List<DepartmentDto> getUsers() {
        return repository.findAll()
                .stream()
                .map(DepartmentDto::of)
                .toList();
    }
}
