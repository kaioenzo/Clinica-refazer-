package com.kaio.api.controller;

import com.kaio.api.model.dto.PacienteDTO;
import com.kaio.api.model.dto.PacientePutDTO;
import com.kaio.api.model.dto.shallow.PacienteShallowDTO;
import com.kaio.api.repository.PacienteRepository;
import com.kaio.api.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;
    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteService service,
                              PacienteRepository pacienteRepository
    ) {
        this.service = service;
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping
    public ResponseEntity<Page<PacienteShallowDTO>> listar( @PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        var pacientePage = service.buscarPacientes(pageable);
        return ResponseEntity.ok(pacientePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteShallowDTO> listarPaciente(@PathVariable Long id){
        var paciente = pacienteRepository.findById(id);
        if(paciente.isPresent()){
            return ResponseEntity.ok(new PacienteShallowDTO(paciente.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteShallowDTO> cadastro(@RequestBody PacienteDTO paciente,
                                                       UriComponentsBuilder uriBuilder){
        var entity = service.salvar(paciente);
        var uri = uriBuilder.path("/medicos/${id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(new PacienteShallowDTO(entity));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PacienteShallowDTO> atualizar(@RequestBody @Valid PacientePutDTO paciente) {
        var entity = service.atualizar(paciente);
        return ResponseEntity.ok(new PacienteShallowDTO(entity));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
