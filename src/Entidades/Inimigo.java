package Entidades;
import java.util.List;
import java.util.Scanner;

import Cartas.Carta;
public abstract class Inimigo extends Entidade {
    protected Carta[] acoes;  /*todas as açoes que o inimigo pode fazer, acoes é definida apenas pela classe que herda Inimigo */
    public Inimigo(String nome, int vida, int escudo, int velocidade){
        super(nome, vida, escudo, velocidade);
        
    }
    public Carta[] getAcoes(){
        return acoes;
    }

    public boolean realizarAcao(Heroi heroi, List<Inimigo> inimigos, Scanner comandoScanner){ /*vai aplicar a escolha feita pela função anuncio, nao usa scanner (não sei como remover esse scanner de forma prática)*/
        System.out.println(getNome() + " usou " + acoes[getAcaoEscolhida()].getNome() + ". ");
        acoes[getAcaoEscolhida()].usar(getAlvo());
        return false;
    }
    public Carta cartaUtilizada(int posicao){
        return acoes[posicao];
    }

    public abstract void anuncio(Heroi heroi, List<Inimigo> inimigos);   /*vai definir acaoEscolhida, que é um indice de açoes e o alvo, cada tipo de inimigo possui uma estratégia*/
}