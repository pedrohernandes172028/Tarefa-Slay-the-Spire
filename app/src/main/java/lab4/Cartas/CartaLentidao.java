package lab4.Cartas;
import lab4.Entidades.Entidade;

/**
 * diminui a velocidade do alvo
 */
public class CartaLentidao extends Carta{
    
    public CartaLentidao(String nome, String descricao, int custo){
        super(nome, descricao, custo, 14);
    }
    /**
     * diminui velocidade do alvo proporcional ao custo da carta
     */
    public void usar(Entidade alvo){  
        alvo.alterarVelocidade(-getCusto() * 2);
        System.out.println(alvo.getNome() + " perdeu " + getCusto() * 2 + " de velocidade.\n");
    }
}
