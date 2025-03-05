package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Consulta;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class AtualizarDentistaDto{
        private  Long id;
        @NotBlank(message = "não pode estar em branco")
        private String nome;
        @NotBlank(message = "não pode estar em branco")
        private String cro;
        @NotBlank(message = "não pode estar em branco")
        private String especialidade;
        @NotBlank(message = "não pode estar em branco")
        private String telefone;
        @NotBlank(message = "não pode estar em branco")
        private String email;
        private List<Consulta> consulta;
}
