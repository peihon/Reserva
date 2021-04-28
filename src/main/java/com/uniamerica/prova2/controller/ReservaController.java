package com.uniamerica.prova2.controller;

import com.uniamerica.prova2.model.Reserva;
import com.uniamerica.prova2.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws Exception{
        try{
            Reserva found = reservaService.findById(id);
            if(found != null) return new ResponseEntity<>(found, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll () throws Exception{
        try{
            List<Reserva> found = reservaService.findAll();

            if(!found.isEmpty()) return new ResponseEntity<>(found, null, HttpStatus.OK);
            else return new ResponseEntity<>(found, null, HttpStatus.NO_CONTENT);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @PutMapping("/encerrar/{id}")
    public ResponseEntity<?> encerrar (@PathVariable Long id) throws Exception {
        try{
            Reserva reserva = reservaService.findById(id);
            if(reserva != null) {
                if(reserva.getStatus() == Reserva.Status.EM_ANDAMENTO) {
                    reserva.setStatus(Reserva.Status.FINALIZADO);
                    reservaService.insereReserva(reserva);
                    return new ResponseEntity<>(reserva, null, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Finalizado", null, HttpStatus.BAD_REQUEST);
                }
            }else {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @PutMapping("/excluir/{id}")
    public ResponseEntity<?> excluir (@PathVariable Long id) throws Exception {
        try{
            Reserva reserva = reservaService.findById(id);
            if(reserva != null) {
                if(reserva.getStatus() == Reserva.Status.RESERVADO) {
                    reserva.setStatus(Reserva.Status.EM_ANDAMENTO);
                    reservaService.insereReserva(reserva);
                    return new ResponseEntity<>(reserva, null, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Andamento", null, HttpStatus.BAD_REQUEST);
                }
            }else {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }



}
