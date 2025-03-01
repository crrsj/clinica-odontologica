package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Paciente;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AtualizarProntuarioDto(
        Long id,
        LocalDate data,
        @NotBlank(message = "não pode estar em branco")
        String descricao,
        @NotBlank(message = "não pode estar em branco")
        String receita,
        @NotBlank(message = "não pode estar em branco")
        String atestado,
        Paciente paciente) {
}
