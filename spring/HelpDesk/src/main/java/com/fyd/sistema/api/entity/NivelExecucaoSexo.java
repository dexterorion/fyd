package com.fyd.sistema.api.entity;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fyd.sistema.api.enums.SexoEnum;

@Document
public class NivelExecucaoSexo {
	@DBRef(lazy=true)
	@NotNull(message="Nivel e necessario")
	private NivelExecucao nivel; // /basico, intermediario, avancado...
	
	@NotNull(message="Sexo e necessario")
	private SexoEnum sexo;

	public NivelExecucao getNivel() {
		return nivel;
	}

	public void setNivel(NivelExecucao nivel) {
		this.nivel = nivel;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}
}
