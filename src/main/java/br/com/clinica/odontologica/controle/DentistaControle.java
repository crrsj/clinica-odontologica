package br.com.clinica.odontologica.controle;


import br.com.clinica.odontologica.dto.AtualizarDentistaDto;
import br.com.clinica.odontologica.dto.BuscarDentistasDto;
import br.com.clinica.odontologica.dto.CadastrarDentistaDto;
import br.com.clinica.odontologica.servico.DentistaServico;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaControle {

    @Autowired
    private DentistaServico dentistaServico;


    @PostMapping
    public ResponseEntity<CadastrarDentistaDto>cadastrarDentista(@RequestBody CadastrarDentistaDto cadastrarDentistaDto){
        var cadastrar = dentistaServico.cadastrarDentista(cadastrarDentistaDto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(cadastrar.getId()).toUri();
        BeanUtils.copyProperties(cadastrarDentistaDto,cadastrar);
        return ResponseEntity.created(uri).body(cadastrarDentistaDto);

    }

    @GetMapping
    public ResponseEntity<List<BuscarDentistasDto>>buscarDentistas(){
        var listar = dentistaServico.buscarDentistas();
        return ResponseEntity.ok().body(listar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuscarDentistasDto>buscarPorId(@PathVariable Long id){
        var buscar = dentistaServico.buscarPorId(id);
        return ResponseEntity.ok().body(new BuscarDentistasDto(buscar));
    }


    @PutMapping("/{id}")
    public ResponseEntity<AtualizarDentistaDto>atualizarDentista(@RequestBody AtualizarDentistaDto atualizarDentistaDto,
                                                                 @PathVariable Long id){
        var atualizar = dentistaServico.atualizarDentista(atualizarDentistaDto,id);
        BeanUtils.copyProperties(atualizarDentistaDto,atualizar);
        return ResponseEntity.ok(atualizarDentistaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>excluirDentista(@PathVariable Long id){
        buscarPorId(id);
        dentistaServico.excluirDentista(id);
        return ResponseEntity.noContent().build();
    }
}
