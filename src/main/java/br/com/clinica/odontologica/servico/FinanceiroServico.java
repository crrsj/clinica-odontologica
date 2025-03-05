package br.com.clinica.odontologica.servico;


import br.com.clinica.odontologica.dto.AtualizarFinanceiroDto;
import br.com.clinica.odontologica.dto.BuscarFinanceiroDto;
import br.com.clinica.odontologica.dto.CadastrarFinanceiroDto;
import br.com.clinica.odontologica.entidade.Financeiro;
import br.com.clinica.odontologica.repositorio.FinanceiroRepositorio;
import br.com.clinica.odontologica.repositorio.PacienteRepositorio;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinanceiroServico {


    
    private final FinanceiroRepositorio financeiroRepositorio;
    
    private final PacienteRepositorio pacienteRepositorio;
    
    private final ModelMapper modelMapper;

    public Financeiro cadastrarFinanceiro(CadastrarFinanceiroDto cadastrarFinanceiroDto,Long pacienteId){     
      var paciente = pacienteRepositorio.findById(pacienteId).orElseThrow();
      var financeiro = modelMapper.map(cadastrarFinanceiroDto, Financeiro.class);
      financeiro.setPaciente(paciente);   
      return financeiroRepositorio.save(financeiro);

    }

    public List<BuscarFinanceiroDto> buscarFinanceiros(){
        return financeiroRepositorio.findAll().stream()
        		.map(listar -> modelMapper
        		.map(listar, BuscarFinanceiroDto.class))
        		.collect(Collectors.toList());
    }

    public Financeiro buscarPorId(Long id){
        Optional<Financeiro>buscar = financeiroRepositorio.findById(id);
        return buscar.orElseThrow();
    }

    
    public Financeiro atualizarFinanceiro(AtualizarFinanceiroDto atualizarFinanceiroDto,Long id){      
        var atualizar = modelMapper.map(atualizarFinanceiroDto, Financeiro.class);
        atualizar.setId(id);
        return financeiroRepositorio.save(atualizar);
    }

    public void excluirFinanceiro(Long id){        
        financeiroRepositorio.deleteById(id);
    }
}
