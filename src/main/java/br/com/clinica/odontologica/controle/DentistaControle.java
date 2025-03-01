package br.com.clinica.odontologica.controle;


import br.com.clinica.odontologica.dto.AtualizarDentistaDto;
import br.com.clinica.odontologica.dto.BuscarDentistasDto;
import br.com.clinica.odontologica.dto.CadastrarDentistaDto;
import br.com.clinica.odontologica.servico.DentistaServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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
    @Operation(summary = "Endpoint responsável por cadastrar dentista.")
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<CadastrarDentistaDto>cadastrarDentista(@RequestBody @Valid CadastrarDentistaDto cadastrarDentistaDto){
        var cadastrar = dentistaServico.cadastrarDentista(cadastrarDentistaDto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(cadastrar.getId()).toUri();
        BeanUtils.copyProperties(cadastrarDentistaDto,cadastrar);
        return ResponseEntity.created(uri).body(cadastrarDentistaDto);

    }

    @GetMapping
    @Operation(summary = "Endpoint responsável por buscar dentistas.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<List<BuscarDentistasDto>>buscarDentistas(){
        var listar = dentistaServico.buscarDentistas();
        return ResponseEntity.ok().body(listar);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint responsável por buscar dentista pelo id.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<BuscarDentistasDto>buscarPorId(@PathVariable Long id){
        var buscar = dentistaServico.buscarPorId(id);
        return ResponseEntity.ok().body(new BuscarDentistasDto(buscar));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Endpoint responsibile por atualizar dentista.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<AtualizarDentistaDto>atualizarDentista(@RequestBody @Valid AtualizarDentistaDto atualizarDentistaDto ){

        var atualizar = dentistaServico.atualizarDentista(atualizarDentistaDto);
        BeanUtils.copyProperties(atualizarDentistaDto,atualizar);
        return ResponseEntity.ok(atualizarDentistaDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint responsável por deletar dentista pelo id.")
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<Void>excluirDentista(@PathVariable Long id){
        buscarPorId(id);
        dentistaServico.excluirDentista(id);
        return ResponseEntity.noContent().build();
    }
}
