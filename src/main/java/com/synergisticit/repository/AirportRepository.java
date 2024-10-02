package com.synergisticit.repository;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query(nativeQuery = true,value = "SELECT MAX(airport_Id) FROM airport")
    Long getMaxId();

    @Query(nativeQuery = true,value = "SELECT airport_city FROM airport")
    List<String> getAirportCities();

    @Query(nativeQuery = true,value = "SELECT * FROM airport WHERE airport_city = ?1")
    Airport getAirportByCity(String city);
}
