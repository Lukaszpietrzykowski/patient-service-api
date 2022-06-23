package com.sop.service;

import com.sop.creators.PatientCreator;
import com.sop.dto.PatientDto;
import com.sop.entity.DepartmentEntity;
import com.sop.entity.PatientEntity;
import com.sop.repository.DepartmentRepository;
import com.sop.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Klasa używająca interfejsu {@link PatientRepository} oraz {@link DepartmentRepository} które pozwalają zarządać operacjami CRUD
 * na bazie danych
 */
@Service
@AllArgsConstructor
public class PatientService {

    /**
     * Przechowuje obiekt typu {@link PatientRepository}
     */
    private final PatientRepository patientRepository;

    /**
     * Przechowuje obiekt typu {@link DepartmentRepository}
     */
    private final DepartmentRepository departmentRepository;

    /**
     * Metoda pobierająca wszystkie rekordy {@link PatientEntity} którzy nie zostali jeszcze wypisani ze szpitala
     * z bazy danych i mapuje je na liste obiektów typu {@link PatientDto}
     *
     * @return zwraca listę obiektów typu {@link PatientDto}
     */
    public List<PatientDto> getPatients() {
        if (patientRepository.findAll().isEmpty()) {
            throw new RuntimeException("Cound not find any patients on list");
        } else {
            return patientRepository.findAll()
                    .stream()
                    .filter(patientEntity -> Objects.isNull(patientEntity.getDischargeDate()))
                    .map(PatientDto::of)
                    .toList();
        }
    }

    /**
     * Metoda aktualizująca rekord {@link PatientEntity} w bazie danych
     *
     * @param id                     jest to ID rekordu w bazie danych
     * @param patientTextInformation jest to opis dotyczący pacjenta i jego historii choroby
     */
    public void updateMedicalHistory(long id, String patientTextInformation) {
        PatientEntity patient = patientRepository.getById(id);
        patient.setMedicalHistory(patientTextInformation);
        patientRepository.save(patient);
    }

    /**
     * Metoda pobierająca wszystkie rekordy {@link PatientEntity} którzy zostali wypisani ze szpitala
     * z bazy danych i mapuje je na liste obiektów typu {@link PatientDto}
     *
     * @return zwraca listę obiektów typu {@link PatientDto}
     */
    public List<PatientDto> getArchivedPatients() {
        if (patientRepository.findAll().isEmpty()) {
            throw new RuntimeException("Cound not find any patients on list");
        } else {
            return patientRepository.findAll()
                    .stream()
                    .filter(patientEntity -> Objects.nonNull(patientEntity.getDischargeDate()))
                    .map(PatientDto::of)
                    .toList();
        }
    }

    /**
     * Metoda pobierająca rekord {@link PatientEntity} z bazy danych i mapująca go na obiekt typu {@link PatientDto}
     * W przypadku braku rekordu o podanym ID wyrzuca błąd informujący o braku ów rekordu
     *
     * @param id jest to ID rekordu w bazie danych
     * @return zwraca obiekt typu {@link PatientDto}
     */
    public PatientDto getPatient(long id) {
        return PatientDto.of(
                patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Error patient doesn't exist"))
        );
    }

    /**
     * Metoda tworząca rekord w bazie danych typu {@link PatientEntity}, przypisuje pacjenta do określonego oddziału w bazie danych
     *
     * @param patient obiekt typu {@link PatientCreator} przechowujący dane o pacjencie wtkorzystywaym do stworzenia rekordu
     */
    public void createPatient(PatientCreator patient) {
        PatientEntity patientEntityByPesel = null;
        if (Objects.nonNull(patient.getPesel())) {
            patientEntityByPesel = patientRepository.getPatientEntityByPesel(patient.getPesel());
        }
        if (Objects.nonNull(patientEntityByPesel)) {
            patientEntityByPesel.setDischargeDate(null);
            patientRepository.save(patientEntityByPesel);
        } else {
            DepartmentEntity department = departmentRepository.getById(patient.getDepartmentId());
            PatientEntity patientEntity = PatientEntity.of(patient, LocalDateTime.now());
            patientEntity.setDepartment(department);
            patientRepository.save(patientEntity);
        }
    }

    /**
     * Metoda tworząca rekord w bazie danych typu {@link PatientEntity}
     *
     * @param patient obiekt typu {@link PatientCreator} przechowujący dane o pacjencie wtkorzystywaym do stworzenia rekordu
     */
    public void createVisit(PatientCreator patient) {
        patientRepository.save(PatientEntity.of(patient, patient.getRegistrationDate()));
    }

    /**
     * Metoda aktualizująca rekord {@link PatientEntity} w bazie danych i mapująca go na obiekt typu {@link PatientDto}
     * W przypadku braku rekordu o podanym ID wyrzuca błąd o braku ów rekordu
     *
     * @param id      jest to ID rekordu w bazie danych
     * @param patient jest to obiekt typu {@link PatientCreator} który ma być zapisany w miejscu rekordu o podanym ID
     */
    public void updatePatient(long id, PatientCreator patient) {
        DepartmentEntity departmentEntity = departmentRepository.findById(patient.getDepartmentId()).orElse(null);
        patientRepository.findById(id)
                .map(oldPatient -> {
                    PatientEntity updatedPatient = oldPatient.updateWith(PatientEntity.of(patient, patient.getRegistrationDate()));
                    updatedPatient.setDepartment(departmentEntity);
                    return patientRepository.save(updatedPatient);
                })
                .orElseThrow(() -> new RuntimeException("Patient doesn't exist"));
    }

    /**
     * Metoda usuwająca rekord {@link PatientEntity} z bazy danych
     *
     * @param id jest to ID rekordu w bazie danych
     */
    public void deletePatient(long id) {
        patientRepository.findById(id)
                .map(patientObject -> {
                    patientObject.setDischargeDate(LocalDateTime.now());
                    return patientRepository.save(patientObject);
                })
                .orElseThrow(() -> new RuntimeException("Patient doesn't exist"));
    }
}