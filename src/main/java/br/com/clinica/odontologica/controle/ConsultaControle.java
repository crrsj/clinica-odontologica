package br.com.clinica.odontologica.controle;

import br.com.clinica.odontologica.dto.AtualizarConsultaDto;
import br.com.clinica.odontologica.dto.BuscarConsultasDto;
import br.com.clinica.odontologica.dto.CadastrarConsultaDto;
import br.com.clinica.odontologica.entidade.Consulta;
import br.com.clinica.odontologica.servico.ConsultaServico;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaControle {

    @Autowired
    public ConsultaServico consultaServico;


    @PostMapping("{pacienteId}")
    public ResponseEntity<CadastrarConsultaDto>cadastrarConsulta(@RequestBody CadastrarConsultaDto cadastrarConsultaDto,
                                                                 @PathVariable Long pacienteId){
        var cadastrar = consultaServico.cadastrarConsulta(cadastrarConsultaDto,pacienteId);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cadastrar.getId()).toUri();
        BeanUtils.copyProperties(cadastrarConsultaDto,cadastrar);
        return ResponseEntity.created(uri).body(cadastrarConsultaDto);

    }

    @GetMapping
    public ResponseEntity<List<BuscarConsultasDto>>listarConsultas(){
        var listar = consultaServico.buscarConsultas();
        return ResponseEntity.ok().body(listar);
    }

    @GetMapping("/agendada")
    public ResponseEntity<List<BuscarConsultasDto>>buscarAgendada(){
        var buscar = consultaServico.buscarAgendada();
        return ResponseEntity.ok().body(buscar);
    }

    @GetMapping("/cancelada")
    public ResponseEntity<List<BuscarConsultasDto>>buscarCancelada(){
        var buscar = consultaServico.buscarCancelada();
        return ResponseEntity.ok().body(buscar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtualizarConsultaDto>atualizarConsulta(@RequestBody AtualizarConsultaDto atualizarConsultaDto,
                                                                 @PathVariable Long id){
        var atualizar = consultaServico.atualizarConsulta(atualizarConsultaDto,id);
        BeanUtils.copyProperties(atualizarConsultaDto,atualizar);
        return ResponseEntity.ok().body(atualizarConsultaDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<BuscarConsultasDto> buscarPorId(@PathVariable Long id){
        var buscar = consultaServico.buscarPorId(id);
        return ResponseEntity.ok().body(new BuscarConsultasDto(buscar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>excluirConsulta(@PathVariable Long id){
        consultaServico.excluirConsulta(id);
        return ResponseEntity.noContent().build();
    }
}
