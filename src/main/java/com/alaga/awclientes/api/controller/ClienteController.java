package com.alaga.awclientes.api.controller;

import com.alaga.awclientes.domain.exception.NegocioExeption;
import com.alaga.awclientes.domain.model.Cliente;
import com.alaga.awclientes.domain.repository.ClienteRepository;
import com.alaga.awclientes.domain.service.CadastroClienteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

//    @Autowired
    private final CadastroClienteService cadastroClienteService;
    private final ClienteRepository clienteRepository;

//    public ClienteController(ClienteRepository clienteRepository) {
//        this.clienteRepository = clienteRepository;
//
//        @PersistenceContext
//    private EntityManager manager;


    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
        return cadastroClienteService.salvar(cliente);
//        return clienteRepository.save(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente){
        if (!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);
        cliente = cadastroClienteService.salvar(cliente);
        clienteRepository.save(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> excluir(@PathVariable long clienteId){
        if (!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }

        clienteRepository.deleteById(clienteId);
        return ResponseEntity.noContent().build();

    }

//    @GetMapping("/clientes")
//    public List<Cliente> listar() {
//        return manager.createQuery("from Clientes", Cliente.class).getResultList();
//    }

    @PostMapping("/clientes")
    public Cliente criar(@RequestBody Cliente cliente) {

         return clienteRepository.save(cliente);
    }

    @ExceptionHandler(NegocioExeption.class)
    public ResponseEntity<String> capturar(NegocioExeption e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


//    @GetMapping("/clientes")
//    public List<Cliente> listar(){
//        return clienteRepository.findAll();
//        Cliente cliente1 = new Cliente(1L, "João da silva", "joao@gmail.com", "34 9999-0987");
//        Cliente cliente2 = new Cliente(2L, "Maria", "maria@gmail.com", "34 7654-2345");
//        return Arrays.asList(cliente1,cliente2);

}
