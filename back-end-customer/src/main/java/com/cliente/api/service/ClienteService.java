package com.cliente.api.service;

import com.cliente.api.model.Cliente;
import com.cliente.api.repository.ClienteRepository;
import com.cliente.api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente save (Cliente cliente) {
        return repository.save(cliente);
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente findById(Long id) throws Exception {
        Optional<Cliente> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Não existe Cliente na base de dados com este ID"));
    }

    public Cliente findByEmail (String email) throws Exception {
        Optional<Cliente> obj = repository.findByEmail(email);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Não existe Cliente na base de dados com este Email"));
    }

    public void delete (Long id) throws Exception {
        Optional<Cliente> obj = Optional.ofNullable(findById(id));

        if (obj.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new ObjectNotFoundException("Não eixste Clietne na base de Dados com este ID.");
        }
    }
    
    public Cliente update(Cliente cliente) throws Exception {
        findById(cliente.getId());

        cliente =  repository.save(cliente);

        return cliente;
    }

}
