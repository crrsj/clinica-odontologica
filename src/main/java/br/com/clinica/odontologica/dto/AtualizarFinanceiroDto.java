package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.enums.FormaPagamento;
import br.com.clinica.odontologica.enums.StatusPagamento;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;



@Data
@NoArgsConstructor
public class AtualizarFinanceiroDto{
		
        private Long id;
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
        private LocalDate data;        
        private String descricao;
        private double valor;
        private FormaPagamento formaPagamento;
        private StatusPagamento status;
        private Paciente paciente;

}
