package lab4.Cartas;
import lab4.Entidades.Entidade;

/**aumenta apenas a vida do alvo */
public class CartaCura extends Carta {

    public CartaCura(String nome, String descricao, int custo){
        super(nome, descricao, custo, 10);
    }
    /** causa cura ao alvo proporcional ao custo da carta */
    public void usar(Entidade alvo){  
        alvo.alterarVida(getCusto() * 5);
        System.out.println(alvo.getNome() + " recebeu " + getCusto() * 5 + " de cura.\n");
    }
}