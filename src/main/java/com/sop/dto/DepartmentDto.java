package com.sop.dto;

import com.sop.entity.DepartmentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Klasa przechowująca DTO (Data Transfer Object) dla klasy DepartmentEntity, służąca do operacji na oddziale.
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
     * Zmienna przchowująca listę pacjentów typu PatientDto.
     */
    private List<PatientDto> patents;
    /**
     * Zmienna typu String przechowująca adres mailowy użytkownika.
     */
    private long availableBeds;

    /**
     * Metoda konwertuje obiekt klasy DepartmentEntity na obiekt klasy DepartmentDto
     * @param department obiekt przechowujący oddział typu DepartmentEntity który chcemy prekonwertować
     * @return zwraca przekonwertowany oddział typu DepartmentDto
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
     * Wewnętrzne DTO (Data Transfer Object) dla klasy DepartmentDto, służąca do operacji na oddziale.
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
         * Konwertuje obiekt klasy DepartmentEntity na obiekt klasy DepartmentDtoShort.
         * @param department zmienna przechowujący oddział typu DepartmentEntity który chcemy przekonwertować.
         * @return zwraca przekonwertowany oddział typu AddressDepartmentDtoShort.
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
         * @param department zmienna przechowujący oddział typu DepartmentEntity
         * @return Zwraca ilość wolnych łóżek w danym oddziale.
         */
        private static long getRemainingBeds(DepartmentEntity department) {
            return department.getAvailableBeds() - department.getPatients().size();
        }

        /**
         * Zwraca listę oddziałów w którym są dostępne łóżka.
         * @param departments lista przechowująca oddziały typu DepartmentEntity.
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
         * @param departments lista przechowująca oddziały typu DepartmentEntity.
         * @return Zwraca listę oddziałów bez względu na dostępne łóżka.
         */
        public static List<DepartmentDtoShort> listOfWithDetails(List<DepartmentEntity> departments) {
            return departments.stream()
                    .map(DepartmentDtoShort::of)
                    .toList();
        }
    }
}
