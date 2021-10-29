package com.jadesenvovimento.holeriteweb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Funcionario extends MongoRepository<Funcionario, String> {
}
