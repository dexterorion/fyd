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

import com.fyd.sistema.api.entity.NivelExecucao;
import com.fyd.sistema.api.response.Response;
import com.fyd.sistema.api.service.NivelExecucaoService;

@RestController
@RequestMapping("/api/nivelExecucao")
@CrossOrigin(origins="*")
public class NivelExecucaoController {
	@Autowired
	private NivelExecucaoService nivelExecucaoService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<NivelExecucao>> create(HttpServletRequest request, @RequestBody NivelExecucao nivelExecucao,
			BindingResult result){
		Response<NivelExecucao> response = new Response<NivelExecucao>();
		
		try {
			validateCreateNivelExecucao(nivelExecucao, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			NivelExecucao nivelExecucaoPersisted = (NivelExecucao) nivelExecucaoService.createOrUpdate(nivelExecucao);
			response.setData(nivelExecucaoPersisted);
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
	
	private void validateCreateNivelExecucao(NivelExecucao nivelExecucao, BindingResult result) {
		if(nivelExecucao.getNome() == null) {
			result.addError(new ObjectError("NivelExecucao", "Nome é necessário"));
		}
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<NivelExecucao>> update(HttpServletRequest request,
			@RequestBody NivelExecucao nivelExecucao,
			BindingResult result) {
		Response<NivelExecucao> response = new Response<NivelExecucao>();
		
		try {
			validateUpdateNivelExecucao(nivelExecucao, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			NivelExecucao nivelExecucaoPersisted = (NivelExecucao) nivelExecucaoService.createOrUpdate(nivelExecucao);
			response.setData(nivelExecucaoPersisted);
		}
		catch(Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}

	private void validateUpdateNivelExecucao(NivelExecucao nivelExecucao, BindingResult result) {
		if(nivelExecucao.getId() == null) {
			result.addError(new ObjectError("NivelExecucao", "Id não informado"));
		}
		
		if(nivelExecucao.getNome() == null) {
			result.addError(new ObjectError("NivelExecucao", "Nome é necessário"));
		}
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<NivelExecucao>> findById(@PathVariable("id") String id){
		Response<NivelExecucao> response = new Response<NivelExecucao>();
		NivelExecucao nivelExecucao = nivelExecucaoService.findById(id);
		if(nivelExecucao == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(nivelExecucao);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id){
		Response<String> response = new Response<String>();
		NivelExecucao nivelExecucao = nivelExecucaoService.findById(id);
		if(nivelExecucao == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		nivelExecucaoService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<NivelExecucao>>> findAll(@PathVariable int page, @PathVariable int count){
		Response<Page<NivelExecucao>> response = new Response<Page<NivelExecucao>>();
		Page<NivelExecucao> niveisExecucao = nivelExecucaoService.findAll(page, count);
		response.setData(niveisExecucao);
		return ResponseEntity.ok(response);
	}
}
