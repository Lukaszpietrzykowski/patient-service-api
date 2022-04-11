package com.sop.service;

import com.sop.creator.AddressCreator;
import com.sop.dto.AddressDto;
import com.sop.entity.AddressEntity;
import com.sop.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    private final ModelMapper modelMapper;

    public AddressDto addAddress(AddressCreator addressCreator) {
        return convertToDto(addressRepository.save(convertToEntity(addressCreator)));
    }

    public List<AddressDto> getAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public AddressDto getAddress(Long id) {
        return convertToDto(addressRepository.findById(id).orElseThrow());
    }

    public AddressDto updateAddress(Long id, AddressCreator addressCreator) {
        return convertToDto(addressRepository.findById(id)
                .map(oldAddress -> {
                            AddressEntity updatedAddress = oldAddress.updateWith(convertToEntity(addressCreator));
                            return addressRepository.save(updatedAddress);
                        }
                ).orElseThrow());
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    private AddressEntity convertToEntity(AddressCreator addressCreator) {
        return modelMapper.map(addressCreator, AddressEntity.class);
    }

    private AddressDto convertToDto(AddressEntity addressEntity) {
        return modelMapper.map(addressEntity, AddressDto.class);
    }
}

