package lab4.Cartas;

import lab4.Entidades.Entidade;

/**gaste todos os pontos de força do alvo e converta em dano */
public class CartaMortalForca extends Carta {
    
    public CartaMortalForca(String nome, String descricao, int custo){
        super(nome, descricao, custo, 16);
    }
    /** causa dano ao alvo proporcional ao custo da carta e a força do usuário além de gastar todos os acúmulos de força do alvo
     * Caso alvo não possua Força, nada acontece
    */
    public void usar(Entidade alvo){  
        if (alvo.getForca() != null){   /*alvo possui acumulos de forca */
            alvo.receberDano(getCusto() * 3 * alvo.getForca().getAcumulo());
            System.out.println(alvo.getNome() + " recebeu " + getCusto() * 3 * alvo.getForca().getAcumulo() + " de dano.");
            System.out.println(alvo.getNome() + "perdeu todos os seus acúmulos de força.\n");
            alvo.getForca().alterarAcumulo(-alvo.getForca().getAcumulo());

        }else{
            System.out.println(alvo.getNome() + " não possui acúmulos de força! Ele não tomou nenhum dano.\n");
        }
    }
}
