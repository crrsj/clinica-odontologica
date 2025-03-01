package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.enums.StatusConsulta;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalTime;

public record AtualizarConsultaDto(
        Long id,
        LocalDate dataConsulta,
        @NotBlank(message = "não pode estar em branco")
        LocalTime horaConsulta,
        @NotBlank(message = "não pode estar em branco")
        String procedimento,
        StatusConsulta status) {
}
