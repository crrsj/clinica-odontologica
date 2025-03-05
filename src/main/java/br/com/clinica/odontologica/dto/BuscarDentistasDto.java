package br.com.clinica.odontologica.dto;

import java.util.List;

import br.com.clinica.odontologica.entidade.Consulta;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuscarDentistasDto{
      private Long id;
      private String nome;
      private String cro;
      private String especialidade;
      private String telefone;
      private String email;
      private List<Consulta>consulta;
    

}
