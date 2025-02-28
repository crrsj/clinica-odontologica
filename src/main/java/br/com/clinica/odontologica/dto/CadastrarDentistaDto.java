package br.com.clinica.odontologica.dto;

public record CadastrarDentistaDto(
        String nome,
        String cro,
        String especialidade,
        String telefone,
        String email) {
}
