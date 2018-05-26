package com.fyd.sistema.api.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ExercicioAquecimento {
	@DBRef(lazy=true)
	@NotBlank(message = "Exercicio e necessario")
	private Exercicio exercicio;
	
	@NotBlank(message = "Numero series e necessario")
	private Integer numeroSeries;
	
	private Integer repeticoes;
	
	private Integer tempo;
	
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
}
