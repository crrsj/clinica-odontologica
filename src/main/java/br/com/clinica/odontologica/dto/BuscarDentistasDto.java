package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Consulta;
import br.com.clinica.odontologica.entidade.Dentista;

import java.util.List;

public record BuscarDentistasDto(
        Long id,
        String nome,
        String cro,
        String especialidade,
        String telefone,
        String email,
        List<Consulta>consulta) {
    public BuscarDentistasDto(Dentista dentista){
        this(
                dentista.getId(),
                dentista.getNome(),
                dentista.getCro(),
                dentista.getEspecialidade(),
                dentista.getTelefone(),
                dentista.getEmail(),
                dentista.getConsulta());
    }

}
