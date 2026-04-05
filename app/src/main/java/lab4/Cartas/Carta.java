package lab4.Cartas;
import lab4.Entidades.Entidade;

public abstract class Carta {
    private String nome;
    private String descricao;
    private int custo;
    /**Momentos representa a ação que esssa carta realiza ao ser usada
     * esse valor guarda duas informações: 
     * apenas momentos é usado para ativar os efeitos de mesmo momento de quem esta usando essa carta
     * enquanto momentos + 1 é usado para ativar os efeitos de mesmo momento de quem esta sendo alvo dessa carta
     */
    private int momentos; 

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