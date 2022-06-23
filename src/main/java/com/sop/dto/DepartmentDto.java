package com.sop.dto;

import com.sop.entity.DepartmentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

/**
 * Klasa przechowująca DTO (Data Transfer Object) dla klasy {@link DepartmentEntity}, służąca do operacji na oddziale.
 */
@AllArgsConstructor
@Builder
@Getter
public class DepartmentDto {
    /**
     * Zmienna typu long przechowująca id oddziału.
     */
    private long id;
    /**
     * Zmienna typu String przechowująca nazwę oddziału.
     */
    private String name;
    /**
     * Zmienna przchowująca listę pacjentów typu {@link PatientDto}.
     */
    private List<PatientDto> patents;
    /**
     * Zmienna typu String przechowująca adres mailowy użytkownika.
     */
    private long availableBeds;

    /**
     * Metoda konwertuje obiekt klasy {@link DepartmentEntity} na obiekt klasy {@link DepartmentDto}
     *
     * @param department obiekt przechowujący oddział typu {@link DepartmentEntity} który chcemy prekonwertować
     * @return zwraca przekonwertowany oddział typu {@link DepartmentDto}
     */
    public static DepartmentDto of(DepartmentEntity department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .patents(PatientDto.listOf(department.getPatients()))
                .availableBeds(department.getAvailableBeds())
                .build();
    }

    /**
     * Wewnętrzne DTO (Data Transfer Object) dla klasy {@link DepartmentDto}, służąca do operacji na oddziale.
     */
    @AllArgsConstructor
    @Builder
    @Getter
    public static class DepartmentDtoShort {
        /**
         * Zmienna typu long przechowująca id oddziału.
         */
        private long id;
        /**
         * Zmienna typu String przechowująca nazwę oddziału.
         */
        private String name;
        /**
         * Zmienna typu long przechowująca liczbę wszystkich łóżek w oddziale.
         */
        private long availableBeds;
        /**
         * Zmienna typu long przechowująca liczbę wolnych łóżek w oddziale.
         */
        private long remainingBeds;

        /**
         * Konwertuje obiekt klasy {@link DepartmentEntity} na obiekt klasy {@link DepartmentDtoShort}.
         *
         * @param department zmienna przechowujący oddział typu {@link DepartmentEntity} który chcemy przekonwertować.
         * @return zwraca przekonwertowany oddział typu {@link DepartmentDtoShort}.
         */
        public static DepartmentDtoShort of(DepartmentEntity department) {
            return DepartmentDtoShort.builder().
                    id(department.getId())
                    .name(department.getName())
                    .availableBeds(department.getAvailableBeds())
                    .remainingBeds(getRemainingBeds(department))
                    .build();
        }

        /**
         * Zwraca ilość wolnych łóżek w danym oddziale.
         *
         * @param department zmienna przechowujący oddział typu {@link DepartmentEntity}
         * @return Zwraca ilość wolnych łóżek w danym oddziale.
         */
        private static long getRemainingBeds(DepartmentEntity department) {
            int beds = department.getPatients().stream()
                    .filter(patient -> Objects.isNull(patient.getDischargeDate()))
                    .toList().size();
            return department.getAvailableBeds() - beds;
        }

        /**
         * Zwraca listę oddziałów w którym są dostępne łóżka.
         *
         * @param departments lista przechowująca oddziały typu {@link DepartmentEntity}.
         * @return Zwraca listę oddziałów w którym są dostępne łóżka.
         */
        public static List<DepartmentDtoShort> listOf(List<DepartmentEntity> departments) {
            return departments.stream()
                    .map(DepartmentDtoShort::of)
                    .filter(department -> department.getRemainingBeds() > 0)
                    .toList();
        }

        /**
         * Zwraca listę oddziałów bez względu na dostępne łóżka.
         *
         * @param departments lista przechowująca oddziały typu {@link DepartmentEntity}.
         * @return Zwraca listę oddziałów bez względu na dostępne łóżka.
         */
        public static List<DepartmentDtoShort> listOfWithDetails(List<DepartmentEntity> departments) {
            return departments.stream()
                    .map(DepartmentDtoShort::of)
                    .toList();
        }
    }
}
