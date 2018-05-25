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

import com.fyd.sistema.api.entity.TipoBenchmark;
import com.fyd.sistema.api.response.Response;
import com.fyd.sistema.api.service.TipoBenchmarkService;

@RestController
@RequestMapping("/api/tipoBenchmark")
@CrossOrigin(origins="*")
public class TipoBenchmarkController {
	@Autowired
	private TipoBenchmarkService tipoBenchmarkService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<TipoBenchmark>> create(HttpServletRequest request, @RequestBody TipoBenchmark tipoBenchmark,
			BindingResult result){
		Response<TipoBenchmark> response = new Response<TipoBenchmark>();
		
		try {
			validateCreateTipoBenchmark(tipoBenchmark, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			TipoBenchmark tipoBenchmarkPersisted = (TipoBenchmark) tipoBenchmarkService.createOrUpdate(tipoBenchmark);
			response.setData(tipoBenchmarkPersisted);
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
	
	private void validateCreateTipoBenchmark(TipoBenchmark tipoBenchmark, BindingResult result) {
		if(tipoBenchmark.getNome() == null) {
			result.addError(new ObjectError("TipoBenchmark", "Nome é necessário"));
		}
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<TipoBenchmark>> update(HttpServletRequest request,
			@RequestBody TipoBenchmark tipoBenchmark,
			BindingResult result) {
		Response<TipoBenchmark> response = new Response<TipoBenchmark>();
		
		try {
			validateUpdateTipoBenchmark(tipoBenchmark, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			TipoBenchmark tipoBenchmarkPersisted = (TipoBenchmark) tipoBenchmarkService.createOrUpdate(tipoBenchmark);
			response.setData(tipoBenchmarkPersisted);
		}
		catch(Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}

	private void validateUpdateTipoBenchmark(TipoBenchmark tipoBenchmark, BindingResult result) {
		if(tipoBenchmark.getId() == null) {
			result.addError(new ObjectError("TipoBenchmark", "Id não informado"));
		}
		
		if(tipoBenchmark.getNome() == null) {
			result.addError(new ObjectError("TipoBenchmark", "Nome é necessário"));
		}
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<TipoBenchmark>> findById(@PathVariable("id") String id){
		Response<TipoBenchmark> response = new Response<TipoBenchmark>();
		TipoBenchmark tipoBenchmark = tipoBenchmarkService.findById(id);
		if(tipoBenchmark == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(tipoBenchmark);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id){
		Response<String> response = new Response<String>();
		TipoBenchmark tipoBenchmark = tipoBenchmarkService.findById(id);
		if(tipoBenchmark == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		tipoBenchmarkService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<TipoBenchmark>>> findAll(@PathVariable int page, @PathVariable int count){
		Response<Page<TipoBenchmark>> response = new Response<Page<TipoBenchmark>>();
		Page<TipoBenchmark> tiposBenchmark = tipoBenchmarkService.findAll(page, count);
		response.setData(tiposBenchmark);
		return ResponseEntity.ok(response);
	}
}
