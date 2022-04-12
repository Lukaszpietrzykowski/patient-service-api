package com.sop.repository;

import com.sop.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Long> {
    boolean existsByCityAndStreetAndStreetNumberAndPostalCodeAndCountryAndLatAndLng(String city, String street, String streetNumber, String postalCode, String country, double lat, double lng);
}
