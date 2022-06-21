package com.sop.controller;

import com.sop.creators.AddressCreator;
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

/**
 * Klasa UserController to klasa zawierająca funkcjonalności wykorzystywane
 * w tworzeniu REST API umożliwijąca wszelkie operacje związane z adresem szpitala.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/address")
public class AddressController {


    /**
     * Przechowuję obiekt typu AdressService.
     */
    private final AddressService addressService;

    /**
     * Zwraca listę wszystkich dostępnych adresów szpitali typu AddressDto.
     * @return zwraca listę wszystkich adresów.
     */
    @GetMapping("/all")
    public List<AddressDto> getAddresses() {
        return addressService.getAddresses();
    }

    /**
     * Zwraca obiekt typu AddressDto na podstawie podanego id.
     * @param id adresu.
     * @return Zwraca adres typu AdressDto na podstawie podanego id.
     */
    @GetMapping("/{id}")
    public AddressDto getAddress(@PathVariable long id) {
        return addressService.getAddress(id);
    }

    /**
     * Tworzy adres szpitala.
     * @param address  obiekt typu AddressCreator przechowujący adres szpitalu
     * @return zwraca utworzony adres typu AdressDto.
     */
    @PostMapping("/add")
    public AddressDto createAddress(@RequestBody AddressCreator address) {
        return addressService.createAddress(address);
    }

    /**
     * Edytuje adres szpitala o podanym id.
     * @param id adresu szpitalu.
     * @param address obiekt typu AddressCreator przechowujący adres szpitalu
     * @return zwraca zedytowany adres typu AdressDto
     */
    @PutMapping("/update/{id}")
    public AddressDto updateAddress(@PathVariable long id, @RequestBody AddressCreator address) {
        return addressService.updateAddress(id, address);
    }

    /**
     * Usuwa podany adres szpitalu na podstawie podanego id.
     * @param id adresu.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable long id) {
        addressService.deleteAddress(id);
    }
}
