package br.com.clinica.odontologica.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.clinica.odontologica.entidade.Consulta;
import br.com.clinica.odontologica.entidade.Financeiro;
import br.com.clinica.odontologica.entidade.Prontuario;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CadastrarPacienteDto{
        @NotBlank(message = "não pode estar em branco")
        private String nome;
        @NotBlank(message = "não pode estar em branco")
        private String cpf;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        private LocalDate dataNascimento;
        @NotBlank(message = "não pode estar em branco")
        private String telefone;
        @NotBlank(message = "não pode estar em branco")
        private String email;
        @NotBlank(message = "não pode estar em branco")
        private String historicoMedico;
        @NotBlank(message = "não pode estar em branco")
        private String alergias;
        @NotBlank(message = "não pode estar em branco")
        private String medicamentos;
        private List<Consulta>consulta;
        private List<Financeiro>financeiro;
        private List<Prontuario>prontuario;

}
