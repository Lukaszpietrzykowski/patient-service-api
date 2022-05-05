package com.sop.service;

import com.sop.creators.HospitalCreator;
import com.sop.dto.HospitalDto;
import com.sop.entity.HospitalEntity;
import com.sop.repository.HospitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalDto createHospital(HospitalCreator hospitalCreator) {
        return HospitalDto.of(hospitalRepository.save(HospitalEntity.of(hospitalCreator)));
    }

    public List<HospitalDto> getHospitals() {
        return hospitalRepository.findAll()
                .stream()
                .map(HospitalDto::of)
                .toList();
    }

    public HospitalDto getHospital(Long id) {
        return HospitalDto.of(hospitalRepository.findById(id)
                .orElseThrow());

    }

    public HospitalDto updateHospital(Long id, HospitalCreator hospitalCreator) {
        return HospitalDto.of(hospitalRepository.findById(id)
                .map(oldHospital -> {
                    HospitalEntity updateHospital = oldHospital.updateWith(HospitalEntity.of(hospitalCreator));
                    return hospitalRepository.save(updateHospital);
                }).orElseThrow());
    }

    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }
}
