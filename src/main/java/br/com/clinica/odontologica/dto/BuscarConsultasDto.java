package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Consulta;
import br.com.clinica.odontologica.entidade.Dentista;
import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.enums.StatusConsulta;

import java.time.LocalDate;
import java.time.LocalTime;

public record BuscarConsultasDto(
        Long id,
        LocalDate dataConsulta,
        LocalTime horaConsulta,
        String procedimento,
        StatusConsulta status,
        Paciente paciente,
        Dentista dentista) {

    public BuscarConsultasDto(Consulta consulta){
        this(
                consulta.getId(),
                consulta.getDataConsulta(),
                consulta.getHoraConsulta(),
                consulta.getProcedimento(),
                consulta.getStatus(),
                consulta.getPaciente(),
                consulta.getDentista());
    }
}
