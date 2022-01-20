package com.cliente.api.controller;

import com.cliente.api.model.Cliente;
import com.cliente.api.model.dto.ClienteDTO;
import com.cliente.api.service.ClienteService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ClienteDTO> save (@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = service.save(modelMapper.map(clienteDTO, Cliente.class));

        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(cliente, ClienteDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> body =
                service.findAll().stream()
                        .map(entity -> modelMapper.map(entity, ClienteDTO.class))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(body);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) throws Exception {
        Cliente cliente = service.findById(id);
        ClienteDTO body = modelMapper.map(cliente, ClienteDTO.class);
        return ResponseEntity.ok().body(body);
    }

    @PutMapping("{id}")
    public ResponseEntity<ClienteDTO> update(@RequestBody ClienteDTO dto,
                                             @PathVariable Long id) throws Exception {
        dto.setId(id);
        Cliente cliente = service.update(modelMapper.map(dto, Cliente.class));
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(cliente, ClienteDTO.class));
    }

    @GetMapping("email/{email}")
    public ResponseEntity<ClienteDTO> findByEmail(@PathVariable String email) throws Exception {

        Cliente cliente = service.findByEmail(email);
        ClienteDTO body = modelMapper.map(cliente, ClienteDTO.class);

        return ResponseEntity.ok(body);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ClienteDTO> deleteById (@PathVariable Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
