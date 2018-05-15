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

import com.fyd.sistema.api.entity.PilarExercicio;
import com.fyd.sistema.api.response.Response;
import com.fyd.sistema.api.service.PilarExercicioService;

@RestController
@RequestMapping("/api/pilarExercicio")
@CrossOrigin(origins="*")
public class PilarExercicioController {
	@Autowired
	private PilarExercicioService pilarExercicioService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<PilarExercicio>> create(HttpServletRequest request, @RequestBody PilarExercicio pilarExercicio,
			BindingResult result){
		Response<PilarExercicio> response = new Response<PilarExercicio>();
		
		try {
			validateCreatePilarExercicio(pilarExercicio, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			PilarExercicio pilarExercicioPersisted = (PilarExercicio) pilarExercicioService.createOrUpdate(pilarExercicio);
			response.setData(pilarExercicioPersisted);
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
	
	private void validateCreatePilarExercicio(PilarExercicio pilarExercicio, BindingResult result) {
		if(pilarExercicio.getNome() == null) {
			result.addError(new ObjectError("PilarExercicio", "Nome é necessário"));
		}
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<PilarExercicio>> update(HttpServletRequest request,
			@RequestBody PilarExercicio pilarExercicio,
			BindingResult result) {
		Response<PilarExercicio> response = new Response<PilarExercicio>();
		
		try {
			validateUpdatePilarExercicio(pilarExercicio, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			PilarExercicio pilarExercicioPersisted = (PilarExercicio) pilarExercicioService.createOrUpdate(pilarExercicio);
			response.setData(pilarExercicioPersisted);
		}
		catch(Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}

	private void validateUpdatePilarExercicio(PilarExercicio pilarExercicio, BindingResult result) {
		if(pilarExercicio.getId() == null) {
			result.addError(new ObjectError("PilarExercicio", "Id não informado"));
		}
		
		if(pilarExercicio.getNome() == null) {
			result.addError(new ObjectError("PilarExercicio", "Nome é necessário"));
		}
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<PilarExercicio>> findById(@PathVariable("id") String id){
		Response<PilarExercicio> response = new Response<PilarExercicio>();
		PilarExercicio pilarExercicio = pilarExercicioService.findById(id);
		if(pilarExercicio == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(pilarExercicio);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id){
		Response<String> response = new Response<String>();
		PilarExercicio pilarExercicio = pilarExercicioService.findById(id);
		if(pilarExercicio == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		pilarExercicioService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<PilarExercicio>>> findAll(@PathVariable int page, @PathVariable int count){
		Response<Page<PilarExercicio>> response = new Response<Page<PilarExercicio>>();
		Page<PilarExercicio> pilaresExercicio = pilarExercicioService.findAll(page, count);
		response.setData(pilaresExercicio);
		return ResponseEntity.ok(response);
	}
}
