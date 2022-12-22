package com.kaio.api.model;

import com.kaio.api.model.dto.MedicoPutDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private Especialidade especialidade;
    private String telefone;
    private boolean ativo = true;
    @Embedded
    private Endereco endereco;



    public void atualizarInformacoes(MedicoPutDTO medico) {
        if (medico.nome() != null) {
            this.nome = medico.nome();
        }
        if (medico.telefone() != null) {
            this.telefone = medico.telefone();
        }
        if (medico.endereco() != null) {
            this.endereco.atualizarInformacoes(medico.endereco());
        }
    }

    public void inativar() {
        this.ativo =  false;
    }
}
