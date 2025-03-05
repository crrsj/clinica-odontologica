package br.com.clinica.odontologica.entidade;


import br.com.clinica.odontologica.enums.FormaPagamento;
import br.com.clinica.odontologica.enums.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_financeiros")
@Data
@NoArgsConstructor
public class Financeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate data;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    private double valor;
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
    @ManyToOne
    private Paciente paciente;

  
}
