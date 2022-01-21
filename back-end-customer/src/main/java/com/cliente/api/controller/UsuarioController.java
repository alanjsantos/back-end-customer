package com.cliente.api.controller;

import com.cliente.api.model.Usuario;
import com.cliente.api.model.dto.UsuarioDTO;
import com.cliente.api.service.UsuarioService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = service.save(modelMapper.map(usuarioDTO, Usuario.class));

        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(usuario, UsuarioDTO.class));
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UsuarioDTO> findByEmail(@Valid @PathVariable String email) {
        Usuario usuario = service.findByEmail(email);
        UsuarioDTO body = modelMapper.map(usuario, UsuarioDTO.class);

        return ResponseEntity.ok(body);
    }
}
