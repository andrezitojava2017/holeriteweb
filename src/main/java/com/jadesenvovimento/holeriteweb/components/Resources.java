package com.jadesenvovimento.holeriteweb.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Resources {

    @Value("${resource.caminhoAnexo}")
    private String caminhoAnexo;

    @Value("${resource.caminhoContraCheque}")
    private String caminhoContraCheque;

    public String getCaminhoContraCheque() {
        return caminhoContraCheque;
    }

    public void setCaminhoContraCheque(String caminhoContraCheque) {
        this.caminhoContraCheque = caminhoContraCheque;
    }

    public String getCaminhoAnexo() {
        return caminhoAnexo;
    }

    public void setCaminhoAnexo(String caminhoAnexo) {
        this.caminhoAnexo = caminhoAnexo;
    }
}
