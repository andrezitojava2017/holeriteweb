package com.jadesenvovimento.holeriteweb.controller;

import com.jadesenvovimento.holeriteweb.components.Resources;
import com.jadesenvovimento.holeriteweb.exceptions.FuncionarioException;
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
    public ResponseEntity<String> uploadDadosFuncionario(
            @RequestHeader(value = "token") String token,
            @RequestHeader(value = "cnpj") String cnpj,
            @RequestParam("anexo") MultipartFile anexo) {

        if (usuario.verificaTokenUsuario(token)) {

            String[] split = anexo.getContentType().split("/");
            if (split[1].equals("plain")) {
                Optional<Orgao> org = this.orgao.consultaOrgaoPorCnpj(cnpj);

                if (org.isPresent()) {

                    // cria o arquivo no diretorio padrao
                    service.uploadArquivo(anexo, re.getCaminhoAnexo());

                    //iremos ler o arquivo  e retornar uma lista de funcionarios
                    List<Funcionario> funcionarios = service.lerArquivoListaFuncionarios(re.getCaminhoAnexo(), org.get());

                    List<Funcionario> lstFun = funcRepository.saveAll(funcionarios);

                } else {
                    throw new OrgaoNotFount("CNPJ informado nao existe na base de dados");
                }
            } else {
                return new ResponseEntity("Arquivo informado com extensão invalida", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            throw new TokenNotFoundExcpetion("Token invalido!");
        }
        return new ResponseEntity<>("Funcionarios salvos com sucesso!", HttpStatus.OK);
    }



    @PostMapping("/novo")
    public ResponseEntity<Funcionario> novoFuncionario(@RequestBody Funcionario novoFuncionario,
                                                       @RequestHeader(value = "token") String token) {

        if (usuario.verificaTokenUsuario(token)) {

            return service.novoFuncionario(novoFuncionario);

        } else {
            throw new TokenNotFoundExcpetion("TOKEN nao foi informado!!");
        }

    }


    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<List<Funcionario>> getListaFuncionariosPorCnpj(@RequestHeader(value = "token") String token,
                                                                         @PathVariable String cnpj) {
        if (usuario.verificaTokenUsuario(token)) {

            Optional<List<Funcionario>> listaFuncionarios = service.recuperarListaFuncionariosPorCnpj(cnpj);

            if (listaFuncionarios.isPresent()) {
                return new ResponseEntity<List<Funcionario>>(listaFuncionarios.get(), HttpStatus.OK);
            }
        }

        throw new TokenNotFoundExcpetion("Token nao foi informado!");
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Funcionario> getFuncionarioPorCpf(@PathVariable String cpf,
                                                            @RequestHeader(value = "token") String token) {
        if (usuario.verificaTokenUsuario(token)) {

            Optional<Funcionario> cpfFun = service.funcionarioPorCpf(cpf);
            return new ResponseEntity<Funcionario>(cpfFun.get(), HttpStatus.OK);
        }
        throw new TokenNotFoundExcpetion("Token nao foi informado!");

    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atuaizarDadosFuncionario(@RequestBody Funcionario func,
                                                                @RequestHeader(value = "token") String token,
                                                                @PathVariable String id) {

        if (usuario.verificaTokenUsuario(token)) {
            Optional<Funcionario> funcAntigo = service.funcionarioPorId(id);

            if (funcAntigo.isPresent()) {

                Funcionario funcAtualizado = service.atualizarDadosFuncinario(funcAntigo.get(), func);
                return new ResponseEntity<>(funcAtualizado, HttpStatus.OK);
            }

        } else {
            throw new TokenNotFoundExcpetion("TOKEN nao foi informado!");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
