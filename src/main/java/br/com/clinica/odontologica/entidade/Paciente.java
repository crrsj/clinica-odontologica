package br.com.clinica.odontologica.entidade;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_pacientes")
@Data
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(nullable = false,unique = true)
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
    @Column(columnDefinition = "TEXT")
    private String historicoMedico;
    private String alergias;
    private String medicamentos;
    @JsonIgnore
    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Consulta>consulta;
    @JsonIgnore
    @OneToMany(mappedBy = "paciente")
    private List<Financeiro>financeiro;
    @JsonIgnore
    @OneToMany(mappedBy = "paciente")
    private List<Prontuario>prontuario;

    }