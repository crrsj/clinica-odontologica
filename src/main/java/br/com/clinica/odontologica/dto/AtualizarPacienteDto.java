package br.com.clinica.odontologica.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AtualizarPacienteDto(
        Long id,
        @NotBlank(message = "não pode estar em branco")
        String nome,
        @NotBlank(message = "não pode estar em branco")
        String cpf,
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,
        @NotBlank(message = "não pode estar em branco")
        String telefone,
        @NotBlank(message = "não pode estar em branco")
        String email,
        @NotBlank(message = "não pode estar em branco")
        String historicoMedico,
        @NotBlank(message = "não pode estar em branco")
        String alergias,
        @NotBlank(message = "não pode estar em branco")
        String medicamentos) {
}
