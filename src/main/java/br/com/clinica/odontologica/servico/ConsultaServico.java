package br.com.clinica.odontologica.servico;

import br.com.clinica.odontologica.dto.AtualizarConsultaDto;
import br.com.clinica.odontologica.dto.BuscarConsultasDto;
import br.com.clinica.odontologica.dto.CadastrarConsultaDto;
import br.com.clinica.odontologica.entidade.Consulta;
import br.com.clinica.odontologica.enums.StatusConsulta;
import br.com.clinica.odontologica.repositorio.ConsultaRepositorio;
import br.com.clinica.odontologica.repositorio.PacienteRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServico {

    @Autowired
    private ConsultaRepositorio consultaRepositorio;

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    public Consulta cadastrarConsulta(CadastrarConsultaDto cadastrarConsultaDto,Long pacienteId){
        var consulta = new Consulta();
        var paciente = pacienteRepositorio.findById(pacienteId).orElseThrow();
        consulta.setPaciente(paciente);
        BeanUtils.copyProperties(cadastrarConsultaDto,consulta);
        return consultaRepositorio.save(consulta);

    }

    public List<BuscarConsultasDto>buscarConsultas(){
      return consultaRepositorio.findAll().
              stream().map(BuscarConsultasDto::new).toList();
    }

    public Consulta buscarPorId(Long id){
        Optional<Consulta>buscar = consultaRepositorio.findById(id);
        return buscar.orElseThrow();
    }

    public List<BuscarConsultasDto> buscarAgendada(){
        return consultaRepositorio.findByStatus(StatusConsulta.AGENDADA);
    }

    public List<BuscarConsultasDto>buscarCancelada(){
        return consultaRepositorio.findByStatus(StatusConsulta.CANCELADA);
    }

    public Consulta atualizarConsulta(AtualizarConsultaDto atualizarConsultaDto){
        var atualizar = new Consulta();
        BeanUtils.copyProperties(atualizarConsultaDto,atualizar);
        return consultaRepositorio.save(atualizar);

    }

    public void excluirConsulta(Long id){
        buscarPorId(id);
        consultaRepositorio.deleteById(id);
    }

}
