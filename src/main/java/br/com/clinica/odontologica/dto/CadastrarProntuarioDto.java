package br.com.clinica.odontologica.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.clinica.odontologica.entidade.Paciente;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CadastrarProntuarioDto {

    
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private String data;
    private String descricao;
    private String receita;
    private String atestado;
    private Paciente paciente;

    
}
