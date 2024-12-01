package ufpr.marvel_app_spring.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="marvelhq")
@Table(name="marvelhq")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class MarvelHq {
	@Id
	@Column(name = "hq_id",nullable = false)
	private Long id;
	@Column(name = "hq_titulo",nullable = false)
	private String titulo;
	@Column(name = "hq_imagem",nullable = false)
	private String imagem;

	@Override
	public String toString() {
		return "MarvelHq [id=" + id + ", titulo=" + titulo + ", imagem=" + imagem + "]";
	}
}
