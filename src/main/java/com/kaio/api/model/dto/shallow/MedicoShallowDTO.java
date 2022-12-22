package com.kaio.api.model.dto.shallow;

import com.kaio.api.model.Especialidade;
import com.kaio.api.model.Medico;
public record MedicoShallowDTO(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {

    public MedicoShallowDTO(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
