package com.fyd.sistema.api.service.impl;

import org.springframework.stereotype.Service;

import com.fyd.sistema.api.entity.Exercicio;
import com.fyd.sistema.api.repository.ExercicioRepository;
import com.fyd.sistema.api.service.ExercicioService;

@Service
public class ExercicioServiceImpl extends BaseServiceImpl<Exercicio, ExercicioRepository>
	implements ExercicioService{

}
