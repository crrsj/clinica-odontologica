package br.com.clinica.odontologica.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.clinica.odontologica.entidade.Dentista;
import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.enums.StatusConsulta;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BuscarConsultasDto{
       private Long id;
       private LocalDate dataConsulta;
       private LocalTime horaConsulta;
       private String procedimento;
       private StatusConsulta status;
       private Paciente paciente;
       private Dentista dentista;

    
}
