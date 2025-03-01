package br.com.clinica.odontologica.repositorio;

import br.com.clinica.odontologica.entidade.Financeiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceiroRepositorio extends JpaRepository<Financeiro,Long> {
}
