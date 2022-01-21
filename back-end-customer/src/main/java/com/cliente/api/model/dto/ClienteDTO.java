package com.cliente.api.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTO {
    private Long id;

    @NotEmpty(message = "Campo obrigatorio")
    private String nome;

    @NotEmpty(message = "Campo obrigatorio")
    private String foneMovel;


    @NotEmpty(message = "Campo obrigatorio")
    private String foneFixo;


    @NotEmpty(message = "Campo obrigatorio")
    private String email;


    @NotEmpty(message = "Campo obrigatorio")
    private String cpf;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
}
