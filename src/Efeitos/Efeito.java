package Efeitos;
import Entidades.Entidade;

public abstract class Efeito {
    private String nome;
    private Entidade dono;
    private int acumulo;
    private int[] momentos; /* momentos em que o Efeito é acionado */

    public Efeito(String nome, Entidade dono, int acumulo, int[] momentos){
        this.nome = nome;
        this.dono = dono;
        this.acumulo = acumulo;
        this.momentos = momentos;
    }
    public String getString(){
        return dono + " possui " + acumulo + " de " + nome + "!";
    }
    public String getNome(){
        return nome;
    }
    public int getAcumulo(){
        return acumulo;
    }
    public Entidade getDono(){
        return dono;
    }
    public int[] getMomentos(){
        return momentos;
    }
    public void alterarAcumulo(int valor){
        acumulo += valor;
        if (acumulo <= 0){
            getDono().removerEfeito(this);
        }
    }
    public abstract void acionado();
}