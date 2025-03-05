package br.com.clinica.odontologica.entidade;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tb_dentistas")
@Data
@NoArgsConstructor
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cro;
    private String especialidade;
    private String telefone;
    private String email;
    @OneToMany(mappedBy = "dentista")
    private List<Consulta> consulta;

  
}

