package ufpr.marvel_app_spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ufpr.marvel_app_spring.models.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	List<Usuario> findAllByActiveTrue();
	Usuario findByEmail(String email);
}
