package com.fyd.sistema.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fyd.sistema.api.entity.TipoTreino;

public interface TipoTreinoRepository extends MongoRepository<TipoTreino, String>{

}
