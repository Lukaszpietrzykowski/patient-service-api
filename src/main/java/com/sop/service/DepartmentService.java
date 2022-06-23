package com.sop.service;

import com.sop.creators.DepartmentCreator;
import com.sop.dto.DepartmentDto;
import com.sop.entity.DepartmentEntity;
import com.sop.entity.HospitalEntity;
import com.sop.repository.DepartmentRepository;
import com.sop.repository.HospitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klasa używająca interfejsu {@link DepartmentRepository} oraz {@link HospitalRepository} które pozwalają zarządać operacjami CRUD
 * na bazie danych
 */
@Service
@AllArgsConstructor
public class DepartmentService {

    /**
     * Przechowuje obiekt typu {@link DepartmentRepository}
     */
    private final DepartmentRepository departmentRepository;
    /**
     * Przechowuje obiekt typu {@link HospitalRepository}
     */
    private final HospitalRepository hospitalRepository;

    /**
     * Metoda tworząca rekord w bazie danych typu {@link DepartmentEntity}, przypisuje oddzial do określonego szpitala w bazie danych
     *
     * @param departmentCreator obiekt typu {@link DepartmentCreator} przechowujący dane o oddziale szpitala wykorzystywany do stworzenia rekordu
     * @return zwraca stworzony rekord z bazy danych przekonwertowany na obiekt typu {@link DepartmentDto}
     */
    public DepartmentDto createDepartment(DepartmentCreator departmentCreator) {
        HospitalEntity hospital = hospitalRepository.findById(departmentCreator.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Error hospital doesn't exist"));

        DepartmentEntity department = DepartmentEntity.of(departmentCreator);
        department.setHospital(hospital);

        hospital.getDepartments().add(department);
        hospitalRepository.save(hospital);

        return DepartmentDto.of(departmentRepository.save(department));
    }

    /**
     * Metoda pobierająca wszystkie rekordy {@link DepartmentEntity} z bazy danych i mapuje je na liste obiektów typu {@link DepartmentDto}
     *
     * @return zwraca listę obiektów typu {@link DepartmentDto}
     */
    public List<DepartmentDto> getDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(DepartmentDto::of)
                .toList();
    }

    /**
     * Metoda pobierająca rekord {@link DepartmentEntity} z bazy danych i mapująca go na obiekt typu {@link DepartmentDto}
     * W przypadku braku rekordu o podanym ID wyrzuca błąd informujący o braku ów rekordu
     *
     * @param id jest to ID rekordu w bazie danych
     * @return zwraca obiekt typu {@link DepartmentDto}
     */
    public DepartmentDto getDepartment(Long id) {
        return DepartmentDto.of(departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error department doesn't exist")));
    }

    /**
     * Metoda aktualizująca rekord {@link DepartmentEntity} w bazie danych i mapująca go na obiekt typu {@link DepartmentDto}
     * W przypadku braku rekordu o podanym ID wyrzuca błąd o braku ów rekordu
     *
     * @param id                jest to ID rekordu w bazie danych
     * @param departmentCreator jest to obiekt typu {@link DepartmentCreator} który ma być zapisany w miejscu rekordu o podanym ID
     * @return zwraca zaktualizowany obiekt typu {@link DepartmentDto}
     */
    public DepartmentDto updateDepartment(Long id, DepartmentCreator departmentCreator) {
        return DepartmentDto.of(departmentRepository.findById(id)
                .map(oldDepartment -> {
                    HospitalEntity hospital = hospitalRepository.findById(departmentCreator.getHospitalId())
                            .orElseThrow(() -> new RuntimeException("Error hospital doesn't exist"));

                    DepartmentEntity updatedDepartment = oldDepartment.updateWith(DepartmentEntity.of(departmentCreator));
                    updatedDepartment.setHospital(hospital);

                    return departmentRepository.save(updatedDepartment);
                })
                .orElseThrow(() -> new RuntimeException("Error department doesn't exist")));
    }

    /**
     * Metoda usuwająca rekord {@link DepartmentEntity} z bazy danych
     *
     * @param id jest to ID rekordu w bazie danych
     */
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}