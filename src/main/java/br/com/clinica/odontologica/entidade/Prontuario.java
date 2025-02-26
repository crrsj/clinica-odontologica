package br.com.clinica.odontologica.entidade;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_prontuarios")
@Data
@NoArgsConstructor
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    private String descricao;
    private String receita;
    private String atestado;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
