package com.fyd.sistema.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fyd.sistema.api.entity.Aquecimento;

public interface AquecimentoRepository extends MongoRepository<Aquecimento, String>{

}
