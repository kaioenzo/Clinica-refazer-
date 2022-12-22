package com.kaio.api.model.dto;

import com.kaio.api.model.Especialidade;
import com.kaio.api.model.Medico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoDTO (
        @NotBlank(message = "Nome não pode ser vazio")
        String nome,
        @NotBlank
        @Email(message = "E-mail não pode ser vazio")
        String email,
        @NotBlank
                @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        //Valida outro dto dentro do dto de médico
        @Valid
        EnderecoDTO endereco,
        @NotBlank
        String telefone){
    public Medico toEntity() {
        var medico = new Medico(null, nome, email, crm, especialidade, telefone, true,endereco.toEntity(endereco));
        return medico;
    }
}
