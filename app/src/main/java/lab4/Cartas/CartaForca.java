package lab4.Cartas;
import lab4.Efeitos.Forca;
import lab4.Entidades.Entidade;

/** ativa a forma específica Forca explosiva do efeito Forca, está relacionada a ataques corpo a corpo */
public class CartaForca extends Carta{
    
    public CartaForca(String nome, String descricao, int custo){
        super(nome, descricao, custo, 6);
    }

    /**aumenta o acumulo de força do alvo proporcional ao custo da carta */
    public void usar(Entidade alvo){
        alvo.aplicarEfeito(new Forca("Força explosiva", alvo, getCusto()));
        System.out.println(alvo.getNome() + " recebeu +" + getCusto() + " acúmulos de força.\n");
    }
}