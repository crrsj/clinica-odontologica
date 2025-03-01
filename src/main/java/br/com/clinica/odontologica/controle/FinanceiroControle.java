package br.com.clinica.odontologica.controle;


import br.com.clinica.odontologica.dto.AtualizarFinanceiroDto;
import br.com.clinica.odontologica.dto.BuscarFinanceiroDto;
import br.com.clinica.odontologica.dto.CadastrarFinanceiroDto;
import br.com.clinica.odontologica.dto.CadastrarPacienteDto;
import br.com.clinica.odontologica.servico.FinanceiroServico;
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
@RequestMapping("/financeiro")
public class FinanceiroControle {


    @Autowired
    private FinanceiroServico financeiroServico;


    @PostMapping("/{pacienteId}")
    @Operation(summary = "Endpoint responsável por cadastrar o financeiro .")
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<CadastrarFinanceiroDto>cadastrarFinanceiro(@RequestBody @Valid CadastrarFinanceiroDto cadastrarFinanceiroDto,
                                                                     @PathVariable Long pacienteId){
        var cadastrar = financeiroServico.cadastrarFinanceiro(cadastrarFinanceiroDto,pacienteId);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(cadastrar.getId()).toUri();
        BeanUtils.copyProperties(cadastrarFinanceiroDto,cadastrar);
        return ResponseEntity.created(uri).body(cadastrarFinanceiroDto);
    }

    @GetMapping
    @Operation(summary = "Endpoint responsável por buscar financeiros.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<List<BuscarFinanceiroDto>>buscarFinanceiros(){
        var listar = financeiroServico.buscarFinanceiros();
        return ResponseEntity.ok().body(listar);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint responsável por buscar financeiro pelo id.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<BuscarFinanceiroDto>buscarPorId(@PathVariable Long id){
        var buscar = financeiroServico.buscarPorId(id);
        return ResponseEntity.ok().body(new BuscarFinanceiroDto(buscar));
    }

    @PutMapping
    @Operation(summary = "Endpoint responsável por atualizar financeiro.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<AtualizarFinanceiroDto>atualizarFinanceiro(@RequestBody AtualizarFinanceiroDto atualizarFinanceiroDto ){

        var atualizar = financeiroServico.atualizarFinanceiro(atualizarFinanceiroDto);
        BeanUtils.copyProperties(atualizarFinanceiroDto,atualizar);
        return ResponseEntity.ok().body(atualizarFinanceiroDto);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint responsável por deletar financeiro pelo id.")
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<Void>excluirFinanceiro(@PathVariable Long id){
        buscarPorId(id);
        financeiroServico.excluirFinanceiro(id);
        return ResponseEntity.noContent().build();
    }
}

