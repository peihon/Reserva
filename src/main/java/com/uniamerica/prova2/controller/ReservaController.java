package com.uniamerica.prova2.controller;

import com.uniamerica.prova2.model.Reserva;
import com.uniamerica.prova2.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<?> insertReserva(@RequestBody Reserva reserva) throws Exception {
        Reserva add;
        try {
            add = reservaService.insereReserva(reserva);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return new ResponseEntity<>(add, null, HttpStatus.CREATED);
    }

    @PutMapping("reservado/{status}")
    public ResponseEntity<?> findByRg (@PathVariable String status) throws Exception {
        try{
            Reserva reserva = reservaService.findByStatus(status);

            if(reserva != null) return new ResponseEntity<>(reserva, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);

        } catch (Exception exception) {
            throw new Exception(exception);
        }
    }

}
