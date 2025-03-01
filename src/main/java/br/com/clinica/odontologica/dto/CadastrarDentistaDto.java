package br.com.clinica.odontologica.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastrarDentistaDto(
        @NotBlank(message = "não pode estar em branco")
        String nome,
        @NotBlank(message = "não pode estar em branco")
        String cro,
        @NotBlank(message = "não pode estar em branco")
        String especialidade,
        @NotBlank(message = "não pode estar em branco")
        String telefone,
        @NotBlank(message = "não pode estar em branco")
        String email) {
}
