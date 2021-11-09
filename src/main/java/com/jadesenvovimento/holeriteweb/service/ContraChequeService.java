package com.jadesenvovimento.holeriteweb.service;

import com.jadesenvovimento.holeriteweb.models.ContraCheque;
import com.jadesenvovimento.holeriteweb.models.Funcionario;
import com.jadesenvovimento.holeriteweb.models.ProventoDescontoNeutro;
import com.jadesenvovimento.holeriteweb.models.TipoProventoDesconto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ContraChequeService {


    @Autowired
    FuncionarioService funcionarioService;


    /**
     * recebe o arquivo enviado pelo cliente e armazena no local padrao
     * sao dados dos Provenso/descontos/ neutros dos funcionarios
     *
     * @param file
     * @param destino
     */
    public void uploadArquivoProvDescFuncionarios(MultipartFile file, String destino) {

        try {
            Path caminho = Paths.get(destino);
            file.transferTo(caminho);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Retorna uma lista de funcionarios com seus proventos/descontos
     * de um determinado mes/ano
     *
     * @param caminhoAnexoIII
     */
    public List<ContraCheque> importarDadosContraChequeAnexoIII(String caminhoAnexoIII, String cnpj) {

        List<ContraCheque> contraCheqFuncionario = new ArrayList<>();
        try {

            Path arquivoAnexo = Paths.get(caminhoAnexoIII);
            List<String> linhas = Files.readAllLines(arquivoAnexo, StandardCharsets.ISO_8859_1);

            contraCheqFuncionario = gerarContraChequePorFuncionario(linhas, cnpj);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return contraCheqFuncionario;
    }


    /**
     * gera lista de contra-cheque de todos os funcionarios importados no arquivo ANEXOIV
     * cada funcionario com seus proventos/descontos referente a competencia informada
     *
     * @param dadosArquivoProvDesconto
     * @param cnpj
     * @return
     */
    private List<ContraCheque> gerarContraChequePorFuncionario(List<String> dadosArquivoProvDesconto, String cnpj) {

        List<ContraCheque> contraCheques = new ArrayList<>();

        // recupera a lista de FUNCIONARIOS do orgao
        Optional<List<Funcionario>> funcionarios = funcionarioService.recuperarListaFuncionariosPorCnpj(cnpj);

        // iteramos sobre a lista de funcionarios, onde é gerado
        // para cada funcionario, uma contracheque
        // logo adicionamos ao objeto contraCheques, cada funcionario com seus proventos/descontos
        funcionarios.ifPresent(fun -> {

            fun.stream().forEach(lstfun -> {

                // pegamos o funcionario q esta iterando
                Funcionario func = lstfun;
                // criamos objetos necessarios ao contracheque
                ContraCheque cntCh = new ContraCheque();

                // montamos a lista de obj com proventos de cada funcionario
                Object listaProvFunc[] = gerarListaProventos(dadosArquivoProvDesconto, func);

                cntCh.setFuncionario(func);
                cntCh.setProventos((List<ProventoDescontoNeutro>) listaProvFunc[0]);
                cntCh.setDescontos((List<ProventoDescontoNeutro>) listaProvFunc[1]);
                contraCheques.add(cntCh);

            });

        });
        return contraCheques;
    }


    /**
     * gera lista de proventos de cada funcionario
     * conforme ANEXOIV importado
     *
     * @param dadosArquivoProvDesc
     * @param func
     * @return
     */
    private Object[] gerarListaProventos(List<String> dadosArquivoProvDesc, Funcionario func) {

        Object listaProventos[] = new ArrayList[2];
        List<ProventoDescontoNeutro> proventos = new ArrayList<>();
        List<ProventoDescontoNeutro> descontos = new ArrayList<>();

        // iteramos na linha recuperada, q contem os dados de proventos/descontos de cada funcionario
        dadosArquivoProvDesc.stream().map(ln -> ln.split("#"))

                // captura o campo CPF da linha e compara com cpf q esta iterando
                .filter(array -> array[1].equalsIgnoreCase(func.getCpf()))

                // agora iteramos sobre o array retornado, com as posições do map
                .forEach(data -> {

                    ProventoDescontoNeutro prov = new ProventoDescontoNeutro();
                    // recuperamos o codigo da rubrica
                    int tipoProv = Integer.parseInt(data[7]);

                    if (tipoProv == TipoProventoDesconto.PROVENTO) {

                        prov.setCodigoRubrica(Integer.parseInt(data[3]));
                        prov.setDescricaoRubrica(data[4]);
                        prov.setValor(Double.parseDouble(data[6].replace(",", ".")));
                        proventos.add(prov);

                    } else if (tipoProv == TipoProventoDesconto.DESCONTO) {

                        prov.setCodigoRubrica(Integer.parseInt(data[3]));
                        prov.setDescricaoRubrica(data[4]);
                        prov.setValor(Double.parseDouble(data[6].replace(",", ".")));
                        descontos.add(prov);

                    }

                });
        listaProventos[0] = proventos;
        listaProventos[1] = descontos;

        return listaProventos;
    }

}