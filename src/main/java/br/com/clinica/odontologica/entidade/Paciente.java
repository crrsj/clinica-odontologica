package br.com.clinica.odontologica.entidade;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pacientes")
@Data
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
    private String endereco;
    @Column(columnDefinition = "TEXT")
    private String historicoMedico;
    private String alergias;
    private String medicamentos;
    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Consulta>consulta = new ArrayList<>();
    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Financeiro>financeiro = new ArrayList<>();
}