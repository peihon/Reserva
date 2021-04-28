package com.uniamerica.prova2.service;

import com.uniamerica.prova2.model.Carro;
import com.uniamerica.prova2.model.Reserva;
import com.uniamerica.prova2.repository.CarroRepository;
import com.uniamerica.prova2.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    private final CarroRepository carroRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, CarroRepository carroRepository) {
        this.reservaRepository = reservaRepository;
        this.carroRepository = carroRepository;
    }

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Reserva findById(Long id) {
        Optional<Reserva> found = reservaRepository.findById(id);

        if(found.isPresent()) return found.get(); else return null;
    }

    public Reserva insereReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public List<Reserva> findByCarro(Carro carro) {
        return reservaRepository.findByCarro(carro);
    }


}