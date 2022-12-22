package com.kaio.api.controller;

import com.kaio.api.model.Paciente;
import com.kaio.api.model.dto.MedicoPutDTO;
import com.kaio.api.model.dto.PacienteDTO;
import com.kaio.api.model.dto.PacientePutDTO;
import com.kaio.api.model.dto.shallow.PacienteShallowDTO;
import com.kaio.api.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @GetMapping
    public Page<PacienteShallowDTO> listar( @PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        return service.buscarPacientes(pageable);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Paciente> cadastro(@RequestBody PacienteDTO paciente){
        return ResponseEntity.ok(service.salvar(paciente));


    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid PacientePutDTO paciente) {
        service.atualizar(paciente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }


}
