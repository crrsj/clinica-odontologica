package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Consulta;
import br.com.clinica.odontologica.entidade.Financeiro;
import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.entidade.Prontuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record CadastrarPacienteDto(
        @NotBlank(message = "não pode estar em branco")
        String nome,
        @NotBlank(message = "não pode estar em branco")
        String cpf,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
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
        String medicamentos,
        List<Consulta>consulta ,
        List<Financeiro>financeiro ,
        List<Prontuario>prontuario) {

}
