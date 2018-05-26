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

import com.fyd.sistema.api.entity.Aquecimento;
import com.fyd.sistema.api.entity.Exercicio;
import com.fyd.sistema.api.response.Response;
import com.fyd.sistema.api.service.AquecimentoService;
import com.fyd.sistema.api.service.ExercicioService;

@RestController
@RequestMapping("/api/aquecimento")
@CrossOrigin(origins="*")
public class AquecimentoController {
	@Autowired
	private AquecimentoService aquecimentoService;
	
	@Autowired
	private ExercicioService exercicioService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Aquecimento>> create(HttpServletRequest request, @RequestBody Aquecimento aquecimento,
			BindingResult result){
		Response<Aquecimento> response = new Response<Aquecimento>();
		
		try {
			validateCreateAquecimento(aquecimento, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Aquecimento aquecimentoPersisted = (Aquecimento) aquecimentoService.createOrUpdate(aquecimento);
			response.setData(aquecimentoPersisted);
		}
		catch(Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
		
	}
	
	private void validateCreateAquecimento(Aquecimento aquecimento, BindingResult result) {
		if(aquecimento.getTipo() == null) {
			result.addError(new ObjectError("Aquecimento", "Tipo e necessario"));
		}
		
		if(!aquecimento.getExercicios().isEmpty()) {
			aquecimento.getExercicios().forEach(exercicioAquecimento -> {
				if(exercicioAquecimento.getExercicio() == null) {
					result.addError(new ObjectError("Aquecimento", "Exercicio e necessario"));
				}
				else {
					Exercicio exercicio = exercicioService.findById(exercicioAquecimento.getExercicio().getId());
					if(exercicio == null) {
						result.addError(new ObjectError("Aquecimento",
								"Nao existe exercicio que corresponde ao id: "+exercicioAquecimento.getExercicio().getId()));
					}
					else {
						exercicioAquecimento.setExercicio(exercicio);
					}
				}
				
				if(exercicioAquecimento.getNumeroSeries() == null) {
					result.addError(new ObjectError("Aquecimento", "Numero de series e necessario"));
				}
			});
		}
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Aquecimento>> update(HttpServletRequest request, @RequestBody Aquecimento aquecimento,
			BindingResult result){
		Response<Aquecimento> response = new Response<Aquecimento>();
		
		try {
			validateUpdateAquecimento(aquecimento, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Aquecimento aquecimentoPersisted = (Aquecimento) aquecimentoService.createOrUpdate(aquecimento);
			response.setData(aquecimentoPersisted);
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
	
	private void validateUpdateAquecimento(Aquecimento aquecimento, BindingResult result) {
		if(aquecimento.getId() == null) {
			result.addError(new ObjectError("Aquecimento", "Id não informado"));
		}
		
		if(aquecimento.getTipo() == null) {
			result.addError(new ObjectError("Aquecimento", "Tipo e necessario"));
		}
		
		if(!aquecimento.getExercicios().isEmpty()) {
			aquecimento.getExercicios().forEach(exercicioAquecimento -> {
				if(exercicioAquecimento.getExercicio() == null) {
					result.addError(new ObjectError("Aquecimento", "Exercicio e necessario"));
				}
				else {
					Exercicio exercicio = exercicioService.findById(exercicioAquecimento.getExercicio().getId());
					if(exercicio == null) {
						result.addError(new ObjectError("Aquecimento",
								"Nao existe exercicio que corresponde ao id: "+exercicioAquecimento.getExercicio().getId()));
					}
					else {
						exercicioAquecimento.setExercicio(exercicio);
					}
				}
				
				if(exercicioAquecimento.getNumeroSeries() == null) {
					result.addError(new ObjectError("Aquecimento", "Numero de series e necessario"));
				}
			});
		}
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Aquecimento>> findById(@PathVariable("id") String id){
		Response<Aquecimento> response = new Response<Aquecimento>();
		Aquecimento aquecimento = aquecimentoService.findById(id);
		if(aquecimento == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(aquecimento);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id){
		Response<String> response = new Response<String>();
		Aquecimento aquecimento = aquecimentoService.findById(id);
		if(aquecimento == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		aquecimentoService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<Aquecimento>>> findAll(@PathVariable int page, @PathVariable int count){
		Response<Page<Aquecimento>> response = new Response<Page<Aquecimento>>();
		Page<Aquecimento> aquecimentos = aquecimentoService.findAll(page, count);
		response.setData(aquecimentos);
		return ResponseEntity.ok(response);
	}

}
