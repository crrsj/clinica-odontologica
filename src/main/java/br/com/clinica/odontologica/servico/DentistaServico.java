package br.com.clinica.odontologica.servico;

import br.com.clinica.odontologica.dto.AtualizarDentistaDto;
import br.com.clinica.odontologica.dto.BuscarDentistasDto;
import br.com.clinica.odontologica.dto.CadastrarDentistaDto;
import br.com.clinica.odontologica.entidade.Dentista;
import br.com.clinica.odontologica.repositorio.DentistaRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaServico {

    @Autowired
    private DentistaRepositorio dentistaRepositorio;

    public Dentista cadastrarDentista(CadastrarDentistaDto cadastrarDentistaDto){
        var cadastrar = new Dentista();
        BeanUtils.copyProperties(cadastrarDentistaDto,cadastrar);
        return dentistaRepositorio.save(cadastrar);
    }

    public List<BuscarDentistasDto>buscarDentistas(){
        return dentistaRepositorio.findAll().
                stream().map(BuscarDentistasDto::new).toList();

    }
    public Dentista buscarPorId(Long id){
        Optional<Dentista>buscar = dentistaRepositorio.findById(id);
        return buscar.orElseThrow();
    }
    public List<Dentista> buscarPeloNome(String nome){
        return dentistaRepositorio.findByNome(nome.trim().toUpperCase());
    }

   public void excluirDentista(Long id){
        buscarPorId(id);
        dentistaRepositorio.deleteById(id);
   }
   public Dentista atualizarDentista(AtualizarDentistaDto atualizarDentistaDto){
    var atualizar = new Dentista();
     BeanUtils.copyProperties(atualizar,atualizarDentistaDto);
     return dentistaRepositorio.save(atualizar);
   }
}
