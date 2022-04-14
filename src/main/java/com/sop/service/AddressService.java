package com.sop.service;

import com.sop.creators.AddressCreator;
import com.sop.dto.AddressDto;
import com.sop.entity.AddressEntity;
import com.sop.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressDto createAddress(AddressCreator addressCreator) {
        return AddressDto.of(addressRepository.save(AddressEntity.of(addressCreator)));
    }

    public List<AddressDto> getAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(AddressDto::of)
                .toList();
    }

    public AddressDto getAddress(Long id) {
        return AddressDto.of(addressRepository.findById(id)
                .orElseThrow());
    }

    public AddressDto updateAddress(Long id, AddressCreator addressCreator) {
        return AddressDto.of(addressRepository.findById(id)
                .map(oldAddress -> {
                    AddressEntity updatedAddress = oldAddress.updateWith(AddressEntity.of(addressCreator));
                    return addressRepository.save(updatedAddress);
                })
                .orElseThrow());
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}

