package br.com.clinica.odontologica.servico;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.clinica.odontologica.dto.AtualizarProntuarioDto;
import br.com.clinica.odontologica.dto.BuscarProntuariosDto;
import br.com.clinica.odontologica.dto.CadastrarProntuarioDto;
import br.com.clinica.odontologica.entidade.Prontuario;
import br.com.clinica.odontologica.repositorio.PacienteRepositorio;
import br.com.clinica.odontologica.repositorio.ProntuarioRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProntuarioServico {

    
    private final ProntuarioRepositorio prontuarioRepositorio;
    
    private final PacienteRepositorio pacienteRepositorio;
    
    private final ModelMapper modelMapper;

    public Prontuario cadastrarProntuario(CadastrarProntuarioDto cadastrarProntuarioDto,Long pacienteId){        
        var paciente = pacienteRepositorio.findById(pacienteId).orElseThrow();
        var prontuario = modelMapper.map(cadastrarProntuarioDto, Prontuario.class);
        prontuario.setPaciente(paciente);        
        return prontuarioRepositorio.save(prontuario);
    }

    public List<BuscarProntuariosDto>buscarProntuarios(){
     return prontuarioRepositorio.findAll().stream().map(listar -> modelMapper
    		 .map(listar, BuscarProntuariosDto.class))
    		 .collect(Collectors.toList());
    }

    public Prontuario buscarPorId(Long id){
        Optional<Prontuario>buscar = prontuarioRepositorio.findById(id);
        return buscar.orElseThrow();
    }


    public Prontuario atualizarProntuario(AtualizarProntuarioDto atualizarProntuarioDto,Long id){     
        var atualizar = modelMapper.map(atualizarProntuarioDto, Prontuario.class);
        return prontuarioRepositorio.save(atualizar);

    }

    public void excluirProntuario(Long id){        
        prontuarioRepositorio.deleteById(id);
    }
}
