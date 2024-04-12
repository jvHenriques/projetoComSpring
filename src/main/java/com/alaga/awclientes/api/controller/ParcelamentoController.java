package com.alaga.awclientes.api.controller;

import com.alaga.awclientes.domain.exception.NegocioExeption;
import com.alaga.awclientes.domain.model.Parcelamento;
import com.alaga.awclientes.domain.repository.ParcelamentoRepository;
import com.alaga.awclientes.domain.service.ParcelamentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {
    private final ParcelamentoRepository parcelamentoRepository;
    private final ParcelamentoService parcelamentoService;

    @GetMapping
    public List<Parcelamento> listar(){
        return parcelamentoRepository.findAll();
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<Parcelamento> buscar(@PathVariable Long parcelamentoId){
        return parcelamentoRepository.findById(parcelamentoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Parcelamento cadastrar(@RequestBody Parcelamento parcelamento){
        return parcelamentoService.cadastrar(parcelamento);
    }

    @ExceptionHandler(NegocioExeption.class)
    public ResponseEntity<String> capturar(NegocioExeption e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
