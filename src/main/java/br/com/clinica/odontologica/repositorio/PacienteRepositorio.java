package br.com.clinica.odontologica.repositorio;

import br.com.clinica.odontologica.dto.BuscarPacientesDto;
import br.com.clinica.odontologica.entidade.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PacienteRepositorio extends JpaRepository<Paciente,Long> {
    Optional<Paciente>findByCpf(String cpf);

    @Query(value = "select p from Paciente p where upper(trim(p.nome)) like %?1% ")
    List<Paciente> findByNome(String nome);
}
