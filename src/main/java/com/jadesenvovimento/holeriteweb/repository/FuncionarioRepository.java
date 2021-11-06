package com.jadesenvovimento.holeriteweb.repository;

import com.jadesenvovimento.holeriteweb.models.Funcionario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends MongoRepository<Funcionario, String> {
}
