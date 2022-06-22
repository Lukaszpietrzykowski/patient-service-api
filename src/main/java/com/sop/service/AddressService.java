package com.sop.service;

import com.sop.creators.AddressCreator;
import com.sop.dto.AddressDto;
import com.sop.entity.AddressEntity;
import com.sop.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klasa używająca interfejsu {@link AddressRepository} która pozwala zarządać operacjami CRUD
 * na bazie danych
 */
@Service
@AllArgsConstructor
public class AddressService {

    /**
     * Przechowuje obiekt typu {@link AddressRepository}
     */
    private final AddressRepository addressRepository;

    /**
     * Metoda tworząca rekord w bazie danych typu {@link AddressEntity} używając {@link AddressRepository}
     *
     * @param addressCreator obiekt typu {@link AddressCreator} przechowujący adres szpitalu wykorzystywany do stworzenia rekordu
     * @return zwraca stworzony rekord z bazy danych przekonwertowany na obiekt typu {@link AddressDto}
     */
    public AddressDto createAddress(AddressCreator addressCreator) {
        return AddressDto.of(addressRepository.save(AddressEntity.of(addressCreator)));
    }

    /**
     * Metoda pobierająca wszystkie rekordy {@link AddressEntity} z bazy danych i mapuje je na liste obiektów typu {@link AddressDto}
     *
     * @return zwraca listę obiektów typu {@link AddressDto}
     */
    public List<AddressDto> getAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(AddressDto::of)
                .toList();
    }

    /**
     * Metoda pobierająca rekord {@link AddressEntity} z bazy danych i mapująca go na obiekt typu {@link AddressDto}
     * W przypadku braku rekordu o podanym ID wyrzuca błąd informujący o braku ów rekordu
     *
     * @param id jest to ID rekordu w bazie danych
     * @return zwraca obiekt typu {@link AddressDto}
     */
    public AddressDto getAddress(Long id) {
        return AddressDto.of(addressRepository.findById(id)
                .orElseThrow());
    }

    /**
     * Metoda aktualizująca rekord {@link AddressEntity} w bazie danych i mapująca go na obiekt typu {@link AddressDto}
     * W przypadku braku rekordu o podanym ID wyrzuca błąd o braku ów rekordu
     *
     * @param id             jest to ID rekordu w bazie danych
     * @param addressCreator jest to obiekt typu {@link AddressCreator} który ma być zapisany w miejscu rekordu o podanym ID
     * @return zwraca zaktualizowany obiekt typu {@link AddressDto}
     */
    public AddressDto updateAddress(Long id, AddressCreator addressCreator) {
        return AddressDto.of(addressRepository.findById(id)
                .map(oldAddress -> {
                    AddressEntity updatedAddress = oldAddress.updateWith(AddressEntity.of(addressCreator));
                    return addressRepository.save(updatedAddress);
                })
                .orElseThrow());
    }

    /**
     * metoda usuwająca rekord {@link AddressEntity} z bazy danych
     *
     * @param id jest to id rekordu który chcemy usunąć
     */
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}

