package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Consulta;

import java.util.List;

public record AtualizarDentistaDto(
        Long id,
        String nome,
        String cro,
        String especialidade,
        String telefone,
        String email,
        List<Consulta> consulta) {
}
