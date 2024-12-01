package ufpr.marvel_app_spring.dtos;

import jakarta.validation.constraints.NotBlank;

public record RequestCreateAluguelHqDto(
	@NotBlank Long id_user,
	@NotBlank Long id_hq,
	@NotBlank String titulo,
	@NotBlank String imagem,
	@NotBlank String data_devolucao
) {}
