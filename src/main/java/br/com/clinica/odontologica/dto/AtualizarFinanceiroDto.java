package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.enums.FormaPagamento;
import br.com.clinica.odontologica.enums.StatusPagamento;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AtualizarFinanceiroDto(
        Long id,
        LocalDate data,
        String descricao,
        double valor,
        FormaPagamento formaPagamento,
         StatusPagamento status, Paciente paciente) {
}
