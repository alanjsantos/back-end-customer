package com.cliente.api.model.dto;

import com.cliente.api.security.enums.PerfilEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    @NotEmpty(message = "Campo obrigatorio")
    private String email;

    @Enumerated(EnumType.STRING)
    private PerfilEnum perfil;
}
