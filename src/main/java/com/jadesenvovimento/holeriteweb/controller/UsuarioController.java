package com.jadesenvovimento.holeriteweb.controller;


import com.jadesenvovimento.holeriteweb.exceptions.TokenNotFoundExcpetion;
import com.jadesenvovimento.holeriteweb.exceptions.UsuarioNotFountException;
import com.jadesenvovimento.holeriteweb.models.Usuario;
import com.jadesenvovimento.holeriteweb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    UsuarioService service;

    /**
     * recupera dados de um usuario
     *
     * @param token
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@RequestHeader(value = "token") String token, @PathVariable String id) {


        if (service.verificaTokenUsuario(token)) {
            Optional<Usuario> user = service.getUsuario(id);

            if (!user.isPresent()) {
                throw new UsuarioNotFountException("Usuario nao cadastrado na base de dados");
            }

            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            throw new TokenNotFoundExcpetion("Atenção TOKEN nao foi informado");
        }
    }


    /**
     * recupera todos os usuarios cadastrados na base
     *
     * @param token
     * @return ResponseEntity<List < Usuario>>
     */
    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getListaUsuarios(@RequestHeader(value = "token") String token) {

        if (service.verificaTokenUsuario(token)) {
            Optional<List<Usuario>> listaUsuarios = service.getListaUsuarios();
            return new ResponseEntity<>(listaUsuarios.get(), HttpStatus.OK);
        } else {
            throw new TokenNotFoundExcpetion("Token nao foi informado!");
        }

    }

    /**
     * Recupera dados de um usuario, conforme CPF passado como identificação
     * @param doc
     * @param token
     * @return
     */
    @GetMapping("/busca/{doc}")
    public ResponseEntity<Usuario> buscaUsuarioPorCpf(@PathVariable String doc, @RequestHeader(value = "token") String token) {

        if (service.verificaTokenUsuario(token)) {
            Optional<Usuario> usuario = service.buscarUsuarioPorCpf(doc);

            if (usuario.isPresent()) {

                return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
            } else {
                throw new UsuarioNotFountException("CPF informando nao cadastrado para nenhum usuario");
            }
        } else {
            throw new TokenNotFoundExcpetion("Token nao foi informado!");
        }

    }


    /**
     * Salva novo usuario na base de dados
     * @param usuario
     * @param token
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<Usuario> novoUsuario(@Valid @RequestBody Usuario usuario, @RequestHeader(value = "token") String token) {

        if (service.verificaTokenUsuario(token)) {
            Usuario novoUsuario = service.inserirNovoUsuario(usuario);
            return new ResponseEntity<Usuario>(novoUsuario, HttpStatus.OK);
        } else {
            throw new TokenNotFoundExcpetion("Token nao foi informado!");
        }

    }


}
