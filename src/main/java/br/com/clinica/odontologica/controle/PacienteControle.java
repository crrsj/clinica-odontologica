package br.com.clinica.odontologica.controle;

import br.com.clinica.odontologica.dto.CadastrarPacienteDto;
import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.servico.PacienteServico;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteControle {

    @Autowired
    private  PacienteServico pacienteServico;


    @PostMapping
    public ResponseEntity<CadastrarPacienteDto>cadastrarPaciente(@RequestBody CadastrarPacienteDto dto){
        var cadastrar = pacienteServico.cadastrarPaciente(dto);
        BeanUtils.copyProperties(dto,cadastrar);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cadastrar.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
        //return ResponseEntity.created(uri).body(new CadastrarPacienteDto(cadastrar));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>>listarPacientes(){
        var listar = pacienteServico.listarPacientes();
        return ResponseEntity.ok().body(listar);
    }
}
