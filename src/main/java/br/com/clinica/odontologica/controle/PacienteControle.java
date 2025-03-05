package br.com.clinica.odontologica.controle;

import java.util.List;
import java.util.stream.Collectors;

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

import br.com.clinica.odontologica.dto.AtualizarPacienteDto;
import br.com.clinica.odontologica.dto.BuscarPacientesDto;
import br.com.clinica.odontologica.dto.CadastrarPacienteDto;
import br.com.clinica.odontologica.servico.PacienteServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
public class PacienteControle {

    
    private final PacienteServico pacienteServico;
    
    private final ModelMapper modelMapper;


    @PostMapping
    @Operation(summary = "Endpoint responsável por cadastrar o paciente .")
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<CadastrarPacienteDto>cadastrarPaciente(@RequestBody @Valid CadastrarPacienteDto dto){
        var cadastrar = pacienteServico.cadastrarPaciente(dto);      
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cadastrar.getId()).toUri();  
        return ResponseEntity.created(uri).body(modelMapper.map(cadastrar, CadastrarPacienteDto.class));
       
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
    
    @GetMapping("/{id}")
    @Operation(summary = "Endpoint responsável por buscar paciente pelo id .")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<BuscarPacientesDto>buscarPorId(@PathVariable Long id) {
    	var buscar = pacienteServico.buscarPorId(id);    	
    	return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarPacientesDto.class));
    }


    @GetMapping("/nomePaciente")
    @Operation(summary = "Endpoint responsável por buscar o paciente pelo nome .")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<List<BuscarPacientesDto>>buscarPorNome(@PathParam("nome")String nome){
        var buscarNome = pacienteServico.buscarPorNome(nome);
        return ResponseEntity.ok().body(buscarNome.stream().map(buscarPaciente -> modelMapper.map(buscarNome, BuscarPacientesDto.class)).
        		collect(Collectors.toList()));
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


    @PutMapping("/{id}")
    @Operation(summary = "Endpoint responsável por atualizar o paciente pelo id .")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<AtualizarPacienteDto> atualizarPaciente(@RequestBody @Valid AtualizarPacienteDto atualizarPacienteDto,
    		                                                       @PathVariable("id") Long id){

        var atualizar = pacienteServico.AtualizarPaciente(atualizarPacienteDto,id);     
        return ResponseEntity.ok().body( modelMapper.map(atualizar, AtualizarPacienteDto.class));
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
