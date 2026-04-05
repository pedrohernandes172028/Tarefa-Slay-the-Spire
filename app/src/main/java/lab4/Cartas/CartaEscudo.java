package lab4.Cartas;
import lab4.Entidades.Entidade;

public class CartaEscudo extends Carta {

    public CartaEscudo(String nome, String descricao, int custo){
        super(nome, descricao, custo, 4);
    }

    /**alvo vai receber escudo proporcional ao custo da carta */
    public void usar(Entidade alvo){ 
        alvo.ganharEscudo(getCusto() * 5);
        System.out.println(alvo.getNome() + " recebeu " + getCusto() * 5 + " de escudo.\n");
    }
}