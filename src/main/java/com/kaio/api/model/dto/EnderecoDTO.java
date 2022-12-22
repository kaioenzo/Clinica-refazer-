package com.kaio.api.model.dto;

import com.kaio.api.model.Endereco;
import com.kaio.api.model.Medico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        String complemento,
        String numero) {

    public Endereco toEntity(EnderecoDTO endereco){
        return new Endereco( endereco.logradouro, endereco.numero, endereco.complemento, endereco.bairro,
                endereco.cidade, endereco.uf, endereco.cep);
    }
}
