package com.jadesenvovimento.holeriteweb.service;

import com.jadesenvovimento.holeriteweb.models.Orgao;
import com.jadesenvovimento.holeriteweb.repository.OrgaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrgaoService {

    @Autowired
    OrgaoRepository repository;


    /**
     * Faz a inserção de novo orgao na base de dados
     *
     * @param orgao
     * @return Orgao
     */
    public Orgao novoOrgao(Orgao orgao) {
        orgao.setId(null);
        Orgao org = repository.save(orgao);
        return org;
    }

    /**
     * Recupera lista de orgaos cadastrados
     *
     * @return Optional<List < Orgao>>
     */
    public Optional<List<Orgao>> getOrgaos() {

        return Optional.ofNullable(repository.findAll());
    }


    /**
     * Atualiza os dados de um determinado orgao
     * conforme ID informado e dados no objeto Orgao
     * @param id
     * @param orgao
     * @return
     */
    public Orgao atualizarOrgao(String id, Orgao orgao) {
        Optional<Orgao> byId = repository.findById(id);

        if (byId.isPresent()) {
            Orgao org = byId.get();
            org.setNomeOrgao(orgao.getNomeOrgao());
            org.setCidade(orgao.getCidade());
            org.setCnpj(orgao.getCnpj());
            org.setUf(orgao.getUf());
            repository.save(org);
            return org;
        }
        return null;
    }

    /**
     * Consulta um determinado orgao cadastrado
     * @param cnpj
     * @return
     */
    public Optional<Orgao> consultaOrgaoPorCnpj(String cnpj){
        Optional<Orgao> byCnpj = Optional.ofNullable(repository.findByCnpj(cnpj));
        return byCnpj;
    }
}
