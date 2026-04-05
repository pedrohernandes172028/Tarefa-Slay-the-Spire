package lab4.Entidades;
import java.util.List;
import java.util.Scanner;

import lab4.Cartas.Carta;
public abstract class Inimigo extends Entidade {
    /**Todas as açoes que o inimigo pode fazer, acoes é definida apenas pela classe que herda Inimigo.  */
    protected Carta[] acoes;
    public Inimigo(String nome, int vida, int escudo, int velocidade){
        super(nome, vida, escudo, velocidade);
        
    }
    public Carta[] getAcoes(){
        return acoes;
    }

    /**para inimigos, escolha determina uma Carta em acoes */
    public void mudarEscolha(int escolha){
        mudarAcaoEscolhida(acoes[escolha]);
    }

    /**vai aplicar a escolha feita pela função anuncio, nao usa scanner */
    public boolean realizarAcao(Heroi heroi, List<Inimigo> inimigos, Scanner comandoScanner){ /*(não sei como remover esse scanner de forma prática)*/
        System.out.println(getNome() + " usou " + getAcaoEscolhida().getNome() + ". ");
        getAcaoEscolhida().usar(getAlvo());
        return false;
    }
    public Carta cartaUtilizada(int posicao){
        return acoes[posicao];
    }
    /**
     * vai definir acaoEscolhida, que é uma posição de carta em açoes, e o alvo da ação. Cada tipo de inimigo possui uma estratégia para realizar esse método 
     * @param heroi heroi do jogador
     * @param inimigos  conjunto de inimigos vivos no jogo
     */
    public abstract void anuncio(Heroi heroi, List<Inimigo> inimigos);   
}