package com.kaio.api.model;

import com.kaio.api.model.Endereco;
import com.kaio.api.model.dto.PacientePutDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;

    public void atualizarInformacoes(PacientePutDTO paciente) {

        if (paciente.nome() != null) {
            this.nome = paciente.nome();
        }
        if (paciente.telefone() != null) {
            this.telefone = paciente.telefone();
        }
        if (paciente.endereco() != null) {
            this.endereco.atualizarInformacoes(paciente.endereco());
        }
    }
}
