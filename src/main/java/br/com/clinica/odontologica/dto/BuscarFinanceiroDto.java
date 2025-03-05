package br.com.clinica.odontologica.dto;

import java.time.LocalDate;

import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.enums.FormaPagamento;
import br.com.clinica.odontologica.enums.StatusPagamento;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BuscarFinanceiroDto{
       private Long id;
       private LocalDate data;
       private String descricao;
       private double valor;
       private FormaPagamento formaPagamento;
       private StatusPagamento status;
       private Paciente paciente;

}
