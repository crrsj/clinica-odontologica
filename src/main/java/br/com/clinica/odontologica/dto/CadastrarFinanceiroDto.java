package br.com.clinica.odontologica.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.enums.FormaPagamento;
import br.com.clinica.odontologica.enums.StatusPagamento;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CadastrarFinanceiroDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate data;
    private String descricao;
    private double valor;
    private FormaPagamento formaPagamento;
    private StatusPagamento status;
    private Paciente paciente;

    
}
