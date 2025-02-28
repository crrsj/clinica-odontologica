package br.com.clinica.odontologica.repositorio;

import br.com.clinica.odontologica.entidade.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DentistaRepositorio extends JpaRepository<Dentista,Long> {
    @Query(value = "select d from Dentista d where upper(trim(d.nome)) like %?1% ")
    List<Dentista> findByNome(String nome);
}
