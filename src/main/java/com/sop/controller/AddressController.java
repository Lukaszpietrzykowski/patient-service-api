package com.sop.controller;

import com.sop.dto.AddressDto;
import com.sop.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;

    @GetMapping("/getAll")
    public List<AddressDto> getAddresses() {
        return addressService.getAddresses();
    }
}
