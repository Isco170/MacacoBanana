package macacobanana;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Sala {

    private Macaco macaco;
    private Cadeira cadeira;
    private Vara vara;
    private Banana banana;

    private final Object[][] ambiente = new Object[5][5];
    Random r = new Random();
    BufferedReader bb = new BufferedReader(new InputStreamReader(System.in));
    int linha, coluna;

    public void ambiente() {

        
        for (int linha = 0; linha < ambiente.length; linha++) {
            for (int coluna = 0; coluna < ambiente[0].length; coluna++) {
//                ambiente[linha][coluna] = r.nextInt(150);
                if(ambiente[linha][coluna] == null){
                    System.out.print(linha + "" + coluna + " ");
                }else{
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
    //Cima()
    //Baixo()
    //Direita()
    //Esquerda()
    //Verificar posicao
    //Verificar se é goal
    public boolean goal() {
        if ((macaco.getPosicaoLinha() == 3) && (macaco.getPosicaoColuna() == 2) && (macaco.getVara() != null) && ("CADEIRA" == ambiente[4][2].toString())) {
            return true;
        }
        return false;
    }

    public void verificarPosicao() {
        if (goal()) {
            System.out.println("Objetivo alcancado");
        } else {
            int linha = macaco.getPosicaoLinha();
            int coluna = macaco.getPosicaoColuna();

            macaco.setColunaAnterior(coluna);
            macaco.setLinhaAnterior(linha);

            System.out.println("POSICOES: " + linha + "" + coluna);

            //Movimentar()
            //tenta cima()
            if ((linha - 1 != 0)) {
                //ver se o de cima nao eh igual ao anterior
                if (linha - 1 != macaco.getLinhaAnterior()) {
                    //tenta mudar para cima verificando se o lugar esta vazio
                    if (ambiente[linha - 1][coluna] == null) {

                        ambiente[linha - 1][coluna] = macaco;
                        ambiente[linha][coluna] = null;
                        macaco.setPosicaoLinha(linha - 1);
                        System.out.println("Foi para cima");
                        
                        System.out.println("");
                        System.out.println("===Nova posicao===");
                        System.out.println("");
                        ambiente();
                    } else {
                        //caso nao esteja vazio, ver o que tem e pegar
                        if (ambiente[macaco.getPosicaoLinha() - 1][coluna].toString() == "CADEIRA") {
                            macaco.setCadeira(cadeira);
                        } else {
                            macaco.setVara(vara);
                        }
                        if (goal()) {
                            System.out.println("Objetivo alcancado");
                        }
                    }
                }
            }else{
                System.out.println("Nao pode ir para cima");
            }

            //tentar baixo()
            //pode ir para baixo desde que a linha seja diferente de 4
            if (linha + 1 <= 4) {
                linha = macaco.getPosicaoLinha();
                coluna = macaco.getPosicaoColuna();
                
                macaco.setColunaAnterior(coluna);
                macaco.setLinhaAnterior(linha);

                //verficar se a proxima linha eh diferente da anterior
                if (linha + 1 != macaco.getLinhaAnterior()) {
                    //tenta mudar para baixo verificando se o lugar esta vazio
                    if (ambiente[linha + 1][coluna] == null) {
                        ambiente[linha + 1][coluna] = macaco;
                        ambiente[linha][coluna] = null;
                        System.out.println("Linha: "+ linha);
                        macaco.setPosicaoLinha(linha + 1);
                        
                        System.out.println("");
                        System.out.println("==========");
                        ambiente();
                        System.out.println("");

                    } else {
                        //caso nao esteja vazio, ver o que tem e pegar
                        if (ambiente[linha + 1][coluna].toString() == "CADEIRA") {
                            macaco.setCadeira(cadeira);
                        } else {
                            macaco.setVara(vara);
                        }
                        if (goal()) {
                            System.out.println("Objetivo alcancado");
                        }
                    }
                }
            }else{
                System.out.println("Não pode ir para baixo");
            }
            
            //tentar esquerda()
                //so vai a esquerda se nao estiver na coluna 0
                    if(macaco.getPosicaoColuna() != 0){
                        linha = macaco.getPosicaoLinha();
                        coluna = macaco.getPosicaoColuna();
                        
                        macaco.setColunaAnterior(coluna);
                        macaco.setLinhaAnterior(linha);
                        
                        //verificar se a proxima coluna eh diferente da anterior
                        if((coluna - 1) != macaco.getColunaAnterior()){
                            //tenta ir a coluna verificando se esta vazia
                            if(ambiente[linha][coluna -1] == null){
                                ambiente[linha][coluna - 1] = macaco;
                                macaco.setPosicaoColuna(coluna - 1);
                                ambiente[linha][coluna] = null;
                                
                                System.out.println("Foi para esquerda");
                                System.out.println("===Nova posicao===");
                                System.out.println("");
                                ambiente();

                            }else{
                                //caso nao esteja vazio, ver o que tem e pegar
                                if(ambiente[linha][coluna -1].toString() == "CADEIRA"){
                                    macaco.setCadeira(cadeira);
                                }else{
                                    macaco.setVara(vara);
                                }
                                if (goal()) {
                                    System.out.println("Objetivo alcancado");
                                }
                            }
                        }
                    }else{
                        System.out.println("Nao pode ir a esquerda");
                    }
            //tentar direita()
                //so va a direita se nao estiver na coluna 4
                    if(macaco.getPosicaoColuna() != 4){
                        linha = macaco.getPosicaoLinha();
                        coluna = macaco.getPosicaoColuna();
                        
                        macaco.setColunaAnterior(coluna);
                        macaco.setLinhaAnterior(linha);
                        //verificar se a proxima coluna eh diferente da anterior
                        if(coluna + 1 != macaco.getColunaAnterior()){
                            //tenta ir verificando se esta vazia
                            if(ambiente[linha][coluna + 1] == null){
                                ambiente[linha][coluna + 1] = macaco;
                                macaco.setPosicaoColuna(coluna + 1);
                                ambiente[linha][coluna] = null;
                                
                                System.out.println("Foi para direita");
                                System.out.println("===Nova posicao===");
                                System.out.println("");
                                ambiente();
                            }else{
                                //caso nao esteja vazio, ver o que tem e pegar
                                if(ambiente[linha][coluna + 1].toString() == "CADEIRA"){
                                    macaco.setCadeira(cadeira);
                                }else{
                                    macaco.setVara(vara);
                                }
                                
                                if(goal()){
                                    System.out.println("Objetivo alcancado");
                                }
                            }
                        }
                        
                    }else{
                        System.out.println("Nao pode ir a direita");
                    }

        }
    }

    public void iniciar() {

        banana = new Banana();

        ambiente[0][2] = banana;
        posicaoInicial();
        System.out.println("V=========ANTES============V");
        ambiente();
        System.out.println("V==========DEPOIS==========V");
        System.out.println("");
        verificarPosicao();

    }

}
