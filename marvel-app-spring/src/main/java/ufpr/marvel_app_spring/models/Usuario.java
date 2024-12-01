package ufpr.marvel_app_spring.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufpr.marvel_app_spring.dtos.RequestCreateUsuarioDto;

@Entity(name="usuarios")
@Table(name="usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id",nullable = false)
	private Long id;
	@Column(name = "user_nome",nullable = false)
	private String nome;
	@Column(name = "user_email",nullable = false)
	private String email;
	@Column(name = "user_senha",nullable = false)
	private String senha;
	@Column(name = "user_telefone",nullable = false)
	private String telefone;
	@Column(name = "user_foto",nullable = true)
	private String foto;
	@Column(nullable = false)
	private Boolean active;
	
	public Usuario(RequestCreateUsuarioDto requestUsuarioDto) {
		this.nome = requestUsuarioDto.nome();
		this.email = requestUsuarioDto.email();
		this.senha = requestUsuarioDto.senha();
		this.telefone = requestUsuarioDto.telefone();
		this.foto = requestUsuarioDto.foto();
		this.active = true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", telefone="
				+ telefone + ", foto=" + foto + "]";
	}
	
}
