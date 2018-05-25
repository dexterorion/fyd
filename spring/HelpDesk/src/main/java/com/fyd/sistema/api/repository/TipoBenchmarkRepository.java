package com.fyd.sistema.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fyd.sistema.api.entity.TipoBenchmark;

public interface TipoBenchmarkRepository extends MongoRepository<TipoBenchmark, String>{

}
