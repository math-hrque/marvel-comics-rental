package ufpr.marvel_app_spring.controllers;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import ufpr.marvel_app_spring.dtos.AluguelHqComDetalhesDto;
import ufpr.marvel_app_spring.dtos.RequestCreateAluguelHqDto;
import ufpr.marvel_app_spring.models.AluguelHq;
import ufpr.marvel_app_spring.models.MarvelHq;
import ufpr.marvel_app_spring.repositories.AluguelHqComDetalhesViewRepository;
import ufpr.marvel_app_spring.repositories.AluguelHqRepository;
import ufpr.marvel_app_spring.repositories.UsuarioRepository;
import ufpr.marvel_app_spring.services.AluguelHqService;
import ufpr.marvel_app_spring.services.MarvelHqService;

@RestController
@RequestMapping("/v1/alugueis")
public class AluguelHqController {
	
	Logger logger = LoggerFactory.getLogger(AluguelHqController.class);
	
	@Autowired
	private AluguelHqService aluguelHqService;	
	@Autowired
	private MarvelHqService marvelHqService;
	@Autowired
	private UsuarioRepository userRepository;
	@Autowired
	private AluguelHqComDetalhesViewRepository aluguelDtoRepository;
	@Autowired
	private AluguelHqRepository aluguelHqRepository;
	
	@Operation(description = "Operação para listar todos os alugueis de um determinado usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluguéis obtidos com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
	@GetMapping("/list/{id}")
	public ResponseEntity<?> getAllAlugueisUsuario(@PathVariable Long id) {
		try {
	        // Busca todos os usuários ativos
	        List<AluguelHqComDetalhesDto> alugueis = aluguelDtoRepository.findByUsuarioId(id);

	        // Verifica se a lista de usuários está vazia
	        if (alugueis.isEmpty()) {
	            // Retorna 204 No Content se não houver usuários ativos
	            return ResponseEntity.noContent().build();
	        } else {
	            // Retorna 200 OK com a lista de usuários ativos
	            return ResponseEntity.ok(alugueis);
	        }
	    } catch (Exception e) {
	        // Em caso de erro, retorna 500 Internal Server Error com a mensagem de erro
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao buscar os usuários: " + e.getMessage());
	    }	
	}
	
	
	@Operation(description = "Operação para persistir um aluguel de Hq.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário salvo com sucesso"),
            @ApiResponse(responseCode = "417", description = "Algum erro de validação de dados ocorreu"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
	@PostMapping
	public ResponseEntity<?> createAluguel(@RequestBody @Valid RequestCreateAluguelHqDto data) {
	
		try {
			boolean user = userRepository.findById(data.id_user()).isPresent();
			MarvelHq novoHq = new MarvelHq(data.id_hq(),data.titulo(),data.imagem());
			MarvelHq savedHq = marvelHqService.save(novoHq);
			if((user) && (savedHq != null) ) {				
				AluguelHq novoAluguel = new AluguelHq();
				novoAluguel.setAluguelHqIdUser(data.id_user());
				novoAluguel.setAluguelHqIdHq(data.id_hq());
				LocalDate date = aluguelHqService.setDataDevolucao(data.data_devolucao());
				novoAluguel.setDataDevolucao(date);
				AluguelHq savedAluguel = aluguelHqService.save(novoAluguel);
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	                    .buildAndExpand(savedAluguel.getId()).toUri();
				return ResponseEntity.created(location).body(savedAluguel);
			}
			return ResponseEntity.noContent().build();
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Erro ao salvar AluguelHq: " + e.getMessage());
            return ResponseEntity.badRequest().build();
		}
	}

	@Operation(description = "Operação para deletar/devolver um Hq alugado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Hq devolvido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Aluguel não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAluguel(@PathVariable Long id) {
		if(aluguelHqRepository.existsById(id)) {
			aluguelHqRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
