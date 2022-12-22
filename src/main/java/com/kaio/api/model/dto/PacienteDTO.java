package com.kaio.api.model.dto;

import com.kaio.api.model.Endereco;
import com.kaio.api.model.Paciente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PacienteDTO (
        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        @NotBlank @Max(11)
        String cpf,
        @NotNull
        String telefone,
        @NotNull
        Endereco endereco) {
        public Paciente toEntity(PacienteDTO paciente) {
                return new Paciente(null, paciente.nome, paciente.email,paciente.telefone(), paciente.cpf(),
                        paciente.endereco());
        }
}
