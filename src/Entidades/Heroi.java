package Entidades;
import java.util.Scanner;

import Cartas.Carta;

import java.util.List;



public class Heroi extends Entidade{
    private Baralho baralho;
    private int energia;

    public Heroi(String nome, int vida, int escudo, int velocidade){
        super(nome, vida, escudo, velocidade);
        this.baralho = new Baralho();
        this.energia = 0;
    }

    public void resetarEscudo(){
        if (getEscudo() != 0){
            System.out.println(getNome() + " perdeu todo o seu escudo!\n");
            ganharEscudo(-getEscudo()); /*escudo vai ficar igual a 0 */
        }
    }
    public Baralho getBaralho(){
        return baralho;
    }
    public void resetarenergia(){
        energia = 3;
    }
    private void gastarenergia(int custo){
        energia -= custo;
    }
    public Carta cartaUtilizada(int posicao){
        return baralho.getCartasNaMao().get(posicao);
    }
    public void mudarEscolha(int escolha){
        if (escolha == -1){
            mudarAcaoEscolhida(null);
        }else{
            mudarAcaoEscolhida(baralho.getCartasNaMao().get(escolha));
        }
    }

    public boolean realizarAcao(Heroi heroi, List<Inimigo> inimigos, Scanner comandoScanner){   /*realiza apenas uma ação */
        if (energia == 0){
            mudarAlvo(null);
            System.out.println(heroi.getNome() + " está sem energia! Seu turno acabou.\n");
            return false; 
        }
        
        System.out.println("Deck:");   /*mostrando, no terminal, as cartas na mao do jogador */
        for (int i = 0; i < baralho.getnCartasNaMao(); i++) {
            System.out.println((i + 1) + " -> " + baralho.getCartasNaMao().get(i).getNome() + " / " + baralho.getCartasNaMao().get(i).getDescricao());
        }
        System.out.println("\n" + energia + "/3 de Energia disponível\n");   /*energias disponíveis */
        System.out.println("Digite o número de uma carta para usá-la ou digite 6 para passar o seu turno.\n");    /*instrução*/
        int comando =  comandoScanner.nextInt();
        if (comando == 6){  /*passa a vez */
            System.out.println(heroi.getNome() + " passou a vez!\n");
            mudarAlvo(null);
            mudarEscolha(-1);
            return false;
        }else if (comando > baralho.getnCartasNaMao()) {  /*escolheu um espaço de carta que nao existe no momento */
            System.out.println("Sem nenhuma carta na posição escolhida.\n");
            mudarAlvo(null);
            mudarEscolha(-1);

        }else if (baralho.getCartasNaMao().get(comando - 1).getCusto() > energia){
            System.out.println("Sem energia para realizar está ação\n");
            mudarAlvo(null); 
            mudarEscolha(-1);
        }else{
            mudarEscolha(comando - 1);
            System.out.println("Como alvo da sua ação, digite 0 para escolher você ou digite um dos números dos inimigos a seguir:\n");
            for (int i = 1; i < inimigos.size() + 1; i++){
                System.out.println(i + " -> " + inimigos.get(i - 1).getNome());
            }
            
            int alvo =  comandoScanner.nextInt();
            if (alvo > inimigos.size()){
                System.out.println("Sem nenhum inimigo na posição escolhida.\n");
                mudarAlvo(null);
                mudarEscolha(-1);
            }else{
                gastarenergia(baralho.getCartasNaMao().get(comando - 1).getCusto());
                System.out.print(getNome() + " usou " + getAcaoEscolhida().getNome() + ". ");
                if (alvo == 0){
                    mudarAlvo(heroi);
                    getAcaoEscolhida().usar(heroi);
                }else{
                    mudarAlvo(inimigos.get(alvo - 1));
                    getAcaoEscolhida().usar(inimigos.get(alvo - 1));
                }
                baralho.cartaUsada(comando);
            }
        }
        return true;
    }
}
