package com.cliente.api.service;

import com.cliente.api.model.Usuario;
import com.cliente.api.model.dto.UsuarioDTO;
import com.cliente.api.repository.UsuarioRepository;
import com.cliente.api.service.exception.ObjectNotFoundException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public Usuario save(Usuario usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return repository.save(usuario);
    }

    public Usuario findByEmail(String email) {
        Optional<Usuario> usuario = repository.findByEmail(email);

        return usuario.orElseThrow(() -> new ObjectNotFoundException("O Usuario com este Email não existe "));
    }

}
