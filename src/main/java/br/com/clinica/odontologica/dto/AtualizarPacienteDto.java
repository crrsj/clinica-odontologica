package br.com.clinica.odontologica.dto;

import java.time.LocalDate;

public record AtualizarPacienteDto(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String telefone,
        String email,
        String endereco,
        String historicoMedico,
        String alergias,
        String medicamentos) {
}
