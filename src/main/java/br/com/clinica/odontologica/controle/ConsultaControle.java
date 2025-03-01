package br.com.clinica.odontologica.controle;

import br.com.clinica.odontologica.dto.AtualizarConsultaDto;
import br.com.clinica.odontologica.dto.BuscarConsultasDto;
import br.com.clinica.odontologica.dto.CadastrarConsultaDto;
import br.com.clinica.odontologica.entidade.Consulta;
import br.com.clinica.odontologica.servico.ConsultaServico;
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
@RequestMapping("/consulta")
public class ConsultaControle {

    @Autowired
    public ConsultaServico consultaServico;


    @PostMapping("{pacienteId}")
    @Operation(summary = "Endpoint responsável por cadastrar consultas pelo id do paciente.")
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<CadastrarConsultaDto>cadastrarConsulta(@RequestBody @Valid CadastrarConsultaDto cadastrarConsultaDto,
                                                                 @PathVariable Long pacienteId){
        var cadastrar = consultaServico.cadastrarConsulta(cadastrarConsultaDto,pacienteId);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cadastrar.getId()).toUri();
        BeanUtils.copyProperties(cadastrarConsultaDto,cadastrar);
        return ResponseEntity.created(uri).body(cadastrarConsultaDto);

    }

    @GetMapping
    @Operation(summary = "Endpoint responsável por buscar consultas.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<List<BuscarConsultasDto>>listarConsultas(){
        var listar = consultaServico.buscarConsultas();
        return ResponseEntity.ok().body(listar);
    }

    @GetMapping("/agendada")
    @Operation(summary = "Endpoint responsável por buscar consultas agendadas.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<List<BuscarConsultasDto>>buscarAgendada(){
        var buscar = consultaServico.buscarAgendada();
        return ResponseEntity.ok().body(buscar);
    }

    @GetMapping("/cancelada")
    @Operation(summary = "Endpoint responsável por buscar consultas canceladas.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<List<BuscarConsultasDto>>buscarCancelada(){
        var buscar = consultaServico.buscarCancelada();
        return ResponseEntity.ok().body(buscar);
    }

    @PutMapping
    @Operation(summary = "Endpoint resposável por atualizar consultas.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<AtualizarConsultaDto>atualizarConsulta(@RequestBody @Valid AtualizarConsultaDto atualizarConsultaDto){

        var atualizar = consultaServico.atualizarConsulta(atualizarConsultaDto);
        BeanUtils.copyProperties(atualizarConsultaDto,atualizar);
        return ResponseEntity.ok().body(atualizarConsultaDto);
    }

    @GetMapping("{id}")
    @Operation(summary = "Endpoint responsável por buscar consulta pelo id.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<BuscarConsultasDto> buscarPorId(@PathVariable Long id){
        var buscar = consultaServico.buscarPorId(id);
        return ResponseEntity.ok().body(new BuscarConsultasDto(buscar));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint responsável por deletar consulta pelo id.")
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<Void>excluirConsulta(@PathVariable Long id){
        consultaServico.excluirConsulta(id);
        return ResponseEntity.noContent().build();
    }
}
