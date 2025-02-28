package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.enums.StatusConsulta;

import java.time.LocalDate;
import java.time.LocalTime;

public record AtualizarConsultaDto(
        Long id,
        LocalDate dataConsulta,
        LocalTime horaConsulta,
        String procedimento,
        StatusConsulta status) {
}
