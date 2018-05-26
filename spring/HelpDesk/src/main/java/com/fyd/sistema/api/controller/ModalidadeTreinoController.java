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

import com.fyd.sistema.api.entity.ModalidadeTreino;
import com.fyd.sistema.api.response.Response;
import com.fyd.sistema.api.service.ModalidadeTreinoService;

@RestController
@RequestMapping("/api/modalidadeTreino")
@CrossOrigin(origins="*")
public class ModalidadeTreinoController {
	@Autowired
	private ModalidadeTreinoService modalidadeTreinoService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<ModalidadeTreino>> create(HttpServletRequest request, @RequestBody ModalidadeTreino modalidadeTreino,
			BindingResult result){
		Response<ModalidadeTreino> response = new Response<ModalidadeTreino>();
		
		try {
			validateCreateModalidadeTreino(modalidadeTreino, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			ModalidadeTreino modalidadeTreinoPersisted = (ModalidadeTreino) modalidadeTreinoService.createOrUpdate(modalidadeTreino);
			response.setData(modalidadeTreinoPersisted);
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
	
	private void validateCreateModalidadeTreino(ModalidadeTreino modalidadeTreino, BindingResult result) {
		if(modalidadeTreino.getNome() == null) {
			result.addError(new ObjectError("ModalidadeTreino", "Nome é necessário"));
		}
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<ModalidadeTreino>> update(HttpServletRequest request,
			@RequestBody ModalidadeTreino modalidadeTreino,
			BindingResult result) {
		Response<ModalidadeTreino> response = new Response<ModalidadeTreino>();
		
		try {
			validateUpdateModalidadeTreino(modalidadeTreino, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			ModalidadeTreino modalidadeTreinoPersisted = (ModalidadeTreino) modalidadeTreinoService.createOrUpdate(modalidadeTreino);
			response.setData(modalidadeTreinoPersisted);
		}
		catch(Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}

	private void validateUpdateModalidadeTreino(ModalidadeTreino modalidadeTreino, BindingResult result) {
		if(modalidadeTreino.getId() == null) {
			result.addError(new ObjectError("ModalidadeTreino", "Id não informado"));
		}
		
		if(modalidadeTreino.getNome() == null) {
			result.addError(new ObjectError("ModalidadeTreino", "Nome é necessário"));
		}
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<ModalidadeTreino>> findById(@PathVariable("id") String id){
		Response<ModalidadeTreino> response = new Response<ModalidadeTreino>();
		ModalidadeTreino modalidadeTreino = modalidadeTreinoService.findById(id);
		if(modalidadeTreino == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(modalidadeTreino);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id){
		Response<String> response = new Response<String>();
		ModalidadeTreino modalidadeTreino = modalidadeTreinoService.findById(id);
		if(modalidadeTreino == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		modalidadeTreinoService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<ModalidadeTreino>>> findAll(@PathVariable int page, @PathVariable int count){
		Response<Page<ModalidadeTreino>> response = new Response<Page<ModalidadeTreino>>();
		Page<ModalidadeTreino> modalidadesTreino = modalidadeTreinoService.findAll(page, count);
		response.setData(modalidadesTreino);
		return ResponseEntity.ok(response);
	}
}
