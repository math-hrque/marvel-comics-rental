package ufpr.marvel_app_spring.dtos;

import jakarta.validation.constraints.NotBlank;

public record RequestUpdateUsuarioDto(@NotBlank Long id,
	@NotBlank String nome, 
	@NotBlank String email,
	@NotBlank String senha,
	@NotBlank String telefone,
	String foto,
	@NotBlank boolean active)
{}
