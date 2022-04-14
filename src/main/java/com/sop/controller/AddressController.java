package com.sop.controller;

import com.sop.creator.AddressCreator;
import com.sop.dto.AddressDto;
import com.sop.service.AddressService;
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

@RestController
@AllArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public List<AddressDto> getAddresses() {
        return addressService.getAddresses();
    }

    @GetMapping("/{id}")
    public AddressDto getAddress(@PathVariable Long id) {
        return addressService.getAddress(id);
    }

    @PostMapping("/add")
    public AddressDto createAddress(@RequestBody AddressCreator address) {
        return addressService.createAddress(address);
    }

    @PutMapping("/update/{id}")
    public AddressDto updateAddress(@PathVariable Long id, @RequestBody AddressCreator address) {
        return addressService.updateAddress(id, address);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}
