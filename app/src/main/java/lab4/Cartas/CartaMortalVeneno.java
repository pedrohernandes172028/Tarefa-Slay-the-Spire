package lab4.Cartas;

import lab4.Entidades.Entidade;

/**gaste todos os pontos de veneno do alvo e converta em dano */
public class CartaMortalVeneno extends Carta{

    public CartaMortalVeneno(String nome, String descricao, int custo){
        super(nome, descricao, custo, 18);
    }
    /** causa dano ao alvo proporcional ao custo da carta e ao veneno do usuário além de gastar todos os acúmulos de veneno do alvo.
     * Caso alvo não possua Veneno, nada acontece
    */
    public void usar(Entidade alvo){  
        if (alvo.getVeneno() != null){   /*alvo possui acumulos de forca */
            alvo.receberDano(getCusto() * 3 * alvo.getVeneno().getAcumulo());
            System.out.println(alvo.getNome() + " recebeu " + getCusto() * 3 * alvo.getVeneno().getAcumulo()+ " de dano.\n");
            System.out.println(alvo.getNome() + "perdeu todos os seus acúmulos de veneno.\n");
            alvo.getVeneno().alterarAcumulo(-alvo.getVeneno().getAcumulo());
        }else{
            System.out.println(alvo.getNome() + " não possui acúmulos de veneno! Ele não tomou nenhum dano.\n");
        }
    }
}
