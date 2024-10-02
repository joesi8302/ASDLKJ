package com.synergisticit.service.Implementation;

import com.synergisticit.domain.Passenger;
import com.synergisticit.domain.Reservation;
import com.synergisticit.repository.PassengerRepository;
import com.synergisticit.service.PassengerService;
import com.synergisticit.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    ReservationService reservationService;

    @Override
    public Passenger save(Passenger passenger) {
        Passenger savedPassenger = passengerRepository.save(passenger);
        Reservation reservation = savedPassenger.getReservation();
        reservation.setCheckedIn(false);
        reservation.setPassenger(savedPassenger);
        Reservation savedReservation = reservationService.save(reservation);
        savedPassenger.setReservation(savedReservation);

        savedPassenger = passengerRepository.save(savedPassenger);

        return savedPassenger;
    }

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger findById(Long id) {
        return passengerRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        Optional<Passenger> optional = passengerRepository.findById(id);
        optional.ifPresent(passenger -> passengerRepository.delete(passenger));
    }
}
