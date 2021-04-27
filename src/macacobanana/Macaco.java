package macacobanana;

public class Macaco {
    private String descricao;
    private int posicaoLinha;
    private int posicaoColuna;
    private boolean cadeiraPosicao;
    
    private int linhaAnterior;
    private int colunaAnterior;
    
    private int proximaLinha;
    private int proximaColuna;
        
    private Cadeira cadeira;
    private Vara vara;

    public Macaco(String descricao, Cadeira cadeira, Vara vara) {
        this.descricao = descricao;
        this.cadeira = cadeira;
        this.vara = vara;
    }

    public Macaco(String descricao) {
        this.descricao = descricao;
    }

    public int getPosicaoLinha() {
        return posicaoLinha;
    }

    public void setPosicaoLinha(int posicaoLinha) {
        this.posicaoLinha = posicaoLinha;
    }

    public int getPosicaoColuna() {
        return posicaoColuna;
    }

    public void setPosicaoColuna(int posicaoColuna) {
        this.posicaoColuna = posicaoColuna;
    }

    public int getLinhaAnterior() {
        return linhaAnterior;
    }

    public void setLinhaAnterior(int linhaAnterior) {
        this.linhaAnterior = linhaAnterior;
    }

    public int getColunaAnterior() {
        return colunaAnterior;
    }

    public void setColunaAnterior(int colunaAnterior) {
        this.colunaAnterior = colunaAnterior;
    }

    public int getProximaColuna() {
        return proximaColuna;
    }

    public void setProximaColuna(int proximaColuna) {
        this.proximaColuna = proximaColuna;
    }
    
    
    public Macaco() {
        this.descricao = "CHELTON";
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cadeira getCadeira() {
        return cadeira;
    }

    public boolean isCadeiraPosicao() {
        return cadeiraPosicao;
    }

    public void setCadeiraPosicao(boolean cadeiraPosicao) {
        this.cadeiraPosicao = cadeiraPosicao;
    }
    
    

    public void setCadeira(Cadeira cadeira) {
        this.cadeira = cadeira;
    }

    public Vara getVara() {
        return vara;
    }

    public void setVara(Vara vara) {
        this.vara = vara;
    }
    
    
    public void pegarCadeira(Cadeira c){
        this.cadeira = c;
    }
    
    public void pegarVara(Vara v){
        this.vara = v;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
