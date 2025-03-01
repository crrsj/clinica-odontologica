package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Consulta;
import br.com.clinica.odontologica.entidade.Financeiro;
import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.entidade.Prontuario;

import java.time.LocalDate;
import java.util.List;

public record BuscarPacientesDto(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String telefone,
        String email,
        String historicoMedico,
        String alergias,
        String medicamentos,
        List<Consulta> consulta,
        List<Financeiro> financeiro,
        List<Prontuario>prontuario) {

    public BuscarPacientesDto(Paciente paciente){
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getDataNascimento(),
                paciente.getTelefone(),
                paciente.getEmail(),
                paciente.getHistoricoMedico(),
                paciente.getAlergias(),
                paciente.getMedicamentos(),
                paciente.getConsulta(),
                paciente.getFinanceiro(),
                paciente.getProntuario());

    }
}
