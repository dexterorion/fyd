package com.fyd.sistema.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fyd.sistema.api.entity.Exercicio;
import com.fyd.sistema.api.entity.FuncaoExercicio;
import com.fyd.sistema.api.entity.PilarExercicio;
import com.fyd.sistema.api.response.Response;
import com.fyd.sistema.api.service.ExercicioService;
import com.fyd.sistema.api.service.FuncaoExercicioService;
import com.fyd.sistema.api.service.PilarExercicioService;

@RestController
@RequestMapping("/api/exercicio")
@CrossOrigin(origins="*")
public class ExercicioController {
	@Autowired
	private ExercicioService exercicioService;
	
	@Autowired
	private PilarExercicioService pilarExercicioService;
	
	@Autowired
	private FuncaoExercicioService funcaoExercicioService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Exercicio>> create(HttpServletRequest request, @RequestBody Exercicio exercicio,
			BindingResult result){
		Response<Exercicio> response = new Response<Exercicio>();
		
		try {
			validateCreateExercicio(exercicio, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Exercicio exercicioPersisted = (Exercicio) exercicioService.createOrUpdate(exercicio);
			response.setData(exercicioPersisted);
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
	
	private void validateCreateExercicio(Exercicio exercicio, BindingResult result) {
		if(exercicio.getNome() == null) {
			result.addError(new ObjectError("Exercicio", "Nome é necessário"));
		}
		if(exercicio.getPilar() == null) {
			result.addError(new ObjectError("Exercicio", "Pilar é necessário"));
		}
		else {
			PilarExercicio pilar = pilarExercicioService.findById(exercicio.getPilar().getId());
			if(pilar == null)
				result.addError(new ObjectError("Exercicio", "Pilar com id inexistente"));
			else
				exercicio.setPilar(pilar);
		}
		
		if(exercicio.getFuncao() == null) {
			result.addError(new ObjectError("Exercicio", "Funcao é necessária"));
		}
		else {
			FuncaoExercicio funcao = funcaoExercicioService.findById(exercicio.getFuncao().getId());
			if(funcao == null)
				result.addError(new ObjectError("Exercicio", "Funcao com id inexistente"));
			else
				exercicio.setFuncao(funcao);
		}
		
		
		if(exercicio.getDescricaoTecnica() == null) {
			result.addError(new ObjectError("Exercicio", "Descricao tecnica é necessária"));
		}
		
	}

}
