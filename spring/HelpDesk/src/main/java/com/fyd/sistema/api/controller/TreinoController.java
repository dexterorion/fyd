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

import com.fyd.sistema.api.entity.Exercicio;
import com.fyd.sistema.api.entity.ModalidadeTreino;
import com.fyd.sistema.api.entity.NivelExecucao;
import com.fyd.sistema.api.entity.TipoTreino;
import com.fyd.sistema.api.entity.Treino;
import com.fyd.sistema.api.response.Response;
import com.fyd.sistema.api.service.ExercicioService;
import com.fyd.sistema.api.service.ModalidadeTreinoService;
import com.fyd.sistema.api.service.NivelExecucaoService;
import com.fyd.sistema.api.service.TipoTreinoService;
import com.fyd.sistema.api.service.TreinoService;

@RestController
@RequestMapping("/api/treino")
@CrossOrigin(origins="*")
public class TreinoController {
	@Autowired
	private TreinoService treinoService;
	
	@Autowired
	private TipoTreinoService tipoTreinoService;
	
	@Autowired
	private ModalidadeTreinoService modalidadeTreinoService;
	
	@Autowired
	private ExercicioService exercicioService;
	
	@Autowired
	private NivelExecucaoService nivelExecucaoService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Treino>> create(HttpServletRequest request, @RequestBody Treino treino,
			BindingResult result){
		Response<Treino> response = new Response<Treino>();
		
		try {
			validateCreateTreino(treino, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Treino treinoPersisted = (Treino) treinoService.createOrUpdate(treino);
			response.setData(treinoPersisted);
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
	
	private void validateCreateTreino(Treino treino, BindingResult result) {
		if(treino.getTipo() == null) {
			result.addError(new ObjectError("Treino", "Tipo é necessário"));
		}
		else {
			TipoTreino tipo = tipoTreinoService.findById(treino.getTipo().getId());
			if(tipo == null) {
				result.addError(new ObjectError("Treino", "Tipo nao encontrado com id: "+treino.getTipo().getId()));
			}
			else {
				treino.setTipo(tipo);
			}
		}
		
		if(treino.getModalidade() == null) {
			result.addError(new ObjectError("Treino", "Modalide é necessária"));
		}
		else {
			ModalidadeTreino modalidade = modalidadeTreinoService.findById(treino.getModalidade().getId());
			if(modalidade == null) {
				result.addError(new ObjectError("Treino", "Modalidade nao encontrada com id: "+
							treino.getModalidade().getId()));
			}
			else {
				treino.setModalidade(modalidade);
			}
		}
		
		if(!treino.getExerciciosTreino().isEmpty()) {
			treino.getExerciciosTreino().forEach(exercicioTreino -> {
				if(exercicioTreino.getExercicio() == null) {
					result.addError(new ObjectError("Treino", 
							"Exercicio e necessario"));
				}
				else {
					Exercicio exercicio = exercicioService.findById(exercicioTreino.getExercicio().getId());
					if(exercicio == null) {
						result.addError(new ObjectError("Treino",
								"Exercicio nao encontrado com id: "+exercicioTreino.getExercicio().getId()));
					}
					else {
						exercicioTreino.setExercicio(exercicio);
					}
				}
				
				if(!exercicioTreino.getNiveisExecucao().isEmpty()) {
					exercicioTreino.getNiveisExecucao().forEach(nivelExecucao -> {
						if(nivelExecucao.getNivel() == null) {
							result.addError(new ObjectError("Treino",
									"Nivel e necessario"));
						}
						else {
							NivelExecucao nivel = nivelExecucaoService.findById(nivelExecucao.
									getNivel().getId());
							if(nivel == null) {
								result.addError(new ObjectError("Treino",
										"Nivel nao encontrado com id: "+nivelExecucao.
											getNivel().getId()));
							}
							else {
								nivelExecucao.setNivel(nivel);
							}
						}
						
						if(nivelExecucao.getSexo() == null) {
							result.addError(new ObjectError("Treino",
									"Sexo e necessario"));
						}
					});
				}
			});
		}
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Treino>> update(HttpServletRequest request,
			@RequestBody Treino treino,
			BindingResult result) {
		Response<Treino> response = new Response<Treino>();
		
		try {
			validateUpdateTreino(treino, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			Treino treinoPersisted = (Treino) treinoService.createOrUpdate(treino);
			response.setData(treinoPersisted);
		}
		catch(Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}

	private void validateUpdateTreino(Treino treino, BindingResult result) {
		if(treino.getId() == null) {
			result.addError(new ObjectError("Treino", "Id não informado"));
		}
		
		if(treino.getTipo() == null) {
			result.addError(new ObjectError("Treino", "Tipo é necessário"));
		}
		else {
			TipoTreino tipo = tipoTreinoService.findById(treino.getTipo().getId());
			if(tipo == null) {
				result.addError(new ObjectError("Treino", "Tipo nao encontrado com id: "+treino.getTipo().getId()));
			}
			else {
				treino.setTipo(tipo);
			}
		}
		
		if(treino.getModalidade() == null) {
			result.addError(new ObjectError("Treino", "Modalide é necessária"));
		}
		else {
			ModalidadeTreino modalidade = modalidadeTreinoService.findById(treino.getModalidade().getId());
			if(modalidade == null) {
				result.addError(new ObjectError("Treino", "Modalidade nao encontrada com id: "+
							treino.getModalidade().getId()));
			}
			else {
				treino.setModalidade(modalidade);
			}
		}
		
		if(!treino.getExerciciosTreino().isEmpty()) {
			treino.getExerciciosTreino().forEach(exercicioTreino -> {
				if(exercicioTreino.getExercicio() == null) {
					result.addError(new ObjectError("Treino", 
							"Exercicio e necessario"));
				}
				else {
					Exercicio exercicio = exercicioService.findById(exercicioTreino.getExercicio().getId());
					if(exercicio == null) {
						result.addError(new ObjectError("Treino",
								"Exercicio nao encontrado com id: "+exercicioTreino.getExercicio().getId()));
					}
					else {
						exercicioTreino.setExercicio(exercicio);
					}
				}
				
				if(!exercicioTreino.getNiveisExecucao().isEmpty()) {
					exercicioTreino.getNiveisExecucao().forEach(nivelExecucao -> {
						if(nivelExecucao.getNivel() == null) {
							result.addError(new ObjectError("Treino",
									"Nivel e necessario"));
						}
						else {
							NivelExecucao nivel = nivelExecucaoService.findById(nivelExecucao.
									getNivel().getId());
							if(nivel == null) {
								result.addError(new ObjectError("Treino",
										"Nivel nao encontrado com id: "+nivelExecucao.
											getNivel().getId()));
							}
							else {
								nivelExecucao.setNivel(nivel);
							}
						}
						
						if(nivelExecucao.getSexo() == null) {
							result.addError(new ObjectError("Treino",
									"Sexo e necessario"));
						}
					});
				}
			});
		}
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Treino>> findById(@PathVariable("id") String id){
		Response<Treino> response = new Response<Treino>();
		Treino treino = treinoService.findById(id);
		if(treino == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(treino);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id){
		Response<String> response = new Response<String>();
		Treino treino = treinoService.findById(id);
		if(treino == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		treinoService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<Treino>>> findAll(@PathVariable int page, @PathVariable int count){
		Response<Page<Treino>> response = new Response<Page<Treino>>();
		Page<Treino> tiposTreino = treinoService.findAll(page, count);
		response.setData(tiposTreino);
		return ResponseEntity.ok(response);
	}
}
