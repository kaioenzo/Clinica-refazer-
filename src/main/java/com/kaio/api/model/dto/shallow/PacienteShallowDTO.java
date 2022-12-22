package com.kaio.api.model.dto.shallow;


import com.kaio.api.model.Paciente;

public record PacienteShallowDTO(Long id, String nome, String email, String cpf) {
    public PacienteShallowDTO(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }

}
