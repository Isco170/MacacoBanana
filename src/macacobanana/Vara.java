package macacobanana;

public class Vara {
    private String descricao;

    public Vara(String descricao) {
        this.descricao = descricao;
    }

    public Vara() {
        this.descricao = "VARA";
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
