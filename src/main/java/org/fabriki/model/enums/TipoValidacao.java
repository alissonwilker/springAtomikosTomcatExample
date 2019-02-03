package org.fabriki.model.enums;

public enum TipoValidacao {
    executarTestesEntradaESaida("Testes de entrada e saída"), executarTestesUnitarios(
        "Testes unitários"), executarTestesIntegracao("Testes de integração");

    private String descricao;

    TipoValidacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
