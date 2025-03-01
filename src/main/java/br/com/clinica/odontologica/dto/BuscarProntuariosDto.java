package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Prontuario;

import java.time.LocalDate;

public record BuscarProntuariosDto(
        Long id,
        LocalDate data,
        String descricao,
        String receita,
        String atestado) {
    public BuscarProntuariosDto(Prontuario prontuario){
        this(prontuario.getId(),
                prontuario.getData(),
                prontuario.getDescricao(),
                prontuario.getReceita(),
                prontuario.getAtestado());
    }
}
