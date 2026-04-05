package lab4.Cartas;
import lab4.Efeitos.Veneno;
import lab4.Entidades.Entidade;

/** ativa a forma específica Veneno fraco do efeito Veneno*/ 
public class CartaVeneno extends Carta{
    
    public CartaVeneno(String nome, String descricao, int custo){
        super(nome, descricao, custo, 8);
    }

    /**aumenta o acumulo de veneno do alvo proporcional ao custo da carta */
    public void usar(Entidade alvo){
        alvo.aplicarEfeito(new Veneno("Veneno fraco", alvo, getCusto() * 2));
        System.out.println(alvo.getNome() + " recebeu +" + getCusto() + " acúmulos de veneno.\n");
    }
}