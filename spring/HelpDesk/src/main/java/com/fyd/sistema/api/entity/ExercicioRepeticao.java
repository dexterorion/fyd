package com.fyd.sistema.api.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ExercicioRepeticao {
	@DBRef(lazy=true)
	@NotBlank(message = "Exercicio e necessario")
	private Exercicio exercicio;
	
	private Integer numeroSeries; // quantidade de series que ira realizar
	
	private Integer repeticoes; // numero de repeticoes por serie
	
	private Integer tempo; // tempo por serie
	
	private List<NivelExecucaoSexo> niveisExecucao = new ArrayList<NivelExecucaoSexo>();
	
	private String descricao;

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public Integer getRepeticoes() {
		return repeticoes;
	}

	public void setRepeticoes(Integer repeticoes) {
		this.repeticoes = repeticoes;
	}

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getNumeroSeries() {
		return numeroSeries;
	}

	public void setNumeroSeries(Integer numeroSeries) {
		this.numeroSeries = numeroSeries;
	}

	public List<NivelExecucaoSexo> getNiveisExecucao() {
		return niveisExecucao;
	}

	public void setNiveisExecucao(List<NivelExecucaoSexo> niveisExecucao) {
		this.niveisExecucao = niveisExecucao;
	}
}
