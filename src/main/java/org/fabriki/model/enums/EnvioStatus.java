package org.fabriki.model.enums;

public enum EnvioStatus {
    correto(0), incorreto(1), erro(2, "não compila"), processando(999, "processando...");

    private int codigo;
    private String descricao;

    EnvioStatus(int codigo) {
        this.codigo = codigo;
        this.descricao = name();
    }

    EnvioStatus(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
