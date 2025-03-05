package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Paciente;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@NoArgsConstructor
public class AtualizarProntuarioDto{
        private  Long id;    
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
        private LocalDate data;
        @NotBlank(message = "não pode estar em branco")
        private String descricao;
        @NotBlank(message = "não pode estar em branco")
        private String receita;
        @NotBlank(message = "não pode estar em branco")
        private String atestado;
        private Paciente paciente;
        
        
}
