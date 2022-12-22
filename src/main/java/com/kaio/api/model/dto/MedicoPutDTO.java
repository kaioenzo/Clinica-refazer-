package com.kaio.api.model.dto;

import com.kaio.api.model.Endereco;
import jakarta.validation.constraints.NotNull;

public record MedicoPutDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco) {
}
