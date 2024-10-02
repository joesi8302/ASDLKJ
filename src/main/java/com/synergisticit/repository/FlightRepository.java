package com.synergisticit.repository;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(nativeQuery = true,value = "SELECT MAX(flight_Id) FROM flight")
    Long getMaxId();

    @Query("SELECT f FROM Flight f WHERE (:departureCity IS NULL OR f.departureCity = :departureCity) AND (:arrivalCity IS NULL OR f.arrivalCity = :arrivalCity) AND (:operatingAirlines IS NULL OR f.operatingAirlines = :operatingAirlines)")
    List<Flight> findFlights(@Param("departureCity") String departureCity,
                             @Param("arrivalCity") String arrivalCity,
                             @Param("operatingAirlines") Airlines operatingAirlines);


}
