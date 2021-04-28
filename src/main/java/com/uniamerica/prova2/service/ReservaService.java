package com.uniamerica.prova2.service;

import com.uniamerica.prova2.model.Reserva;
import com.uniamerica.prova2.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }
    public Reserva insereReserva(Reserva reserva){
        return reservaRepository.save(reserva);
    }


    public Reserva findByStatus(String status) {
        return reservaRepository.findByStatus(status);
    }
}
