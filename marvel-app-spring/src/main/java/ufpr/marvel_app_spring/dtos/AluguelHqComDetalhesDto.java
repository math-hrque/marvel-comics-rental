package ufpr.marvel_app_spring.dtos;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "v_aluguelhq_marvelhq")
@Table(name = "v_aluguelhq_marvelhq")
public class AluguelHqComDetalhesDto {
	@Id
	@Column(name = "aluguelhq_id")
	private Long aluguelhq_id;
	@Column(name = "aluguelhq_usuario_id")
	private Long aluguelhq_usuario_id;
	@Column(name = "aluguelhq_marvelhq_id")
	private Long aluguelhq_marvelhq_id;
	@Column(name = "hq_titulo")
	private String hq_titulo;
	@Column(name = "hq_imagem")
	private String hq_imagem;
	@Column(name = "aluguelhq_data_devolucao")
	private LocalDate aluguelhq_data_devolucao;
}
