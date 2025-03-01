package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Financeiro;
import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.enums.FormaPagamento;
import br.com.clinica.odontologica.enums.StatusPagamento;

import java.time.LocalDate;

public record BuscarFinanceiroDto(
        Long id,
        LocalDate data,
        String descricao,
        double valor,
        FormaPagamento formaPagamento,
        StatusPagamento status,
        Paciente paciente) {

    public BuscarFinanceiroDto(Financeiro financeiro){
        this(
                financeiro.getId(),
                financeiro.getData(),
                financeiro.getDescricao(),
                financeiro.getValor(),
                financeiro.getFormaPagamento(),
                financeiro.getStatus(),
                financeiro.getPaciente());
    }
}
