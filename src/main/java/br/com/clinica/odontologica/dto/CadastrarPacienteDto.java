package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Consulta;
import br.com.clinica.odontologica.entidade.Financeiro;
import br.com.clinica.odontologica.entidade.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record CadastrarPacienteDto(
        String nome,
        String cpf,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,
        String telefone,
        String endereco,
        String email,
        String historicoMedico,
        String alergias,
        String medicamentos,
        List<Consulta>consulta ,
        List<Financeiro>financeiro ) {

}
