package macacobanana;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Sala {

    private Macaco macaco;
    private Cadeira cadeira;
    private Vara vara;
    private Banana banana;
    boolean sucesso = false;

    private final Object[][] ambiente = new Object[5][5];
    Random r = new Random();
    BufferedReader bb = new BufferedReader(new InputStreamReader(System.in));
    int linha, coluna;

    public void ambiente() {

        for (int linha = 0; linha < ambiente.length; linha++) {
            for (int coluna = 0; coluna < ambiente[0].length; coluna++) {
//                ambiente[linha][coluna] = r.nextInt(150);
                if (ambiente[linha][coluna] == null) {
                    System.out.print(linha + "" + coluna + " ");
                } else {
                    System.out.print(ambiente[linha][coluna] + " ");
                }
            }
            System.out.println(" ");
        }
        System.out.println("Macaco: " + macaco.getPosicaoLinha() + "" + macaco.getPosicaoColuna());
    }

    public void posicionarMacaco() {
        linha = r.nextInt((4 - 1) + 1) + 1;
        coluna = r.nextInt((4 - 0) + 1) + 0;

        if (ambiente[linha][coluna] == null) {
            macaco = new Macaco();
            ambiente[linha][coluna] = macaco;
            macaco.setPosicaoLinha(linha);
            macaco.setPosicaoColuna(coluna);

        } else {
            posicionarMacaco();
        }
    }

    public void posicionarVara() {
        linha = r.nextInt((4 - 1) + 1) + 1;
        coluna = r.nextInt((4 - 0) + 1) + 0;

        if (ambiente[linha][coluna] == null) {
            vara = new Vara();
            ambiente[linha][coluna] = vara;
        } else {
            posicionarVara();
        }
    }

    public void posicionarCadeira() {
        linha = r.nextInt((4 - 1) + 1) + 1;
        coluna = r.nextInt((4 - 0) + 1) + 0;
        if (ambiente[linha][coluna] == null) {
            cadeira = new Cadeira();
            ambiente[linha][coluna] = cadeira;
        } else {
            posicionarCadeira();
        }

    }

    public void posicaoInicial() {
        //Posicionar cadeira
        posicionarCadeira();

        //Posicionar vara
        posicionarVara();

        //Posicionar macaco
        posicionarMacaco();

        //Estado do ambiente
        ambiente();
    }

    //Movimentos
    public boolean cima() {
        boolean retorno = true;

        int linha = macaco.getPosicaoLinha();
        int coluna = macaco.getPosicaoColuna();

        macaco.setColunaAnterior(coluna);
        macaco.setLinhaAnterior(linha);
        System.out.println("POSICOES: " + linha + "" + coluna);
        //tenta cima()
        if ((linha - 1 != 0)) {
            //ver se o de cima nao eh igual ao anterior
            if (linha - 1 != macaco.getLinhaAnterior()) {
                //tenta mudar para cima verificando se o lugar esta vazio
                if (ambiente[linha - 1][coluna] == null) {
                    ambiente[linha - 1][coluna] = macaco;
                    ambiente[linha][coluna] = null;
                    macaco.setPosicaoLinha(linha - 1);

                    System.out.println("Antes: " + linha + coluna + " Foi para cima: " + macaco.getPosicaoLinha() + coluna);
                    //verifica se esta no goal
                    if (goal()) {
                        System.out.println("SUCESSO");
                    }
                } else {
                    //caso nao esteja vazio, verifica se eh uma cadeira, se for ve se esta na posicao [4][2]
                    if (ambiente[linha - 1][coluna].toString() == "CADEIRA") {
                        //se nao estiver na posicao posiciona a cadeira
                        if (macaco.isCadeiraPosicao() == false) {
                            ambiente[4][2] = cadeira;
                            ambiente[linha - 1][coluna] = null;
                            macaco.setCadeiraPosicao(true);
                        }
                        //testa goal()
                        if (goal()) {
                            System.out.println("SUCESSO");
                        }
                    } else {
                        //pega vara e depois verifica goal
                        macaco.setVara(vara);
                        ambiente[linha -1][coluna] = null;
                        if (goal()) {
                            System.out.println("SUCESSO");
                        }
                    }
                }
            }
        } else {
            System.out.println("Na posicao: " + linha + "" + coluna + " Nao pode ir para cima");
            retorno = false;
        }

        return retorno;
    }

    //Baixo()
    public boolean baixo() {
        boolean retorno = true;
        int linha = macaco.getPosicaoLinha();
        int coluna = macaco.getPosicaoColuna();

        macaco.setColunaAnterior(coluna);
        macaco.setLinhaAnterior(linha);
        System.out.println("POSICOES: " + linha + "" + coluna);

        //pode ir para baixo desde que a linha seja diferente de 4
        if (linha + 1 <= 4) {
            //verficar se a proxima linha eh diferente da anterior
            if (linha + 1 != macaco.getLinhaAnterior()) {
                //tenta mudar para baixo verificando se o lugar esta vazio
                if (ambiente[linha + 1][coluna] == null) {
                    ambiente[linha + 1][coluna] = macaco;
                    ambiente[linha][coluna] = null;
                    macaco.setPosicaoLinha(linha + 1);

                    System.out.println("Antes: " + linha + coluna + " Foi para baixo: " + macaco.getPosicaoLinha() + coluna);
                    if (goal()) {
                        System.out.println("SUCESSO");
                    }

                } else {
                    //caso nao esteja vazio, ver o que tem e pegar
                    if (ambiente[linha + 1][coluna].toString() == "CADEIRA") {
                        //se nao estiver na posicao posiciona a cadeira
                        if (macaco.isCadeiraPosicao() == false) {
                            ambiente[4][2] = cadeira;
                            ambiente[linha + 1][coluna] = null;
                            macaco.setCadeiraPosicao(true);
                            ambiente[linha][coluna] = null;
                            ambiente[linha + 1][coluna] = macaco;
                        }
                        if (goal()) {
                            System.out.println("SUCESSO");
                        }
                    } else {
                        macaco.setVara(vara);
                        ambiente[linha + 1][coluna] = null;
                        if (goal()) {
                            System.out.println("SUCESSO");
                        }
                    }
                }
            }
        } else {
            System.out.println("Na posicao: " + linha + "" + coluna + " Nao pode ir para baixo");
            retorno = false;

        }
        return retorno;
    }

    //Direita()
    public boolean direita() {
        boolean retorno = true;
        int linha = macaco.getPosicaoLinha();
        int coluna = macaco.getPosicaoColuna();

        macaco.setColunaAnterior(coluna);
        macaco.setLinhaAnterior(linha);
        System.out.println("POSICOES: " + linha + "" + coluna);

        //so va a direita se nao estiver na coluna 4
        if (macaco.getPosicaoColuna() != 4) {
            //verificar se a proxima coluna eh diferente da anterior
            if (coluna + 1 != macaco.getColunaAnterior()) {
                //tenta ir verificando se esta vazia
                if (ambiente[linha][coluna + 1] == null) {
                    ambiente[linha][coluna + 1] = macaco;
                    macaco.setPosicaoColuna(coluna + 1);
                    ambiente[linha][coluna] = null;

                    System.out.println("Antes: " + linha + coluna + " Foi para direita: " + linha + macaco.getPosicaoColuna());
                    if (goal()) {
                        System.out.println("SUCESSO");
                    }

                } else {
                    //caso nao esteja vazio, ver o que tem e pegar
                    if (ambiente[linha][coluna + 1].toString() == "CADEIRA") {
                        //se nao estiver na posicao posiciona a cadeira
                        if (macaco.isCadeiraPosicao() == false) {
                            ambiente[4][2] = cadeira;
                            ambiente[linha][coluna + 1] = null;
                            macaco.setCadeiraPosicao(true);
                            ambiente[linha][coluna] = null;
                            ambiente[linha][coluna + 1] = macaco;
                        }
                        if (goal()) {
                            System.out.println("SUCESSO");
                        }
                    } else {
                        macaco.setVara(vara);
                        ambiente[linha][coluna + 1] = null;
                        if (goal()) {
                            System.out.println("SUCESSO");
                        }
                    }

                }
            }

        } else {
            System.out.println("Na posicao: " + linha + "" + coluna + " Nao pode ir para direita");
            retorno = false;
        }

        return retorno;
    }

    //Esquerda()
    public boolean esquerda() {
        boolean retorno = true;
        int linha = macaco.getPosicaoLinha();
        int coluna = macaco.getPosicaoColuna();

        macaco.setColunaAnterior(coluna);
        macaco.setLinhaAnterior(linha);
        System.out.println("POSICOES: " + linha + "" + coluna);

        //so vai a esquerda se nao estiver na coluna 0
        if (macaco.getPosicaoColuna() != 0) {
            //verificar se a proxima coluna eh diferente da anterior
            if ((coluna - 1) != macaco.getColunaAnterior()) {
                //tenta ir a coluna verificando se esta vazia
                if (ambiente[linha][coluna - 1] == null) {
                    ambiente[linha][coluna - 1] = macaco;
                    macaco.setPosicaoColuna(coluna - 1);
                    ambiente[linha][coluna] = null;

                    System.out.println("Antes: " + linha + coluna + " Foi para esquerda: " + linha + macaco.getPosicaoColuna());
                    if (goal()) {
                        System.out.println("SUCESSO");
                    }

                } else {
                    //caso nao esteja vazio, ver o que tem e pegar
                    if (ambiente[linha][coluna - 1].toString() == "CADEIRA") {
                        //se nao estiver na posicao posiciona a cadeira
                        if (macaco.isCadeiraPosicao() == false) {
                            ambiente[linha][coluna - 1] = null;
                            ambiente[4][2] = cadeira;
                            macaco.setCadeiraPosicao(true);
                            ambiente[linha][coluna] = null;
                            ambiente[linha][coluna - 1] = macaco;
                        }
                        if (goal()) {
                            System.out.println("SUCESSO");
                        }
                    } else {
                        macaco.setVara(vara);
                        ambiente[linha][coluna - 1] = null;
                        if (goal()) {
                            System.out.println("SUCESSO");
                        }
                    }
                }
            }
        } else {
            System.out.println("Na posicao: " + linha + "" + coluna + " Nao pode ir para direita");
            retorno = false;
        }
        return retorno;
    }

    //Verificar posicao
    //Verificar se é goal
    public boolean goal() {
        if ((macaco.getPosicaoLinha() == 3) && (macaco.getPosicaoColuna() == 2) && (macaco.getVara() != null) && (macaco.isCadeiraPosicao() == true)) {
            sucesso = true;
            System.out.println("");
            System.out.println("");
            ambiente();
            System.out.println("");
            return true;
        }
        return false;
    }

    public void escolherMovimento() {
        int lado = r.nextInt((4 - 1) + 1) + 1;
        boolean retorno = true;
        if (lado == 1) {
            retorno = esquerda();
            if (retorno == false) {
                escolherMovimento();
            }
        } else if (lado == 2) {
            retorno = cima();
            if (retorno == false) {
                escolherMovimento();
            }
        } else if (lado == 3) {
            retorno = direita();
            if (retorno == false) {
                escolherMovimento();
            }
        } else {
            retorno = baixo();
            if (retorno == false) {
                escolherMovimento();
            }
        }
    }

    public void verificarPosicao() {
        if (goal()) {
            System.out.println("Objetivo alcancado");
        } else {
            while (sucesso == false) {
                escolherMovimento();
            }
        }
    }

    public void iniciar() {

        banana = new Banana();

        ambiente[0][2] = banana;
        posicaoInicial();
        ambiente();

        verificarPosicao();

    }

}
