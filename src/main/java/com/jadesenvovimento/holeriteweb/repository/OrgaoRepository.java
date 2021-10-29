package com.jadesenvovimento.holeriteweb.repository;

import com.jadesenvovimento.holeriteweb.models.Orgao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgaoRepository extends MongoRepository<Orgao, String> {

    @Query("{'cnpj' : ?0}")
    Orgao findByCnpj(String cnpj);
}
