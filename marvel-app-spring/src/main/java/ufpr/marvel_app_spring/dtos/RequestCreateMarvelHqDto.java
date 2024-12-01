package ufpr.marvel_app_spring.dtos;

import jakarta.validation.constraints.NotBlank;

public record RequestCreateMarvelHqDto(
    @NotBlank Long id,
    @NotBlank String titulo,
    @NotBlank String imagem
) {}