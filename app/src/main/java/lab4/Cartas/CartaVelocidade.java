package lab4.Cartas;
import lab4.Entidades.Entidade;

/**
 * aumenta a velocidade do alvo
 */
public class CartaVelocidade extends Carta {

    public CartaVelocidade(String nome, String descricao, int custo){
        super(nome, descricao, custo, 12);
    }

    /**
     * aumenta velocidade do alvo prorcional ao custo da carta
     */
    public void usar(Entidade alvo){  
        alvo.alterarVelocidade(getCusto() * 2);
        System.out.println(alvo.getNome() + " recebeu " + getCusto() * 2 + " de velocidade.\n");
    }
}