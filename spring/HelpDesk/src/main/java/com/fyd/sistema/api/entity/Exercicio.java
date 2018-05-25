package com.fyd.sistema.api.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Exercicio {
	@Id
	private String id;
	
	@Indexed(unique = true)
	@NotBlank(message = "Nome é necessário")
	private String nome;
	
	@NotBlank(message = "Pilar é necessário")
	@DBRef(lazy=true)
	private PilarExercicio pilar;
	
	@NotBlank(message = "Funcao é necessário")
	@DBRef(lazy=true)
	private FuncaoExercicio funcao;
	
	@NotBlank(message = "Descricao tecnica e necessária")
	private String descricaoTecnica;
	
	private List<String> adaptacoes = new ArrayList<String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public PilarExercicio getPilar() {
		return pilar;
	}

	public void setPilar(PilarExercicio pilar) {
		this.pilar = pilar;
	}

	public FuncaoExercicio getFuncao() {
		return funcao;
	}

	public void setFuncao(FuncaoExercicio funcao) {
		this.funcao = funcao;
	}

	public String getDescricaoTecnica() {
		return descricaoTecnica;
	}

	public void setDescricaoTecnica(String descricaoTecnica) {
		this.descricaoTecnica = descricaoTecnica;
	}

	public List<String> getAdaptacoes() {
		return adaptacoes;
	}

	public void setAdaptacoes(List<String> adaptacoes) {
		this.adaptacoes = adaptacoes;
	}
}
