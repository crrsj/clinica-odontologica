package br.com.clinica.odontologica.infra;

import br.com.clinica.odontologica.dto.MensagemDeErro;
import br.com.clinica.odontologica.dto.ValidarCamposDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class TratamentoDeErros {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<MensagemDeErro>objetoNaoEncontrado(){
        var msg = new MensagemDeErro(HttpStatus.NOT_FOUND,"Objeto não encontrado !");
        return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MensagemDeErro>cpfJaCadastrado(){
        var msg = new MensagemDeErro(HttpStatus.NOT_FOUND,"CPF Já cadastrado !");
        return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?>validarCampos(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().
                body(erros.stream().map(ValidarCamposDto::new).toList());
    }

}
