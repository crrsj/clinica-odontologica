package br.com.clinica.odontologica.servico;


import br.com.clinica.odontologica.dto.AtualizarFinanceiroDto;
import br.com.clinica.odontologica.dto.BuscarFinanceiroDto;
import br.com.clinica.odontologica.dto.CadastrarFinanceiroDto;
import br.com.clinica.odontologica.entidade.Financeiro;
import br.com.clinica.odontologica.repositorio.FinanceiroRepositorio;
import br.com.clinica.odontologica.repositorio.PacienteRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinanceiroServico {


    @Autowired
    private FinanceiroRepositorio financeiroRepositorio;
    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    public Financeiro cadastrarFinanceiro(CadastrarFinanceiroDto cadastrarFinanceiroDto,Long pacienteId){
      var financeiro = new Financeiro();
      var paciente = pacienteRepositorio.findById(pacienteId).orElseThrow();
      financeiro.setPaciente(paciente);
       BeanUtils.copyProperties(cadastrarFinanceiroDto,financeiro);
      return financeiroRepositorio.save(financeiro);

    }

    public List<BuscarFinanceiroDto> buscarFinanceiros(){
        return financeiroRepositorio.findAll().stream().map(BuscarFinanceiroDto::new).toList();
    }

    public Financeiro buscarPorId(Long id){
        Optional<Financeiro>buscar = financeiroRepositorio.findById(id);
        return buscar.orElseThrow();
    }

    public Financeiro atualizarFinanceiro(AtualizarFinanceiroDto atualizarFinanceiroDto){
        var atualizar = new Financeiro();
        BeanUtils.copyProperties(atualizarFinanceiroDto,atualizar);
        return financeiroRepositorio.save(atualizar);
    }

    public void excluirFinanceiro(Long id){
        buscarPorId(id);
        financeiroRepositorio.deleteById(id);
    }
}
