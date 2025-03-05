package br.com.clinica.odontologica.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.clinica.odontologica.entidade.Consulta;
import br.com.clinica.odontologica.entidade.Financeiro;
import br.com.clinica.odontologica.entidade.Prontuario;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BuscarPacientesDto{
       private Long id;
       private String nome;
       private String cpf;
       private LocalDate dataNascimento;
       private String telefone;
       private String email;
       private String historicoMedico;
       private String alergias;
       private String medicamentos;
       private List<Consulta> consulta;
       private List<Financeiro> financeiro;
       private List<Prontuario>prontuario;

    
}
