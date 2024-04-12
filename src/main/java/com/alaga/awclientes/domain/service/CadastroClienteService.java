package com.alaga.awclientes.domain.service;

import com.alaga.awclientes.domain.exception.NegocioExeption;
import com.alaga.awclientes.domain.model.Cliente;
import com.alaga.awclientes.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CadastroClienteService {

    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente){
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).filter(cliente1 -> !cliente1.equals(cliente)).isPresent();
            if (emailEmUso){
                throw new NegocioExeption("Ja existe um cliente cadastrado com esse e-mail!");
            }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }
}
