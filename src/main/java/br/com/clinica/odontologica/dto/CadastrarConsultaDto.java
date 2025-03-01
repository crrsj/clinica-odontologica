package br.com.clinica.odontologica.dto;

import br.com.clinica.odontologica.entidade.Dentista;
import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.enums.StatusConsulta;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalTime;


public class CadastrarConsultaDto {


    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dataConsulta;
    @NotBlank(message = "não pode estar em branco")
    private LocalTime horaConsulta;
    @NotBlank(message = "não pode estar em branco")
    private String procedimento;
    private StatusConsulta status;
    private Paciente paciente;
    private Dentista dentista;

    public CadastrarConsultaDto() {

    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalTime getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(LocalTime horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }
}
