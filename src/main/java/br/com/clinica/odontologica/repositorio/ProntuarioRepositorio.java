package br.com.clinica.odontologica.repositorio;

import br.com.clinica.odontologica.entidade.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProntuarioRepositorio extends JpaRepository<Prontuario,Long> {
}
