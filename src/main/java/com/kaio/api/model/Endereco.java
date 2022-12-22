package com.kaio.api.model;

import com.kaio.api.model.dto.EnderecoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public void atualizarInformacoes(EnderecoDTO endereco) {
        if (endereco.logradouro() != null){this.logradouro = endereco.logradouro();}
        if (endereco.numero() != null){this.numero = endereco.numero();}
        if (endereco.complemento() != null){this.complemento = endereco.complemento();}
        if (endereco.bairro() != null){this.complemento =  endereco.bairro();}
        if (endereco.cidade() != null){this.cidade = endereco.cidade();}
        if (endereco.uf() != null){this.uf = endereco.uf();}
        if (endereco.cep() != null){this.cep = endereco.cep();}

    }
}
