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

import br.com.clinica.odontologica.dto.AtualizarProntuarioDto;
import br.com.clinica.odontologica.dto.BuscarProntuariosDto;
import br.com.clinica.odontologica.dto.CadastrarProntuarioDto;
import br.com.clinica.odontologica.servico.ProntuarioServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/prontuario")
@RequiredArgsConstructor
public class ProntuarioControle {

    private final ModelMapper modelMapper;
    private final ProntuarioServico prontuarioServico;

    @PostMapping("/{pacienteId}")
    @Operation(summary = "Endpoint responsável por cadastrar prontuário pelo id do paciente.")
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<CadastrarProntuarioDto>cadastrarProntuario(@RequestBody CadastrarProntuarioDto cadastrarProntuarioDto,
                                                                   @PathVariable Long pacienteId){
        var cadastrar = prontuarioServico.cadastrarProntuario(cadastrarProntuarioDto,pacienteId);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(cadastrar.getId()).toUri();       
        return ResponseEntity.created(uri).body(modelMapper.map(cadastrar, CadastrarProntuarioDto.class));
    }

    @GetMapping
    @Operation(summary = "Endpoint responsável por buscar prontuários.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<List<BuscarProntuariosDto>>buscarProntuarios(){
        var listar = prontuarioServico.buscarProntuarios();
        return ResponseEntity.ok().body(listar);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Endpoint responsável por buscar prontuário pelo id.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<BuscarProntuariosDto>buscarPorId(@PathVariable Long id){
        var buscar = prontuarioServico.buscarPorId(id);
        return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarProntuariosDto.class));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint responsável por deletar prontuário pelo id.")
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<Void>excluirProntuario(@PathVariable Long id){
        buscarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Endpoint responsável por atualizar o prontuário pelo id.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<AtualizarProntuarioDto> atualizarProntuario(@RequestBody @Valid AtualizarProntuarioDto atualizarProntuarioDto,
    		                                                          @PathVariable Long id){
        var atualizar = prontuarioServico.atualizarProntuario(atualizarProntuarioDto,id);    
        return ResponseEntity.ok().body(modelMapper.map(atualizar, AtualizarProntuarioDto.class));
    }
}
