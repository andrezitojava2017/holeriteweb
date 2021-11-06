package com.jadesenvovimento.holeriteweb.controller;

import com.jadesenvovimento.holeriteweb.components.Resources;
import com.jadesenvovimento.holeriteweb.exceptions.OrgaoNotFount;
import com.jadesenvovimento.holeriteweb.exceptions.TokenNotFoundExcpetion;
import com.jadesenvovimento.holeriteweb.models.Funcionario;
import com.jadesenvovimento.holeriteweb.models.Orgao;
import com.jadesenvovimento.holeriteweb.repository.FuncionarioRepository;
import com.jadesenvovimento.holeriteweb.service.FuncionarioService;
import com.jadesenvovimento.holeriteweb.service.OrgaoService;
import com.jadesenvovimento.holeriteweb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioService service;

    @Autowired
    FuncionarioRepository funcRepository;

    @Autowired
    UsuarioService usuario;

    @Autowired
    Resources re;

    @Autowired
    OrgaoService orgao;

    @PostMapping("/")
    public ResponseEntity<String> funcionario(
            @RequestHeader(value = "token") String token,
            @RequestHeader(value = "cnpj") String cnpj,
            @RequestParam("anexo") MultipartFile anexo) {

        if(usuario.verificaTokenUsuario(token)){

            String[] split = anexo.getContentType().split("/");
            if(split[1].equals("plain")){
                Optional<Orgao> org = this.orgao.consultaOrgaoPorCnpj(cnpj);

                if (org.isPresent()){

                    //iremos ler o arquivo  e retornar uma lista de funcionarios
                    List<Funcionario> funcionarios = service.lerArquivoListaFuncionarios(re.getCaminhoAnexo(), org.get());

                    List<Funcionario> lstFun = funcRepository.saveAll(funcionarios);

                    lstFun.forEach(fn->{
                        System.out.println(">>>>>> " + fn.getId() + " -  " + fn.getNomeFuncionario());
                    } );

                } else {
                    throw new OrgaoNotFount("CNPJ informado nao existe na base de dados");
                }
            } else {
                return new ResponseEntity("Arquivo informado com extensão invalida",HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            throw new TokenNotFoundExcpetion("Token invalido!");
        }
    return new ResponseEntity<>("Funcionarios salvos com sucesso!", HttpStatus.OK);
    }


}
