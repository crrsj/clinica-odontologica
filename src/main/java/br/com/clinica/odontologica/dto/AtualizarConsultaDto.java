package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.enums.StatusConsulta;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record AtualizarConsultaDto(
        Long id,
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
        LocalDate dataConsulta,        
        LocalTime horaConsulta,
        @NotBlank(message = "não pode estar em branco")
        String procedimento,
        StatusConsulta status) {
}
