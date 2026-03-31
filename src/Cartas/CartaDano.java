package Cartas;
import Entidades.Entidade;

public class CartaDano extends Carta {

    public CartaDano(String nome, String descricao, int custo){
        super(nome, descricao, custo, 2);
    }
    public void usar(Entidade alvo){  /*personagem vai ser um inimigo, por enquanto */
        alvo.receberDano(getCusto() * 5);
        System.out.println(alvo.getNome() + " recebeu " + getCusto() * 5 + " de dano.\n");
    }
}