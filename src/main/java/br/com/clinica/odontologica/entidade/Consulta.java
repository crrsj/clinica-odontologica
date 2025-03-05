package br.com.clinica.odontologica.entidade;

import br.com.clinica.odontologica.enums.StatusConsulta;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_consultas")
@Data
@NoArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dataConsulta;
    private LocalTime horaConsulta;
    private String procedimento;
    @Enumerated(EnumType.STRING)
    private StatusConsulta status;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "dentista_id")
    private Dentista dentista;

   
}
