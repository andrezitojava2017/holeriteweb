package com.jadesenvovimento.holeriteweb.controller;

import com.jadesenvovimento.holeriteweb.exceptions.OrgaoNotFount;
import com.jadesenvovimento.holeriteweb.exceptions.TokenNotFoundExcpetion;
import com.jadesenvovimento.holeriteweb.models.Orgao;
import com.jadesenvovimento.holeriteweb.service.OrgaoService;
import com.jadesenvovimento.holeriteweb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orgao")
public class OrgaoController {

    @Autowired
    private OrgaoService serviceOrgao;

    @Autowired
    private UsuarioService serviceUsuario;


    @GetMapping("/")
    public ResponseEntity<List<Orgao>> getListaOrgaos(@RequestHeader(value = "token") String token) {

        if (serviceUsuario.verificaTokenUsuario(token)) {

            Optional<List<Orgao>> orgaos = serviceOrgao.getOrgaos();
            if (orgaos.isPresent()) {
                return new ResponseEntity<>(orgaos.get(), HttpStatus.OK);
            }

        } else {
            throw new TokenNotFoundExcpetion("Atenção TOKEN nao foi informado");
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Orgao> novoOrgao(@RequestBody Orgao orgao, @RequestHeader(value = "token") String token) {

        if (serviceUsuario.verificaTokenUsuario(token)) {

            Orgao novoOrgao = serviceOrgao.novoOrgao(orgao);
            return new ResponseEntity<>(novoOrgao, HttpStatus.OK);

        } else {
            throw new TokenNotFoundExcpetion("Token nao foi informado!");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Orgao> atualizarOrgao(@PathVariable String id, @RequestBody Orgao orgao, @RequestHeader(value = "token") String token) {

        if (serviceUsuario.verificaTokenUsuario(token)) {
            Orgao orgAtualizado = serviceOrgao.atualizarOrgao(id, orgao);
            return new ResponseEntity<>(orgAtualizado, HttpStatus.OK);
        } else {
            throw new TokenNotFoundExcpetion("Token nao foi informado!");
        }

    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<Orgao> consultaOrgaoPorCnpj(@PathVariable String cnpj, @RequestHeader(value = "token") String token) {

        if (serviceUsuario.verificaTokenUsuario(token)) {
            Optional<Orgao> orgao = serviceOrgao.consultaOrgaoPorCnpj(cnpj);
            if (orgao.isPresent()) {
                return new ResponseEntity<>(orgao.get(), HttpStatus.OK);
            } else {
                throw new OrgaoNotFount("CNPJ nao foi localizado na base de dados");
            }
        } else {
            throw new TokenNotFoundExcpetion("Token nao foi informado!");
        }
    }

}
