package br.com.clinica.odontologica.dto;

import org.springframework.validation.FieldError;

public record ValidarCamposDto(
        String campo,
        String mensagem) {

    public ValidarCamposDto(FieldError erro){
        this(
                erro.getField(),
                erro.getDefaultMessage());
    }

}
