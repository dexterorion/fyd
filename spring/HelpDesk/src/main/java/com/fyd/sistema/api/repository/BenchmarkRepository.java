package com.fyd.sistema.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fyd.sistema.api.entity.Benchmark;

public interface BenchmarkRepository extends MongoRepository<Benchmark, String>{

}
