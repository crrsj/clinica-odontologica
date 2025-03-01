package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Paciente;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AtualizarProntuarioDto(
        Long id,
        JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
        LocalDate data,
        @NotBlank(message = "não pode estar em branco")
        String descricao,
        @NotBlank(message = "não pode estar em branco")
        String receita,
        @NotBlank(message = "não pode estar em branco")
        String atestado,
        Paciente paciente) {
}
