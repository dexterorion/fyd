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

import com.fyd.sistema.api.entity.FuncaoExercicio;
import com.fyd.sistema.api.response.Response;
import com.fyd.sistema.api.service.FuncaoExercicioService;

@RestController
@RequestMapping("/api/funcaoExercicio")
@CrossOrigin(origins="*")
public class FuncaoExercicioController {
	@Autowired
	private FuncaoExercicioService funcaoExercicioService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<FuncaoExercicio>> create(HttpServletRequest request, @RequestBody FuncaoExercicio funcaoExercicio,
			BindingResult result){
		Response<FuncaoExercicio> response = new Response<FuncaoExercicio>();
		
		try {
			validateCreateFuncaoExercicio(funcaoExercicio, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			FuncaoExercicio funcaoExercicioPersisted = (FuncaoExercicio) funcaoExercicioService.createOrUpdate(funcaoExercicio);
			response.setData(funcaoExercicioPersisted);
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
	
	private void validateCreateFuncaoExercicio(FuncaoExercicio funcaoExercicio, BindingResult result) {
		if(funcaoExercicio.getNome() == null) {
			result.addError(new ObjectError("FuncaoExercicio", "Nome é necessário"));
		}
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<FuncaoExercicio>> update(HttpServletRequest request,
			@RequestBody FuncaoExercicio funcaoExercicio,
			BindingResult result) {
		Response<FuncaoExercicio> response = new Response<FuncaoExercicio>();
		
		try {
			validateUpdateFuncaoExercicio(funcaoExercicio, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			FuncaoExercicio funcaoExercicioPersisted = (FuncaoExercicio) funcaoExercicioService.createOrUpdate(funcaoExercicio);
			response.setData(funcaoExercicioPersisted);
		}
		catch(Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}

	private void validateUpdateFuncaoExercicio(FuncaoExercicio funcaoExercicio, BindingResult result) {
		if(funcaoExercicio.getId() == null) {
			result.addError(new ObjectError("FuncaoExercicio", "Id não informado"));
		}
		
		if(funcaoExercicio.getNome() == null) {
			result.addError(new ObjectError("FuncaoExercicio", "Nome é necessário"));
		}
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<FuncaoExercicio>> findById(@PathVariable("id") String id){
		Response<FuncaoExercicio> response = new Response<FuncaoExercicio>();
		FuncaoExercicio funcaoExercicio = funcaoExercicioService.findById(id);
		if(funcaoExercicio == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(funcaoExercicio);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id){
		Response<String> response = new Response<String>();
		FuncaoExercicio funcaoExercicio = funcaoExercicioService.findById(id);
		if(funcaoExercicio == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		funcaoExercicioService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<FuncaoExercicio>>> findAll(@PathVariable int page, @PathVariable int count){
		Response<Page<FuncaoExercicio>> response = new Response<Page<FuncaoExercicio>>();
		Page<FuncaoExercicio> funcoesExercicio = funcaoExercicioService.findAll(page, count);
		response.setData(funcoesExercicio);
		return ResponseEntity.ok(response);
	}
}
