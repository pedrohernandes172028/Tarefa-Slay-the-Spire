package Cartas;
import Entidades.Entidade;

public abstract class Carta {
    private String nome;
    private String descricao;
    private int custo;
    private int momentos; /*esse valor guarda duas informações: apenas momentos mostra o momento que ativa os efeitos de mesmo momento de quem está usando a carta, enquanto momentos + 1 mostra o momento que ativa os efeitos de mesmo momento de quem está recebendo a carta*/

    public Carta(String nome, String descricao, int custo, int momentos){
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.momentos = momentos;
    }


    public String getNome(){
        return this.nome;
    }
    public String getDescricao(){
        return this.descricao;
    }
    public int getCusto(){
        return this.custo;
    }
    public int getMomentos(){
        return this.momentos;
    }
    public abstract void usar(Entidade alvo);
}