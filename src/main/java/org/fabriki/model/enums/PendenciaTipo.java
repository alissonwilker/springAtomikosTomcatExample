package org.fabriki.model.enums;

public enum PendenciaTipo {
    inscricao("Inscrição em turma"), instrutoria("Convite para instrutoria"), instituicao(
        "Convite para instituição"), coordenadoria(
            "Convite para coordenadoria"), acessoInstituicao("Solicitação de acesso à instituição");
    
    private String descricao;
    
    PendenciaTipo(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}
