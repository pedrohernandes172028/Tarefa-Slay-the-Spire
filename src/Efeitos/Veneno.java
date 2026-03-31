package Efeitos;
import Entidades.Entidade;

public class Veneno extends Efeito {
    
    public Veneno(String nome, Entidade dono, int acumulo){
        super(nome, dono, acumulo, new int[]{1});
    }
    public void acionado(){
        getDono().receberDano(3);
        alterarAcumulo(-1);
        System.out.println(getNome() + " abaixou em 1 o seu acúmulo e causou +3 de dano em " + getDono().getAlvo().getNome());
    }
}