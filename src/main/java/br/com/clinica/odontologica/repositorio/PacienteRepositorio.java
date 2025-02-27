package br.com.clinica.odontologica.repositorio;

import br.com.clinica.odontologica.entidade.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepositorio extends JpaRepository<Paciente,Long> {
}
