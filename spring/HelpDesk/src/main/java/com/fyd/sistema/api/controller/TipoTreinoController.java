package com.fyd.sistema.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fyd.sistema.api.entity.TipoTreino;
import com.fyd.sistema.api.response.Response;
import com.fyd.sistema.api.service.TipoTreinoService;

@RestController
@RequestMapping("/api/tipoTreino")
@CrossOrigin(origins="*")
public class TipoTreinoController {
	@Autowired
	private TipoTreinoService tipoTreinoService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<TipoTreino>> create(HttpServletRequest request, @RequestBody TipoTreino tipoTreino,
			BindingResult result){
		Response<TipoTreino> response = new Response<TipoTreino>();
		
		try {
			validateCreateTipoTreino(tipoTreino, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			TipoTreino tipoTreinoPersisted = (TipoTreino) tipoTreinoService.createOrUpdate(tipoTreino);
			response.setData(tipoTreinoPersisted);
		}
		catch(DuplicateKeyException dE) {
			response.getErrors().add("Nome já registrado");
			return ResponseEntity.badRequest().body(response);
		}
		catch(Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
		
	}
	
	private void validateCreateTipoTreino(TipoTreino tipoTreino, BindingResult result) {
		if(tipoTreino.getNome() == null) {
			result.addError(new ObjectError("TipoTreino", "Nome é necessário"));
		}
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<TipoTreino>> update(HttpServletRequest request,
			@RequestBody TipoTreino tipoTreino,
			BindingResult result) {
		Response<TipoTreino> response = new Response<TipoTreino>();
		
		try {
			validateUpdateTipoTreino(tipoTreino, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			TipoTreino tipoTreinoPersisted = (TipoTreino) tipoTreinoService.createOrUpdate(tipoTreino);
			response.setData(tipoTreinoPersisted);
		}
		catch(Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}

	private void validateUpdateTipoTreino(TipoTreino tipoTreino, BindingResult result) {
		if(tipoTreino.getId() == null) {
			result.addError(new ObjectError("TipoTreino", "Id não informado"));
		}
		
		if(tipoTreino.getNome() == null) {
			result.addError(new ObjectError("TipoTreino", "Nome é necessário"));
		}
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<TipoTreino>> findById(@PathVariable("id") String id){
		Response<TipoTreino> response = new Response<TipoTreino>();
		TipoTreino tipoTreino = tipoTreinoService.findById(id);
		if(tipoTreino == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(tipoTreino);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id){
		Response<String> response = new Response<String>();
		TipoTreino tipoTreino = tipoTreinoService.findById(id);
		if(tipoTreino == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		tipoTreinoService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<TipoTreino>>> findAll(@PathVariable int page, @PathVariable int count){
		Response<Page<TipoTreino>> response = new Response<Page<TipoTreino>>();
		Page<TipoTreino> tiposTreino = tipoTreinoService.findAll(page, count);
		response.setData(tiposTreino);
		return ResponseEntity.ok(response);
	}
}
