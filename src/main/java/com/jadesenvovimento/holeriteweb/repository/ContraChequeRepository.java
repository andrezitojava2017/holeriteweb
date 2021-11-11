package com.jadesenvovimento.holeriteweb.repository;

import com.jadesenvovimento.holeriteweb.models.ContraCheque;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContraChequeRepository extends MongoRepository<ContraCheque, String> {
}
