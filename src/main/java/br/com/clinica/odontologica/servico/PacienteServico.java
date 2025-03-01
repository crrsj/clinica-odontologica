package br.com.clinica.odontologica.servico;

import br.com.clinica.odontologica.dto.AtualizarPacienteDto;
import br.com.clinica.odontologica.dto.BuscarPacientesDto;
import br.com.clinica.odontologica.dto.CadastrarPacienteDto;
import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.repositorio.PacienteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServico {

    @Autowired
    private  PacienteRepositorio pacienteRepositorio;



    public Paciente cadastrarPaciente(CadastrarPacienteDto cadastrarPacienteDto){
        var cadastrar = new Paciente();
        BeanUtils.copyProperties(cadastrarPacienteDto,cadastrar);
        return pacienteRepositorio.save(cadastrar);
    }

    public List<BuscarPacientesDto> listarPacientes(){
      return  pacienteRepositorio.findAll().stream().map(BuscarPacientesDto::new).toList();
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

    public Paciente AtualizarPaciente(AtualizarPacienteDto atualizarPacienteDto){
        var atualizar  = new Paciente();
        BeanUtils.copyProperties(atualizarPacienteDto,atualizar);
        return pacienteRepositorio.save(atualizar);
    }
    public void excluirPaciente(Long id){
        buscarPorId(id);
        pacienteRepositorio.findById(id);
    }
}
