package ufpr.marvel_app_spring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ufpr.marvel_app_spring.models.LoginUsuario;
import ufpr.marvel_app_spring.models.Usuario;
import ufpr.marvel_app_spring.repositories.UsuarioRepository;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UsuarioRepository repository;

    @PostMapping()
    public ResponseEntity<?> getUserByLogin(@RequestBody @Valid LoginUsuario data){
        	Usuario usuarioEncontrado = repository.findByEmail(data.getEmail());
        	if (usuarioEncontrado != null && usuarioEncontrado.getSenha().equals(data.getSenha())){
        		return ResponseEntity.ok(usuarioEncontrado);
        	} else {
			return ResponseEntity.badRequest().build(); 
		}
    }

}
