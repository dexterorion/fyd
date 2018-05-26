package com.fyd.sistema.api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Treino {
	@Id
	private String id;
	
	@DBRef(lazy=true)
	@NotNull(message="Tipo e necessario")
	private TipoTreino tipo; // Aquecimento, aquecimento especifico, treino geral, treino forca, workout...
	
	@DBRef(lazy=true)
	@NotNull(message="Modalidade e necessaria")
	private ModalidadeTreino modalidade; // AC1'M(5')...
	
	private List<ExercicioRepeticao> exerciciosTreino = new ArrayList<ExercicioRepeticao>();
	
	private List<String> observacoes = new ArrayList<String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TipoTreino getTipo() {
		return tipo;
	}

	public void setTipo(TipoTreino tipo) {
		this.tipo = tipo;
	}

	public ModalidadeTreino getModalidade() {
		return modalidade;
	}

	public void setModalidade(ModalidadeTreino modalidade) {
		this.modalidade = modalidade;
	}

	public List<ExercicioRepeticao> getExerciciosTreino() {
		return exerciciosTreino;
	}

	public void setExerciciosTreino(List<ExercicioRepeticao> exerciciosTreino) {
		this.exerciciosTreino = exerciciosTreino;
	}

	public List<String> getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(List<String> observacoes) {
		this.observacoes = observacoes;
	}
}
