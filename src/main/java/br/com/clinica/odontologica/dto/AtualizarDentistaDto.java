package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Consulta;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record AtualizarDentistaDto(
        Long id,
        @NotBlank(message = "não pode estar em branco")
        String nome,
        @NotBlank(message = "não pode estar em branco")
        String cro,
        @NotBlank(message = "não pode estar em branco")
        String especialidade,
        @NotBlank(message = "não pode estar em branco")
        String telefone,
        @NotBlank(message = "não pode estar em branco")
        String email,
        List<Consulta> consulta) {
}
