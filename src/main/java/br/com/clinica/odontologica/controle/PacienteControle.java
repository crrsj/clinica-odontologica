package br.com.clinica.odontologica.controle;

import br.com.clinica.odontologica.dto.AtualizarPacienteDto;
import br.com.clinica.odontologica.dto.BuscarPacientesDto;
import br.com.clinica.odontologica.dto.CadastrarPacienteDto;
import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.servico.PacienteServico;
import jakarta.websocket.server.PathParam;
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
       
    }

    @GetMapping
    public ResponseEntity<List<BuscarPacientesDto>>listarPacientes(){
        var listar = pacienteServico.listarPacientes();
        return ResponseEntity.ok().body(listar);
    }


    @GetMapping("/nomePaciente")
    public ResponseEntity<List<BuscarPacientesDto>>buscarPorNome(@PathParam("nome")String nome){
        var buscarNome = pacienteServico.buscarPorNome(nome);
        return ResponseEntity.ok().body(buscarNome.stream().map(BuscarPacientesDto::new).toList());
    }


    @GetMapping("/cpfPaciente")
    public ResponseEntity<?>buscarPorCpf(@PathParam("cpf")String cpf){
        var buscarCpf = pacienteServico.buscarPorCpf(cpf);
        return ResponseEntity.ok().body(buscarCpf);
    }


    @PostMapping("/{id}")
    public ResponseEntity<AtualizarPacienteDto> atualizarPaciente(@RequestBody AtualizarPacienteDto atualizarPacienteDto,
                                                                  @PathVariable Long id){
        var atualizar = pacienteServico.AtualizarPaciente(atualizarPacienteDto,id);
        BeanUtils.copyProperties(atualizarPacienteDto,atualizar);
        return ResponseEntity.ok().body(atualizarPacienteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>excluirPaciente(@PathVariable Long id){
        pacienteServico.excluirPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
