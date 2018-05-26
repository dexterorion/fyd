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

import com.fyd.sistema.api.entity.Benchmark;
import com.fyd.sistema.api.entity.Treino;
import com.fyd.sistema.api.response.Response;
import com.fyd.sistema.api.service.BenchmarkService;
import com.fyd.sistema.api.service.TreinoService;

@RestController
@RequestMapping("/api/benchmark")
@CrossOrigin(origins="*")
public class BenchmarkController {
	@Autowired
	private BenchmarkService benchmarkService;
	
	@Autowired
	private TreinoService treinoService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Benchmark>> create(HttpServletRequest request, @RequestBody Benchmark benchmark,
			BindingResult result){
		Response<Benchmark> response = new Response<Benchmark>();
		
		try {
			validateCreateBenchmark(benchmark, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Benchmark benchmarkPersisted = (Benchmark) benchmarkService.createOrUpdate(benchmark);
			response.setData(benchmarkPersisted);
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
	
	private void validateCreateBenchmark(Benchmark benchmark, BindingResult result) {
		if(benchmark.getTreinos().isEmpty())
			result.addError(new ObjectError("Benchmark", "Treinos sao necessarios"));
		else {
			benchmark.getTreinos().forEach(treinoBenchmark -> {
				Treino treino = treinoService.findById(treinoBenchmark.getId());
				if(treino == null) {
					result.addError(new ObjectError("Benchmark", 
							"Treino nao encontrado com id: "+treinoBenchmark.getId()));
				}
			});
		}
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Benchmark>> update(HttpServletRequest request, @RequestBody Benchmark benchmark,
			BindingResult result){
		Response<Benchmark> response = new Response<Benchmark>();
		
		try {
			validateUpdateBenchmark(benchmark, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Benchmark benchmarkPersisted = (Benchmark) benchmarkService.createOrUpdate(benchmark);
			response.setData(benchmarkPersisted);
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
	
	private void validateUpdateBenchmark(Benchmark benchmark, BindingResult result) {
		if(benchmark.getId() == null) {
			result.addError(new ObjectError("Benchmark", "Id não informado"));
		}
		
		if(benchmark.getTreinos().isEmpty())
			result.addError(new ObjectError("Benchmark", "Treinos sao necessarios"));
		else {
			benchmark.getTreinos().forEach(treinoBenchmark -> {
				Treino treino = treinoService.findById(treinoBenchmark.getId());
				if(treino == null) {
					result.addError(new ObjectError("Benchmark", 
							"Treino nao encontrado com id: "+treinoBenchmark.getId()));
				}
			});
		}
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Benchmark>> findById(@PathVariable("id") String id){
		Response<Benchmark> response = new Response<Benchmark>();
		Benchmark benchmark = benchmarkService.findById(id);
		if(benchmark == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(benchmark);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id){
		Response<String> response = new Response<String>();
		Benchmark benchmark = benchmarkService.findById(id);
		if(benchmark == null) {
			response.getErrors().add("Registro não encontrado para o id: "+id);
			return ResponseEntity.badRequest().body(response);
		}
		
		benchmarkService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<Benchmark>>> findAll(@PathVariable int page, @PathVariable int count){
		Response<Page<Benchmark>> response = new Response<Page<Benchmark>>();
		Page<Benchmark> benchmarks = benchmarkService.findAll(page, count);
		response.setData(benchmarks);
		return ResponseEntity.ok(response);
	}

}
