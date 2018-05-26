package com.fyd.sistema.api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fyd.sistema.api.enums.TipoAquecimentoEnum;

@Document
public class Aquecimento {
	@Id
	private String id;
	
	@NotNull(message = "Tipo e necessario")
	private TipoAquecimentoEnum tipo;
	
	private String descricao;
	
	List<ExercicioAquecimento> exercicios = new ArrayList<ExercicioAquecimento>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TipoAquecimentoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoAquecimentoEnum tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ExercicioAquecimento> getExercicios() {
		return exercicios;
	}

	public void setExercicios(List<ExercicioAquecimento> exercicios) {
		this.exercicios = exercicios;
	}
}
