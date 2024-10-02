package com.synergisticit.service.Implementation;

import com.synergisticit.domain.Reservation;
import com.synergisticit.domain.Role;
import com.synergisticit.repository.ReservationRepository;
import com.synergisticit.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        Optional<Reservation> optional = reservationRepository.findById(id);
        optional.ifPresent(reservation -> reservationRepository.delete(reservation));
    }
}
