package ufpr.marvel_app_spring.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "aluguelhq")
@Table(name = "aluguelhq")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AluguelHq {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aluguelhq_id")
    private Long id;

    @Column(name = "aluguelhq_usuario_id")
    private Long aluguelHqIdUser;

    @Column(name = "aluguelhq_marvelhq_id")
    private Long aluguelHqIdHq;

    @Column(name = "aluguelhq_data_devolucao")
    private LocalDate dataDevolucao;
}
