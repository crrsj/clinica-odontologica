package br.com.clinica.odontologica.controle;

import br.com.clinica.odontologica.dto.AtualizarPacienteDto;
import br.com.clinica.odontologica.dto.BuscarPacientesDto;
import br.com.clinica.odontologica.dto.CadastrarPacienteDto;
import br.com.clinica.odontologica.entidade.Paciente;
import br.com.clinica.odontologica.servico.PacienteServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Endpoint responsável por cadastrar o paciente .")
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<CadastrarPacienteDto>cadastrarPaciente(@RequestBody CadastrarPacienteDto dto){
        var cadastrar = pacienteServico.cadastrarPaciente(dto);
        BeanUtils.copyProperties(dto,cadastrar);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cadastrar.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
       
    }

    @GetMapping
    @Operation(summary = "Endpoint responsável por buscar os pacientes .")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<List<BuscarPacientesDto>>listarPacientes(){
        var listar = pacienteServico.listarPacientes();
        return ResponseEntity.ok().body(listar);
    }


    @GetMapping("/nomePaciente")
    @Operation(summary = "Endpoint responsável por buscar o paciente pelo nome .")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<List<BuscarPacientesDto>>buscarPorNome(@PathParam("nome")String nome){
        var buscarNome = pacienteServico.buscarPorNome(nome);
        return ResponseEntity.ok().body(buscarNome.stream().map(BuscarPacientesDto::new).toList());
    }


    @GetMapping("/cpfPaciente")
    @Operation(summary = "Endpoint responsável por buscar o paciente pelo cpf .")
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<?>buscarPorCpf(@PathParam("cpf")String cpf){
        var buscarCpf = pacienteServico.buscarPorCpf(cpf);
        return ResponseEntity.ok().body(buscarCpf);
    }


    @PutMapping
    @Operation(summary = "Endpoint responsável por atualizar o paciente .")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<AtualizarPacienteDto> atualizarPaciente(@RequestBody AtualizarPacienteDto atualizarPacienteDto){

        var atualizar = pacienteServico.AtualizarPaciente(atualizarPacienteDto);
        BeanUtils.copyProperties(atualizarPacienteDto,atualizar);
        return ResponseEntity.ok().body(atualizarPacienteDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint responsável por excluir o paciente .")
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<Void>excluirPaciente(@PathVariable Long id){
        pacienteServico.excluirPaciente(id);
        return ResponseEntity.noContent().build();
    }


}
