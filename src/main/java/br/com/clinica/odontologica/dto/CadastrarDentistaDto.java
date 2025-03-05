package br.com.clinica.odontologica.dto;

import jakarta.validation.constraints.NotBlank;

public class CadastrarDentistaDto{
        @NotBlank(message = "não pode estar em branco")
        private String nome;
        @NotBlank(message = "não pode estar em branco")
        private String cro;
        @NotBlank(message = "não pode estar em branco")
        private String especialidade;
        @NotBlank(message = "não pode estar em branco")
        private String telefone;
        @NotBlank(message = "não pode estar em branco")
        private String email;
}
