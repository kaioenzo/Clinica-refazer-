package com.kaio.api.service;

import com.kaio.api.model.Paciente;
import com.kaio.api.model.dto.PacienteDTO;
import com.kaio.api.model.dto.PacientePutDTO;
import com.kaio.api.model.dto.shallow.PacienteShallowDTO;
import com.kaio.api.repository.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    private PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public Paciente salvar(PacienteDTO paciente){
        return repository.save(paciente.toEntity(paciente));
    }

    public Page<PacienteShallowDTO> buscarPacientes(Pageable pageable){
        return repository.findAll(pageable).map(PacienteShallowDTO::new);
    }

    public Paciente atualizar(PacientePutDTO paciente) {
        var entity = repository.getReferenceById(paciente.id());
        entity.atualizarInformacoes(paciente);
        return entity;
    }

    public void deletar(Long id) {
        repository.deleteById(id);

    }
}
