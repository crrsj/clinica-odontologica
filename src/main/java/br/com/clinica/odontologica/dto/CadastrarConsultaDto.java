package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Dentista;
import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.enums.StatusConsulta;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class CadastrarConsultaDto {


    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dataConsulta;    
    private LocalTime horaConsulta;
    @NotBlank(message = "não pode estar em branco")
    private String procedimento;
    private StatusConsulta status;
    private Paciente paciente;
    private Dentista dentista;

   
}
