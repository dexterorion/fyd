package com.fyd.sistema.api.service.impl;

import org.springframework.stereotype.Service;

import com.fyd.sistema.api.entity.Benchmark;
import com.fyd.sistema.api.repository.BenchmarkRepository;
import com.fyd.sistema.api.service.BenchmarkService;

@Service
public class BenchmarkServiceImpl extends BaseServiceImpl<Benchmark, BenchmarkRepository>
	implements BenchmarkService{
}
