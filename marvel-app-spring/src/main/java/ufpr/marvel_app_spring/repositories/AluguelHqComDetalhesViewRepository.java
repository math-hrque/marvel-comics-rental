package ufpr.marvel_app_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ufpr.marvel_app_spring.dtos.AluguelHqComDetalhesDto;

import java.util.List;

public interface AluguelHqComDetalhesViewRepository extends JpaRepository<AluguelHqComDetalhesDto, Long> {
    @Query("SELECT alu FROM v_aluguelhq_marvelhq alu WHERE alu.aluguelhq_usuario_id = :id")
    List<AluguelHqComDetalhesDto> findByUsuarioId(@Param("id") Long id);
}
