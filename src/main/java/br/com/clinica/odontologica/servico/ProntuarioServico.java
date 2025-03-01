package br.com.clinica.odontologica.servico;


import br.com.clinica.odontologica.dto.AtualizarProntuarioDto;
import br.com.clinica.odontologica.dto.BuscarProntuariosDto;
import br.com.clinica.odontologica.dto.CadastrarProntuarioDto;
import br.com.clinica.odontologica.entidade.Prontuario;
import br.com.clinica.odontologica.repositorio.PacienteRepositorio;
import br.com.clinica.odontologica.repositorio.ProntuarioRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProntuarioServico {

    @Autowired
    private ProntuarioRepositorio prontuarioRepositorio;

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    public Prontuario cadastrarProntuario(CadastrarProntuarioDto cadastrarProntuarioDto,Long pacienteId){
        var prontuario = new Prontuario();
        var paciente = pacienteRepositorio.findById(pacienteId).orElseThrow();
        prontuario.setPaciente(paciente);
        BeanUtils.copyProperties(cadastrarProntuarioDto,prontuario);
        return prontuarioRepositorio.save(prontuario);
    }

    public List<BuscarProntuariosDto>buscarProntuarios(){
     return prontuarioRepositorio.findAll().stream().map(BuscarProntuariosDto::new).toList();
    }

    public Prontuario buscarPorId(Long id){
        Optional<Prontuario>buscar = prontuarioRepositorio.findById(id);
        return buscar.orElseThrow();
    }

    public Prontuario atualizarProntuario(AtualizarProntuarioDto atualizarProntuarioDto){
        var atualizar = new Prontuario();

        BeanUtils.copyProperties(atualizarProntuarioDto,atualizar);
        return prontuarioRepositorio.save(atualizar);

    }

    public void excluirProntuario(Long id){
        buscarPorId(id);
        prontuarioRepositorio.deleteById(id);
    }
}
