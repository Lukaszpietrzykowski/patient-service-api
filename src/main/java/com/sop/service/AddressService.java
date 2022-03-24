package com.sop.service;

import com.sop.dto.AddressDto;
import com.sop.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    AddressRepository addressRepository;

    public List<AddressDto> getAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(AddressDto::of)
                .toList();
    }
}

