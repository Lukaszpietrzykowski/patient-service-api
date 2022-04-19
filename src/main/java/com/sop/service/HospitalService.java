package com.sop.service;

import com.sop.creators.HospitalCreator;
import com.sop.dto.HospitalDto;
import com.sop.repository.HospitalRepository;
import com.sop.entity.HospitalEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class HospitalService {


    public HospitalDto createHospital(HospitalCreator hospitalCreator)
    {
        return HospitalDto.of(hospitalRepository.save(HospitalEntity.of(hospitalCreator)));
    }
    @Autowired
    private static HospitalRepository hospitalRepository;

    public List<HospitalDto> getHospitals() {
        return hospitalRepository.findAll()
                .stream()
                .map(HospitalDto::of)
                .toList();
    }
    public static HospitalDto getHospital(long id){
        return HospitalDto.of(hospitalRepository.findById(id).orElseThrow());

    }
    public HospitalDto updateHospital(Long id, HospitalCreator hospitalCreator){
        return HospitalDto.of(hospitalRepository.findById(id)
                .map(oldHospital ->{
                    HospitalEntity updateHospital = oldHospital.updateWith(HospitalEntity.of(hospitalCreator));
                    return hospitalRepository.save(updateHospital);
                }).orElseThrow());
    }
    public void deleteHospital(long id){
        hospitalRepository.deleteAllById(Collections.singleton(id));
    }
}
