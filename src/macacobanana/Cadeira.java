package macacobanana;

public class Cadeira {
    private String descricao;

    public Cadeira() {
        this.descricao = "CADEIRA";
    }

    public Cadeira(String descricao) {
        this.descricao = descricao;
    }
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
