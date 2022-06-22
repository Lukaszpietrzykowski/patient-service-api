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

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    private final DepartmentRepository departmentRepository;

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

    public void updateMedicalHistory(long id, String patientTextInformation) {
        PatientEntity patient = patientRepository.getById(id);
        patient.setMedicalHistory(patientTextInformation);
        patientRepository.save(patient);
    }

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

    public PatientDto getPatient(long id) {
        return PatientDto.of(
                patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Error patient doesn't exist"))
        );
    }

    public void createPatient(PatientCreator patient) {
        PatientEntity patientEntityByPesel = null;
        if (Objects.nonNull(patient.getPesel()) && !patient.getPesel().isEmpty()) {
            patientEntityByPesel = patientRepository.getPatientEntityByPesel(patient.getPesel());
        }
        if (Objects.nonNull(patientEntityByPesel)) {
            patientEntityByPesel.setDischargeDate(null);
            patientRepository.save(patientEntityByPesel);
        } else {
            DepartmentEntity department = departmentRepository.getById(patient.getDepartmentId());
            PatientEntity patientEntity = PatientEntity.of(patient, LocalDateTime.now().plusHours(2));
            patientEntity.setDepartment(department);
            patientRepository.save(patientEntity);
        }
    }

    public void createVisit(PatientCreator patient) {
        patientRepository.save(PatientEntity.of(patient, patient.getRegistrationDate()));
    }

    public void updatePatient(long id, PatientCreator patient) {
        DepartmentEntity departmentEntity = departmentRepository.findById(patient.getDepartmentId()).orElse(null);
        patientRepository.findById(id)
                .map(oldPatient -> {
                    PatientEntity updatedPatient = oldPatient.updateWith(PatientEntity.of(patient, patient.getRegistrationDate()));
                    updatedPatient.setDepartment(departmentEntity);
                    updatedPatient.setMedicalHistory(oldPatient.getMedicalHistory());
                    return patientRepository.save(updatedPatient);
                })
                .orElseThrow(() -> new RuntimeException("Patient doesn't exist"));
    }

    public void deletePatient(long id) {
        patientRepository.findById(id)
                .map(patientObject -> {
                    patientObject.setDischargeDate(LocalDateTime.now().plusHours(2));
                    return patientRepository.save(patientObject);
                })
                .orElseThrow(() -> new RuntimeException("Patient doesn't exist"));
    } // usunięcie pacjenta tyczysz sie jego wypisaniu a nie usunięciu pacjenta z listy pacjentów według mnie tak powinno to być

}
