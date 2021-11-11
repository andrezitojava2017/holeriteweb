package com.jadesenvovimento.holeriteweb.controller;

import com.jadesenvovimento.holeriteweb.components.Resources;
import com.jadesenvovimento.holeriteweb.exceptions.TokenNotFoundExcpetion;
import com.jadesenvovimento.holeriteweb.models.ContraCheque;
import com.jadesenvovimento.holeriteweb.service.ContraChequeService;
import com.jadesenvovimento.holeriteweb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/contracheque")
public class ContraChequeController {

    @Autowired
    UsuarioService usuarioSerive;

    @Autowired
    ContraChequeService cntChequeService;

    @Autowired
    Resources resources;


    @PostMapping("/upload/{cnpj}/{competencia}")
    public ResponseEntity<ContraCheque> uploadArquivoContraCheque(@RequestParam(value = "anexoIII") MultipartFile anexoIII,
                                                                  @RequestHeader(value = "token") String token,
                                                                  @PathVariable String cnpj,
                                                                  @PathVariable String competencia) {

        if (usuarioSerive.verificaTokenUsuario(token)) {

            // cria arquivo txt no servidor
            cntChequeService.uploadArquivoProvDescFuncionarios(anexoIII, resources.getCaminhoContraCheque());

            // inicia leitura arquivo
            List<ContraCheque> contraCheques = cntChequeService.importarDadosContraChequeAnexoIII(resources.getCaminhoContraCheque(), cnpj, competencia);

            // grava no banco de dados os contra cheques gerados
            List<ContraCheque> resultContraCheques = cntChequeService.salvarContraChequesFuncionarios(contraCheques);

            return new ResponseEntity(resultContraCheques, HttpStatus.OK);


        } else {
            throw new TokenNotFoundExcpetion("TOKEN nao foi informado!!");
        }

    }

    @GetMapping("/{id}/{competencia}")
    public ResponseEntity<ContraCheque> contraChequeFuncionario(@RequestHeader(value = "token") String token,
                                                  @PathVariable String id,
                                                  @PathVariable String competencia){

        if(usuarioSerive.verificaTokenUsuario(token)){

            ContraCheque contraCheque = cntChequeService.contraChequePorFuncionario(id, competencia);
            return new ResponseEntity(contraCheque,HttpStatus.OK);
        } else {
            throw new TokenNotFoundExcpetion("TOKEN nao foi informado!!");
        }

    }

}
