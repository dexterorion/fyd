package com.fyd.sistema.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fyd.sistema.api.entity.Treino;

public interface TreinoRepository extends MongoRepository<Treino, String>{

}
