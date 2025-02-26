package br.com.clinica.odontologica.entidade;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


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
    private List<Consulta> consulta = new ArrayList<>();
}

