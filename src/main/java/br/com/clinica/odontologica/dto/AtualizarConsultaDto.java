package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.enums.StatusConsulta;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@NoArgsConstructor
public class AtualizarConsultaDto{
         private Long id;
         @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
         private LocalDate dataConsulta;  
         private LocalTime horaConsulta;
         @NotBlank(message = "não pode estar em branco")
         private String procedimento;
         private StatusConsulta status;
}
