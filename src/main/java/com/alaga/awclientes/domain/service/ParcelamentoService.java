package com.alaga.awclientes.domain.service;

import com.alaga.awclientes.domain.exception.NegocioExeption;
import com.alaga.awclientes.domain.model.Cliente;
import com.alaga.awclientes.domain.model.Parcelamento;
import com.alaga.awclientes.domain.repository.ClienteRepository;
import com.alaga.awclientes.domain.repository.ParcelamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ParcelamentoService {

    private final ParcelamentoRepository parcelamentoRepository;
    private final ClienteRepository clienteRepository;

    @Transactional
    public Parcelamento cadastrar(Parcelamento novoParcelamento) {
        if (novoParcelamento.getId() != null) {
            throw new NegocioExeption("Parcelamento a ser criado não deve possuir um código");
        }

        Cliente cliente = clienteRepository.findById(novoParcelamento.getCliente().getId()).orElseThrow(() -> new NegocioExeption("Cliente não encontrado"));

        novoParcelamento.setCliente(cliente);
        novoParcelamento.setDataCriacao(LocalDateTime.now());


        return parcelamentoRepository.save(novoParcelamento);
    }
}
