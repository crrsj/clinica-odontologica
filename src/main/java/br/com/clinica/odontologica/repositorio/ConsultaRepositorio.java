package br.com.clinica.odontologica.repositorio;

import br.com.clinica.odontologica.dto.BuscarConsultasDto;
import br.com.clinica.odontologica.entidade.Consulta;
import br.com.clinica.odontologica.enums.StatusConsulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepositorio extends JpaRepository<Consulta,Long> {
    List<BuscarConsultasDto> findByStatus(StatusConsulta status);

}
