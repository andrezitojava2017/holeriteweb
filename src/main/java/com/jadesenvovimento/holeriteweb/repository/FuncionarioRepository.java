package com.jadesenvovimento.holeriteweb.repository;

import com.jadesenvovimento.holeriteweb.models.Funcionario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends MongoRepository<Funcionario, String> {

    @Query("{'orgao.cnpj': ?0}")
    List<Funcionario> findByCnpj(String cnpj);

    @Query("{'cpf': ?0}")
    Funcionario findByCpf(String cpf);
}
