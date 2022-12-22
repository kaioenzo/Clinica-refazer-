package com.kaio.api.controller;

import com.kaio.api.model.Medico;
import com.kaio.api.model.dto.MedicoDTO;
import com.kaio.api.model.dto.MedicoPutDTO;
import com.kaio.api.model.dto.shallow.MedicoShallowDTO;
import com.kaio.api.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private MedicoService service;
    public MedicoController( MedicoService service) {
        this.service =  service;
    }

    // o objeto pageable recebido pela requisição é uma instância para criar a paginação na requisição
    @GetMapping
    public Page<MedicoShallowDTO> listar( @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return service.buscarMedicos(paginacao);
    }

    @PostMapping
    public Medico cadastrar(@RequestBody @Valid MedicoDTO medico) {
        var entity = medico.toEntity();
        return service.salvar(entity);
    }

    @PutMapping
    @Transactional
    public void atualizar (@RequestBody @Valid MedicoPutDTO medico) {
        service.atualizar(medico);
    }

//    @DeleteMapping("/{id}")
//    @Transactional
//    public void deletar (@PathVariable(name = "id") Long id){
//        service.deletar(id);
//    }

    @PutMapping("/{id}")
    @Transactional
    public void inativar(@PathVariable(name = "id") Long id){
        service.inativarMedico(id);
    }
}
