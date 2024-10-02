package com.synergisticit.repository;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(nativeQuery = true,value = "SELECT MAX(ticket_Number) FROM reservation")
    Long getMaxId();


}
