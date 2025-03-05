package br.com.clinica.odontologica.controle;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.clinica.odontologica.dto.AtualizarDentistaDto;
import br.com.clinica.odontologica.dto.BuscarDentistasDto;
import br.com.clinica.odontologica.dto.CadastrarDentistaDto;
import br.com.clinica.odontologica.servico.DentistaServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dentista")
@RequiredArgsConstructor
public class DentistaControle {

    private final ModelMapper modelMapper;
    private final DentistaServico dentistaServico;


    @PostMapping
    @Operation(summary = "Endpoint responsável por cadastrar dentista.")
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<CadastrarDentistaDto>cadastrarDentista(@RequestBody @Valid CadastrarDentistaDto cadastrarDentistaDto){
        var cadastrar = dentistaServico.cadastrarDentista(cadastrarDentistaDto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(cadastrar.getId()).toUri();
        
        return ResponseEntity.created(uri).body(modelMapper.map(cadastrar, CadastrarDentistaDto.class));

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
        return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarDentistasDto.class));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Endpoint responsibile por atualizar o dentista pelo id.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<AtualizarDentistaDto>atualizarDentista(@RequestBody @Valid AtualizarDentistaDto atualizarDentistaDto,
    		                                                      @PathVariable Long id){
        var atualizar = dentistaServico.atualizarDentista(atualizarDentistaDto,id);       
        return ResponseEntity.ok(modelMapper.map(atualizar, AtualizarDentistaDto.class));
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
