package lab4.Efeitos;
import lab4.Entidades.Entidade;

public abstract class Efeito {
    private String nome;
    private Entidade dono;
    private int acumulo;
    /** conjunto de momentos que ocorrem durante o jogo em que o efeito é acionado */
    private int[] momentos; 

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

    /**
     * soma um valor no atributo acumulo e remove esse efeito do seu dono caso acumulo menor ou igual a 0 
     * @param valor valor que vai ser somado ao acúmulo
     */
    public void alterarAcumulo(int valor){
        acumulo += valor;
        if (acumulo <= 0){
            getDono().removerEfeito(this);
        }
    }
    public abstract void acionado();
}