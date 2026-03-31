package Efeitos;
import Entidades.Entidade;

public class Forca extends Efeito{
    
    public Forca(String nome, Entidade dono, int acumulo){
        super(nome, dono, acumulo, new int[]{2});
    }

    public void acionado(){
        getDono().getAlvo().receberDano(2 * getAcumulo());
        System.out.println(getNome() + " de " + getDono().getNome() + " causou +" + (2 * getAcumulo()) + " de dano em " + getDono().getAlvo().getNome());
    }
}