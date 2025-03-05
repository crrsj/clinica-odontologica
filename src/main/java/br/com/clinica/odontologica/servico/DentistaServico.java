package br.com.clinica.odontologica.servico;







import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.clinica.odontologica.dto.AtualizarDentistaDto;
import br.com.clinica.odontologica.dto.BuscarDentistasDto;
import br.com.clinica.odontologica.dto.CadastrarDentistaDto;

import br.com.clinica.odontologica.entidade.Dentista;
import br.com.clinica.odontologica.repositorio.DentistaRepositorio;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DentistaServico {

	private final ModelMapper modelMapper;
    
    private final DentistaRepositorio dentistaRepositorio;

    public Dentista cadastrarDentista(CadastrarDentistaDto cadastrarDentistaDto){
        var cadastrar = modelMapper.map(cadastrarDentistaDto, Dentista.class);        
        return dentistaRepositorio.save(cadastrar);
    }

    public List<BuscarDentistasDto>buscarDentistas(){
        return dentistaRepositorio.findAll()
                .stream().map(buscarDentistas -> modelMapper
                .map(buscarDentistas, BuscarDentistasDto.class))
                .collect(Collectors.toList());

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
   public Dentista atualizarDentista(AtualizarDentistaDto atualizarDentistaDto,Long id){
  var atualizar = modelMapper.map(atualizarDentistaDto, Dentista.class);
  atualizar.setId(id);
     return dentistaRepositorio.save(atualizar);
   }
}
