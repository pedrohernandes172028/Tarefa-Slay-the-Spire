package lab4.Efeitos;
import lab4.Entidades.Entidade;

/**Veneno causa continuo a cada turno geral que passa
 * Veneno é acionado no final de um turno geral
*/
public class Veneno extends Efeito {
    
    public Veneno(String nome, Entidade dono, int acumulo){
        super(nome, dono, acumulo, new int[]{1});
    }

    /**causa 3 de dano apenas na vida do seu dono e diminui seu acumulo de veneno em 1*/
    public void acionado(){
        getDono().alterarVida(-3);
        System.out.println(getNome() + " abaixou em 1 o seu acúmulo e causou +3 de dano em " + getDono().getNome());
        alterarAcumulo(-1);

    }
}