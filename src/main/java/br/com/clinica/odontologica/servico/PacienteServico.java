package br.com.clinica.odontologica.servico;

import br.com.clinica.odontologica.dto.CadastrarPacienteDto;
import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.repositorio.PacienteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServico {

    @Autowired
    private  PacienteRepositorio pacienteRepositorio;



    public Paciente cadastrarPaciente(CadastrarPacienteDto cadastrarPacienteDto){
        var cadastrar = new Paciente();
        BeanUtils.copyProperties(cadastrarPacienteDto,cadastrar);
        return pacienteRepositorio.save(cadastrar);
    }

    public List<Paciente> listarPacientes(){
      return  pacienteRepositorio.findAll();
    }
}
