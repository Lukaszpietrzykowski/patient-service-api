package com.sop.service;

import com.sop.dto.PatientDto;
import com.sop.entity.PatientEntity;
import com.sop.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository repository;

    public List<PatientDto> getPatients() {
        return repository.findAll()
                .stream()
                .map(PatientDto::of)
                .toList();
    }

    public void addPatient(PatientEntity patient) {
        repository.save(patient);
    }
}
