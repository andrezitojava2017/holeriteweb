package com.jadesenvovimento.holeriteweb.service;

import com.jadesenvovimento.holeriteweb.models.Cargo;
import com.jadesenvovimento.holeriteweb.models.Funcionario;
import com.jadesenvovimento.holeriteweb.models.Orgao;
import com.jadesenvovimento.holeriteweb.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {


    @Autowired
    FuncionarioRepository repository;


    /**
     * Metodo para ler o arquivo ANEXO1 com dados dos funcionarios
     * que serao persistidos na base de dados
     * retorna uma lista de funcionarios
     *
     * @param caminhoArquivo
     * @param orgao
     * @return
     */
    public List<Funcionario> lerArquivoListaFuncionarios(String caminhoArquivo, Orgao orgao) {
        List<Funcionario> lst = new ArrayList<>();
        try {

            Path anexo1 = Paths.get(caminhoArquivo);
            List<String> linha = Files.readAllLines(anexo1, StandardCharsets.ISO_8859_1);

            // remove a primeira linha, cabeçalho do arquivo
            linha.remove(0);

            linha.stream()
                    .map(ff -> ff.split("#"))
                    .forEach(ln -> {
                        //ln[2] nome do funcionario
                        //ln[3] CPF do funcinario
                        //ln[4] matricula
                        //ln[6] cargo
                        //ln[8] admissao
                        //ln[11] carga horaria

                        Funcionario novo = new Funcionario(
                                ln[2], // nome
                                ln[3], // cpf
                                new Cargo(ln[6], this.formatData(ln[8])),
                                orgao
                        );

                        lst.add(novo);

                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lst;
    }

    /**
     * cria arquivo no destino padrao
     *
     * @param file
     * @param destino
     */
    public void uploadArquivo(MultipartFile file, String destino) {

        try {

            Path DestArquivo = Paths.get(destino);
            file.transferTo(DestArquivo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Optional<List<Funcionario>> recuperarListaFuncionariosPorCnpj(String cnpj) {

        Optional<List<Funcionario>> all = Optional.ofNullable(repository.findByCnpj(cnpj));
        return all;
    }


    /**
     * recupera um funcionario pelo CPF informado
     *
     * @param cpf
     * @return
     */
    public Optional<Funcionario> funcionarioPorCpf(String cpf) {
        Optional<Funcionario> funCpf = Optional.ofNullable(repository.findByCpf(cpf));
        return funCpf;
    }

    /**
     * metodo que recupera um funcionario pelo ID
     *
     * @param id
     * @return
     */
    public Optional<Funcionario> funcionarioPorId(String id) {

        Optional<Funcionario> byId = repository.findById(id);
        return byId;
    }

    /**
     * Metodo para ataualizar dados de um ususario
     *
     * @param funcAntigo
     * @return
     */
    public Funcionario atualizarDadosFuncinario(Funcionario funcAntigo, Funcionario funcNovo) {

        funcNovo.setId(funcAntigo.getId());
        Funcionario save = repository.save(funcNovo);
        return save;
    }

    /**
     * metodo que recebe uma data em string e converte para LocalDate
     *
     * @param dat
     * @return
     */
    private LocalDate formatData(String dat) {

        DateTimeFormatter dtfm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dat, dtfm);
        // System.out.println(data);
        return data;
    }
}
