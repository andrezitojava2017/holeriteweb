package com.jadesenvovimento.holeriteweb.controller;


import com.jadesenvovimento.holeriteweb.components.Resources;
import com.jadesenvovimento.holeriteweb.exceptions.TokenNotFoundExcpetion;
import com.jadesenvovimento.holeriteweb.exceptions.UsuarioNotFountException;
import com.jadesenvovimento.holeriteweb.models.Usuario;
import com.jadesenvovimento.holeriteweb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    UsuarioService service;

    @Autowired
    Resources re;

    /**
     * recupera dados de um usuario
     * neste endpoint o é rotornado também o orgao vinculado ao usuario
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
     * Neste endpoint o orgao vinculado nao retorna junto ao objeto usuario
     *
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
     *
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

    @PutMapping("/up/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable String id, @RequestBody Usuario usuario, @RequestHeader(value = "token") String token) {

        if (service.verificaTokenUsuario(token)) {

            Optional<Usuario> dadosUsuario = service.getUsuario(id);
            if (dadosUsuario.isPresent()) {
                Usuario usuarioAtualizado = service.atualizarUsuario(dadosUsuario.get(), usuario);
                return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
            }

        } else {
            throw new UsuarioNotFountException("Atenção token nao foi informado!!");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/upload")
    public ResponseEntity uploadListaFuncionarios(@RequestHeader(value = "token") String token,
                                                     @RequestParam("anexo") MultipartFile anexo) {

        if (service.verificaTokenUsuario(token)) {

            String[] split = anexo.getContentType().split("/");
            if (split[1].equalsIgnoreCase("plain")) {

                try {

                    // caminho definido no applicaton.yml
                    Path caminho = Paths.get(re.getCaminhoAnexo());
                    anexo.transferTo(caminho);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }


}
