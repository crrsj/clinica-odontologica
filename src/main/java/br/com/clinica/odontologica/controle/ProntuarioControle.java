package br.com.clinica.odontologica.controle;

import br.com.clinica.odontologica.dto.AtualizarProntuarioDto;
import br.com.clinica.odontologica.dto.BuscarProntuariosDto;
import br.com.clinica.odontologica.dto.CadastrarPacienteDto;
import br.com.clinica.odontologica.dto.CadastrarProntuarioDto;
import br.com.clinica.odontologica.servico.ProntuarioServico;
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
@RequestMapping("/prontuario")
public class ProntuarioControle {

    @Autowired
    private ProntuarioServico prontuarioServico;

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
        BeanUtils.copyProperties(cadastrarProntuarioDto,cadastrar);
        return ResponseEntity.created(uri).body(cadastrarProntuarioDto);
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
        return ResponseEntity.ok().body(new BuscarProntuariosDto(buscar));
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

    @PutMapping
    @Operation(summary = "Endpoint responsável por atualizar prontuário.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<AtualizarProntuarioDto> atualizarProntuario(@RequestBody @Valid AtualizarProntuarioDto atualizarProntuarioDto){
        var atualizar = prontuarioServico.atualizarProntuario(atualizarProntuarioDto);
        BeanUtils.copyProperties(atualizarProntuarioDto,atualizar);
        return ResponseEntity.ok().body(atualizarProntuarioDto);
    }
}
