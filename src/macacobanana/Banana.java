package macacobanana;

public class Banana {
    private int quantidade;
    private String descricao;

    public Banana() {
        this.quantidade = 10;
        this.descricao = "BANANA";
    }

    public Banana(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
    
}
