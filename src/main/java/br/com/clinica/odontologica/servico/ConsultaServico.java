package br.com.clinica.odontologica.servico;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import br.com.clinica.odontologica.dto.AtualizarConsultaDto;
import br.com.clinica.odontologica.dto.BuscarConsultasDto;
import br.com.clinica.odontologica.dto.CadastrarConsultaDto;
import br.com.clinica.odontologica.entidade.Consulta;
import br.com.clinica.odontologica.enums.StatusConsulta;
import br.com.clinica.odontologica.repositorio.ConsultaRepositorio;
import br.com.clinica.odontologica.repositorio.PacienteRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsultaServico {
	
	
   private final ModelMapper modelMapper;

    
    private final  ConsultaRepositorio consultaRepositorio;

    
    private final PacienteRepositorio pacienteRepositorio;

    public Consulta cadastrarConsulta(CadastrarConsultaDto cadastrarConsultaDto,Long pacienteId){    	
        var paciente = pacienteRepositorio.findById(pacienteId).orElseThrow();
        var consulta = modelMapper.map(cadastrarConsultaDto, Consulta.class);
        consulta.setPaciente(paciente);       
        return consultaRepositorio.save(consulta);

    }

    public List<BuscarConsultasDto>buscarConsultas(){
      return consultaRepositorio.findAll().
              stream().map(buscaracaonsultas -> modelMapper.map(buscaracaonsultas, BuscarConsultasDto.class)).toList();
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

    public Consulta atualizarConsulta(AtualizarConsultaDto atualizarConsultaDto,Long id){
     var  atualizar = modelMapper.map(atualizarConsultaDto, Consulta.class);
     buscarPorId(id);
        return consultaRepositorio.save(atualizar);

    }

    public void excluirConsulta(Long id){  
        consultaRepositorio.deleteById(id);
    }

}
