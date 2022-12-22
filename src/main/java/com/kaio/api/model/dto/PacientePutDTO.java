package com.kaio.api.model.dto;

import jakarta.validation.constraints.NotNull;

public record PacientePutDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco
        ) {
}
