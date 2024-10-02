package com.synergisticit.repository;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlinesRepository extends JpaRepository<Airlines, Long> {
    @Query(nativeQuery = true,value = "SELECT MAX(airlines_Id) FROM airlines")
    Long getMaxId();

    @Query(nativeQuery = true,value = "SELECT * FROM airlines where airlines_name=?1")
    Airlines findByAirlineName(String airlineName);

    @Query(nativeQuery = true,value = "SELECT * FROM airlines where airlines_code=?1")
    Airlines findByAirlineCode(String airlineCode);


}
