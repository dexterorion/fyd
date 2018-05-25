package com.fyd.sistema.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fyd.sistema.api.entity.Exercicio;

public interface ExercicioRepository extends MongoRepository<Exercicio, String>{

}
