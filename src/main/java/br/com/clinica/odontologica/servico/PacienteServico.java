package br.com.clinica.odontologica.servico;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.clinica.odontologica.dto.AtualizarPacienteDto;
import br.com.clinica.odontologica.dto.BuscarPacientesDto;
import br.com.clinica.odontologica.dto.CadastrarPacienteDto;
import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.repositorio.PacienteRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteServico {

    
    private final PacienteRepositorio pacienteRepositorio;

    private final ModelMapper modelMapper;

    public Paciente cadastrarPaciente(CadastrarPacienteDto cadastrarPacienteDto){
        var cadastrar = modelMapper.map(cadastrarPacienteDto, Paciente.class);
        return pacienteRepositorio.save(cadastrar);
    }

    public List<BuscarPacientesDto> listarPacientes(){
      return  pacienteRepositorio.findAll().stream().map(buscarPacientes ->modelMapper.
    		  map(buscarPacientes, BuscarPacientesDto.class)).collect(Collectors.toList());
    }

    public Paciente buscarPorCpf(String cpf){
        Optional<Paciente> buscarPaciente =  pacienteRepositorio.findByCpf(cpf);
        return buscarPaciente.orElseThrow();
    }
    public List<Paciente>buscarPorNome(String nome){
        return pacienteRepositorio.findByNome(nome.trim().toUpperCase());
    }

    public Paciente buscarPorId(Long id){
        Optional<Paciente>buscar = pacienteRepositorio.findById(id);
        return buscar.orElseThrow();
    }

    public Paciente AtualizarPaciente(AtualizarPacienteDto atualizarPacienteDto,Long id){
     var atualizar = modelMapper.map(atualizarPacienteDto, Paciente.class);
     atualizar.setId(id);     
     return pacienteRepositorio.save(atualizar);
      
    }

    public void excluirPaciente(Long id){        
        buscarPorId(id);
        pacienteRepositorio.deleteById(id);

    }
}
