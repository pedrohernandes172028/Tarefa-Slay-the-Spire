package Cartas;
import Efeitos.Veneno;
import Entidades.Entidade;

public class CartaVeneno extends Carta{
    
    public CartaVeneno(String nome, String descricao, int custo){
        super(nome, descricao, custo, 8);
    }

    public void usar(Entidade alvo){
        alvo.aplicarEfeito(new Veneno("Veneno fraco", alvo, getCusto()));
        System.out.println(alvo.getNome() + " recebeu +" + getCusto() + " acúmulos de veneno.\n");
    }
}