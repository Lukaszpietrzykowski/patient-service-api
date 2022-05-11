package com.sop.service;

import com.sop.creators.HospitalCreator;
import com.sop.dto.HospitalDto;
import com.sop.entity.AddressEntity;
import com.sop.entity.HospitalEntity;
import com.sop.repository.AddressRepository;
import com.sop.repository.HospitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final AddressRepository addressRepository;

    public HospitalDto createHospital(HospitalCreator hospitalCreator) {
        AddressEntity address = addressRepository.save(AddressEntity.of(hospitalCreator.getAddress()));

        HospitalEntity hospital = HospitalEntity.of(hospitalCreator);
        hospital.setAddress(address);

        return HospitalDto.of(hospitalRepository.save(hospital));
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
                    AddressEntity oldAddress = addressRepository.findById(oldHospital.getAddress().getId())
                            .orElseThrow();
                    AddressEntity updatedAddress = oldAddress.updateWith(AddressEntity.of(hospitalCreator.getAddress()));
                    addressRepository.save(updatedAddress);

                    HospitalEntity updateHospital = oldHospital.updateWith(HospitalEntity.of(hospitalCreator));
                    updateHospital.setAddress(updatedAddress);

                    return hospitalRepository.save(updateHospital);
                }).orElseThrow());
    }

    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }
}
