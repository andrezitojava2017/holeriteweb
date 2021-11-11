package com.jadesenvovimento.holeriteweb.repository;

import com.jadesenvovimento.holeriteweb.models.ContraCheque;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContraChequeRepository extends MongoRepository<ContraCheque, String> {

    @Query("{'funcionario.id': ?0, 'competencia' : ?1}")
    ContraCheque findByFuncionario(String idFuncionario, String competencia);
}
