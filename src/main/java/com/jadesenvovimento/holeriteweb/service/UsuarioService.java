package com.jadesenvovimento.holeriteweb.service;

import com.jadesenvovimento.holeriteweb.models.Usuario;
import com.jadesenvovimento.holeriteweb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;


    /**
     * Verifica token informado, se existir token, o usuario é cadastrado
     *
     * @param token
     * @return
     */
    public boolean verificaTokenUsuario(String token) {
        Optional<Usuario> tokenUser = Optional.ofNullable(repository.findUsuario(token));
        return tokenUser.isPresent();
    }

    /**
     * recupera um usuario da base de dados
     *
     * @param id
     * @return
     */
    public Optional<Usuario> getUsuario(String id) {

        Optional<Usuario> ususarioToken = repository.findById(id);
        return ususarioToken;
    }


    /**
     * recupera uma lista de usuarios cadastrados
     *
     * @return
     */
    public Optional<List<Usuario>> getListaUsuarios() {
        Optional<List<Usuario>> lista = Optional.ofNullable(repository.findAll());
        return lista;
    }

    /**
     * Cadastra  novo usuario do sistema
     *
     * @param usuario
     * @return
     */
    public Usuario inserirNovoUsuario(Usuario usuario) {

        Usuario save = repository.save(usuario);
        return save;
    }

    /**
     * Busca um usuasrio pelo cpf informado
     * @param doc
     * @return
     */
    public Optional<Usuario> buscarUsuarioPorCpf(String doc){
        Optional<Usuario> usuarioCpf = Optional.ofNullable(repository.findByCpf(doc));
        return usuarioCpf;
    }
}
