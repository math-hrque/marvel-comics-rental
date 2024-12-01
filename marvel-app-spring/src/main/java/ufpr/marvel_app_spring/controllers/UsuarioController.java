package ufpr.marvel_app_spring.controllers;


import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ufpr.marvel_app_spring.dtos.RequestCreateUsuarioDto;
import ufpr.marvel_app_spring.dtos.RequestUpdateUsuarioDto;
import ufpr.marvel_app_spring.models.Usuario;
import ufpr.marvel_app_spring.repositories.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {
	
	Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	@Autowired
	private UsuarioRepository repository;
	
	@Operation(description = "Operação para listar todos os usuários.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários obtidos com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
	@GetMapping("/list")
	public ResponseEntity<?> getAllUsers() {
		try {
	        // Busca todos os usuários ativos
	        List<Usuario> usuarios = repository.findAllByActiveTrue();

	        // Verifica se a lista de usuários está vazia
	        if (usuarios.isEmpty()) {
	            // Retorna 204 No Content se não houver usuários ativos
	            return ResponseEntity.noContent().build();
	        } else {
	            // Retorna 200 OK com a lista de usuários ativos
	            return ResponseEntity.ok(usuarios);
	        }
	    } catch (Exception e) {
	        // Em caso de erro, retorna 500 Internal Server Error com a mensagem de erro
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Ocorreu um erro ao buscar os usuários: " + e.getMessage());
	    }
	}

	@Operation(description = "Operação para ler um usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário obtido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
	    return repository.findById(id)
	                     .map(user -> ResponseEntity.ok(user))
	                     .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
    @Operation(description = "Operação para persistir o usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário salvo com sucesso"),
            @ApiResponse(responseCode = "417", description = "Algum erro de validação de dados ocorreu"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
	@PostMapping()
	public ResponseEntity<?> createUser(@RequestBody @Valid RequestCreateUsuarioDto data) {
    	try {
            Usuario novoUsuario = new Usuario(data);
            Usuario savedUsuario = repository.save(novoUsuario);
            
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedUsuario.getId()).toUri();
            return ResponseEntity.created(location).body(savedUsuario);   
        } catch (Exception e) {
            // Captura a exceção e trata o erro
            // Exemplo:
            logger.error("Erro ao salvar usuário: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }	
	}
    
    @Operation(description = "Operação para alterar e persistir um usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso."),
            @ApiResponse(responseCode = "204", description = "Indica que a atualização foi bem-sucedida, mas não há conteúdo para retornar"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "417", description = "Algum erro de validação de dados ocorreu"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
	@PutMapping()
	@Transactional
	public ResponseEntity<?> updateUser(@RequestBody @Valid RequestUpdateUsuarioDto data) {
       		Optional<Usuario> optionalUser = repository.findById(data.id());
    		if (optionalUser.isPresent()) {
    			Usuario updateUsuario = optionalUser.get();
    			updateUsuario.setNome(data.nome());
    			updateUsuario.setEmail(data.email());
    			updateUsuario.setSenha(data.senha());
    			updateUsuario.setTelefone(data.telefone());
    			updateUsuario.setFoto(data.foto());
    			//updateUsuario.setActive(data.active());
    			return ResponseEntity.ok(updateUsuario);
    		}	else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    		}
	}
    
    @Operation(description = "Operação para deletar um usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
	@DeleteMapping("/{id}")
    @Transactional
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		//repository.deleteById(id);
		Optional<Usuario> optionalUser = repository.findById(id);
		if (optionalUser.isPresent()) {
			Usuario updateUsuario = optionalUser.get();
			updateUsuario.setActive(false);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}	
	}
}
