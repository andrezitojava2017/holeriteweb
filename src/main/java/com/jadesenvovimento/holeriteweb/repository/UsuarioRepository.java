package com.jadesenvovimento.holeriteweb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.jadesenvovimento.holeriteweb.models.Usuario;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    @Query("{ 'token' : ?0 }")
    Usuario findUsuario(String token);

    @Query("{'cpf' : ?0}")
    Usuario findByCpf(String cpf);


}
