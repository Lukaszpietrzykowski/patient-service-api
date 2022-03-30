package com.sop.service;

import com.sop.dto.HospitalDto;
import com.sop.repository.HospitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public List<HospitalDto> getHospitals() {
        return hospitalRepository.findAll()
                .stream()
                .map(HospitalDto::of)
                .toList();
    }
}
