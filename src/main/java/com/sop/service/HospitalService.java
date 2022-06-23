package com.sop.service;

import com.sop.creators.HospitalCreator;
import com.sop.dto.DepartmentDto;
import com.sop.dto.HospitalDto;
import com.sop.entity.AddressEntity;
import com.sop.entity.HospitalEntity;
import com.sop.repository.AddressRepository;
import com.sop.repository.HospitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klasa używająca interfejsu {@link HospitalRepository} oraz {@link AddressRepository} które pozwalają zarządać operacjami CRUD
 * na bazie danych
 */
@Service
@AllArgsConstructor
public class HospitalService {

    /**
     * Przechowuje obiekt typu {@link HospitalRepository}
     */
    private final HospitalRepository hospitalRepository;
    /**
     * Przechowuje obiekt typu {@link AddressRepository}
     */
    private final AddressRepository addressRepository;

    /**
     * Metoda tworząca rekord w bazie danych typu {@link HospitalCreator}, przypisuje adres do określonego szpitala w bazie danych
     *
     * @param hospitalCreator obiekt typu {@link HospitalCreator} przechowujący dane o szpitalu wykorzystywany do stworzenia rekordu
     * @return zwraca stworzony rekord z bazy danych przekonwertowany na obiekt typu {@link HospitalDto}
     */
    public HospitalDto createHospital(HospitalCreator hospitalCreator) {
        AddressEntity address = addressRepository.save(AddressEntity.of(hospitalCreator.getAddress()));

        HospitalEntity hospital = HospitalEntity.of(hospitalCreator);
        hospital.setAddress(address);

        return HospitalDto.of(hospitalRepository.save(hospital));
    }

    /**
     * Metoda pobierająca wszystkie rekordy {@link HospitalEntity} z bazy danych i mapuje je na liste obiektów typu {@link HospitalDto}
     * z ogólnymi informacjami o oddziałach szpitala przeznaczonych dla zwykłego użytkownika
     *
     * @return zwraca listę obiektów typu {@link HospitalDto} z ogólnymi informacjami o oddziałach szpitala
     */
    public List<HospitalDto> getHospitals() {
        return hospitalRepository.findAll()
                .stream()
                .map(HospitalDto::of)
                .toList();
    }

    /**
     * Metoda pobierająca wszystkie rekordy {@link HospitalEntity} z bazy danych i mapuje je na liste obiektów typu {@link HospitalDto}
     * z szczególowymi informacjami o oddziałach szpitala wyłącznie dla admina
     *
     * @return zwraca listę obiektów typu {@link HospitalDto} z szczególowymi informacjami o oddziałach szpitala
     */
    public List<HospitalDto> getHospitalsDetails() {
        return hospitalRepository.findAll()
                .stream()
                .map(HospitalDto::ofWithDetails)
                .toList();
    }

    /**
     * Metoda pobierająca rekord {@link HospitalEntity} z bazy danych i mapująca go na obiekt typu {@link HospitalDto}
     * W przypadku braku rekordu o podanym ID wyrzuca błąd informujący o braku ów rekordu
     *
     * @param id jest to ID rekordu w bazie danych
     * @return zwraca obiekt typu {@link HospitalDto}
     */
    public HospitalDto getHospital(Long id) {
        return HospitalDto.of(hospitalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error hospital doesn't exist")));

    }

    /**
     * Metoda pobierająca liste oddziałów z rekordu {@link HospitalEntity} w bazie danych i mapująca ją na liste typu {@link com.sop.dto.DepartmentDto.DepartmentDtoShort}
     *
     * @param id jest to ID rekordu w bazie danych
     * @return zwraca listę obiektów typu {@link com.sop.dto.DepartmentDto.DepartmentDtoShort}
     */
    public List<DepartmentDto.DepartmentDtoShort> getHospitalDepartments(Long id) {
        return HospitalDto.ofWithDetails(hospitalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error hospital doesn't exist"))).getDepartments();

    }

    /**
     * Metoda aktualizująca rekord {@link HospitalEntity} w bazie danych i mapująca go na obiekt typu {@link HospitalDto}
     * W przypadku braku rekordu o podanym ID wyrzuca błąd o braku ów rekordu
     *
     * @param id              jest to ID rekordu w bazie danych
     * @param hospitalCreator jest to obiekt typu {@link HospitalCreator} który ma być zapisany w miejscu rekordu o podanym ID
     * @return zwraca zaktualizowany obiekt typu {@link HospitalDto}
     */
    public HospitalDto updateHospital(Long id, HospitalCreator hospitalCreator) {
        return HospitalDto.of(hospitalRepository.findById(id)
                .map(oldHospital -> {
                    AddressEntity oldAddress = addressRepository.findById(oldHospital.getAddress().getId())
                            .orElseThrow(() -> new RuntimeException("Error address doesn't exist"));
                    AddressEntity updatedAddress = oldAddress.updateWith(AddressEntity.of(hospitalCreator.getAddress()));
                    addressRepository.save(updatedAddress);

                    HospitalEntity updateHospital = oldHospital.updateWith(HospitalEntity.of(hospitalCreator));
                    updateHospital.setAddress(updatedAddress);

                    return hospitalRepository.save(updateHospital);
                }).orElseThrow(() -> new RuntimeException("Error hospital doesn't exist")));
    }

    /**
     * Metoda usuwająca rekord {@link HospitalEntity} z bazy danych
     *
     * @param id jest to ID rekordu w bazie danych
     */
    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }
}