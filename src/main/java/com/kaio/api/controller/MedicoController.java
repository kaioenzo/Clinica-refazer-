package com.kaio.api.controller;

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
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private MedicoService service;
    public MedicoController( MedicoService service) {
        this.service =  service;
    }

    // o objeto pageable recebido pela requisição é uma instância para criar a paginação na requisição
    @GetMapping
    public ResponseEntity<Page<MedicoShallowDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var pageMedicos =  service.buscarMedicos(paginacao);
        return ResponseEntity.ok(pageMedicos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MedicoShallowDTO> listarComId(@PathVariable("id") Long id){
        var pageMedicos =  service.buscarMedico(id);
        return ResponseEntity.ok(pageMedicos);
    }

    @PostMapping
    public ResponseEntity<MedicoShallowDTO> cadastrar(@RequestBody @Valid MedicoDTO medico,
                                                      UriComponentsBuilder uriBuilder) {
        var entity = medico.toEntity();
        var medicoCriado = service.salvar(entity);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medicoCriado.getId()).toUri();
        return ResponseEntity.created(uri).body(new MedicoShallowDTO(medicoCriado));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicoShallowDTO> atualizar (@RequestBody @Valid MedicoPutDTO medico) {
        var medicoAtualizado = service.atualizar(medico);
        return ResponseEntity.ok(new MedicoShallowDTO(medicoAtualizado));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar (@PathVariable(name = "id") Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MedicoShallowDTO> inativar(@PathVariable(name = "id") Long id){

        var entity = service.inativarMedico(id);
        return ResponseEntity.ok(new MedicoShallowDTO(entity));
    }
}
