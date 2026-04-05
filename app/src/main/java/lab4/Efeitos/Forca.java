package lab4.Efeitos;
import lab4.Entidades.Entidade;


/**Força causa dano extra ao alvo do seu dono
 * Forca é acionada apenas quando o seu dono usa alguma carta de dano corpo a corpo, ou seja, a CartaDano e a CartaMortalForca*/
public class Forca extends Efeito{
    
    public Forca(String nome, Entidade dono, int acumulo){
        super(nome, dono, acumulo, new int[]{2, 16});  
    }

    /** Causa dano bonus igual a 2 * getAcumulo() ao alvo do dono do efeito*/
    public void acionado(){
        getDono().getAlvo().receberDano(2 * getAcumulo());
        System.out.println(getNome() + " de " + getDono().getNome() + " causou +" + (2 * getAcumulo()) + " de dano em " + getDono().getAlvo().getNome());
    }
}