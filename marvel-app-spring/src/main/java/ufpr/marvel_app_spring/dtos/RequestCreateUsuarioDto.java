package ufpr.marvel_app_spring.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestCreateUsuarioDto(
	@NotBlank String nome, 
	@NotBlank @Email String email,
	@NotBlank String senha,
	@NotBlank String telefone,
	String foto
) {}
