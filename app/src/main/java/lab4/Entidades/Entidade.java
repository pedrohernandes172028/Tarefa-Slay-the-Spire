package lab4.Entidades;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lab4.Cartas.Carta;
import lab4.Efeitos.*;

/**
 * Representa qualquer personagem no jogo
 */
public abstract class Entidade {
    private String nome;
    private int vida;
    /** A maior vida possível que a entidade pode ter */
    private int vidaMaxima; /*futuramente vai servir para alguma carta de cura */
    private int escudo;
    private ArrayList<Efeito> efeitos = new ArrayList<>();
    /** decide quem vai realizar sua ação primeiro */
    private int velocidade;
    /** a posição da acao em acoes que o inimigo vai fazer ou a posicao da acao em cartasNaMao que o heroi vai fazer */
    private Carta acaoEscolhida; 
    /** o alvo da sua ação  */
    private Entidade alvo;  

    /**
     * 
     * @param nome
     * @param vida
     * @param escudo
     * @param velocidade
     */
    public Entidade(String nome, int vida,  int escudo, int velocidade){
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.velocidade = velocidade;
        this.vidaMaxima = vida;
    }

    /**
     * causa dano à entidade
     * @param dano  determina o quanto a vida da entidade vai diminuir 
     */
    public void receberDano(int dano){
        escudo -= dano;
        if (escudo < 0){    /*evitando negativos */
            vida += escudo;
            escudo = 0;
            if (vida < 0){
                vida = 0;
            }
        }
    }

    /**
     * altera a vida da entidade sem considerar escudo
     * @param valor determina quanto a vida da entidade será alterada
     */
    public void alterarVida(int valor){
        vida += valor;
        if (vida > vidaMaxima){
            vida = vidaMaxima;
        }
        if (vida < 0){
            vida = 0;
        }
    }
    
    /**
     * altera a velocidade da entidade
     * @param valor determina quanto a velocidade da entidade será alterada
     */
    public void alterarVelocidade(int valor){
        velocidade += valor;
        if (velocidade < 0){
            velocidade = 0;
        }
    }

    /**
     * aumenta o atributo escudo da entidade
     * @param bonus determina quanto de escudo a entidade vai ganhar
     */
    public void ganharEscudo(int bonus){
        escudo += bonus;
    }

    /**
     * verifica se a entidade está viva ou morta
     * @return retorna true para entidade viva e false para entidade morta
     */
    public boolean estarVivo(){
        if (vida > 0){
            return true;
        }else{
            return false;
        }
    }

    public String getNome(){
        return this.nome;
    }
    public int getVida(){
        return this.vida;
    }
    public int getVidaMaxima(){
        return this.vidaMaxima;
    }
    public int getEscudo(){ 
        return this.escudo;
    }
    public int getVelocidade(){
        return this.velocidade;
    }
    public ArrayList<Efeito> getEfeitos(){
        return efeitos;
    }
    public Carta getAcaoEscolhida(){
        return acaoEscolhida;
    }
    public Entidade getAlvo(){
        return alvo;
    }

    /**
     * procura o efeito Forca da entidade
     * @return retorna o efeito força da entidade ou null caso a entidade não tenha força
     */
    public Efeito getForca(){
        for (int i = 0; i < efeitos.size(); i++){
            if (efeitos.get(i) instanceof Forca){
                return efeitos.get(i);
            }
        }
        return null;
    }

    /**
     * procura o efeito Veneno da entidade
     * @return retorna o efeito veneno da entidade ou null caso a entidade não tenha veneno
     */
    public Efeito getVeneno(){
        for (int i = 0; i < efeitos.size(); i++){
            if (efeitos.get(i) instanceof Veneno){
                return efeitos.get(i);
            }
        }
        return null;
    }

    
    /**
     * troca o atributo alvo da entidade por uma nova entidade
     * @param alvo entidade que será o novo alvo do usuário
     */
    public void mudarAlvo(Entidade alvo){
        this.alvo = alvo;
    }
    
    /**
     * ajuda a função mudarAcaoEscolhida achando a Carta. 
     * A posicão dessa Carta será relacionada ao atributo acoes para inimigos e ao atributo cartasNaMão do atributo baralho do Heroi
     * @param escolha posicao da carta que será usada pela entidade
     */
    public abstract void mudarEscolha(int escolha);

    /**
     * troca a carta armazenada em acaoEscolhida por outra
     * @param nova  carta que será a nova ação escolhida
     */
    public void mudarAcaoEscolhida(Carta nova){
        acaoEscolhida = nova;
    }

    /**
     * remove um efeito da lista de efeitos e avisa no terminal
     * @param removido efeito que vai ser removido de efeitos
     */
    public void removerEfeito(Efeito removido){
        efeitos.remove(removido);
        System.out.println("O efeito " + removido.getNome() + " foi removido de " + getNome());
    }

    /**
     * Verifica se o efeito novo não está na entidade: se não estivar, adiciona um novo efeito a lista de efeitos, se estiver, apenas soma os acúmulos
     * @param novo efeito novo que será adicionado à entidade
     */
    public void aplicarEfeito(Efeito novo){
        for (Efeito i : efeitos){
            if (novo.getNome().equals(i.getNome())){
                i.alterarAcumulo(novo.getAcumulo());
                return;
            }
        }
        efeitos.add(novo);
    
    }

    /** informa os status dos personagens no terminal */
    public void getStatus(){
        System.out.print(getNome() + " (" + getVida() + "/" + getVidaMaxima() + " de vida) (" + getEscudo() + " de escudo)");
        for (int i = 0; i < getEfeitos().size(); i++){
            System.out.print(" (" + getEfeitos().get(i).getAcumulo() + " de " + getEfeitos().get(i).getNome() + ")");
        }
        System.out.println();
    }
    /**
     * realiza uma única ação da entidade
     * @param comandoScanner usado apenas por Heroi para ler a entrada do usuário durante seu turno
     * @return  retorna true para a entidade realizar mais ações e retorna false quando a entidade terminar o seu turno (inimigo sempre retorna false)
     */
    public abstract boolean realizarAcao(Heroi heroi, List<Inimigo> inimigos, Scanner comandoScanner);

    /**
     * acha a carta da entidade na posicao pedida
     * @param posicao posicao de uma carta dentre um conjunto de cartas
     * @return retorna a carta na posicao pedida
     */
    public abstract Carta cartaUtilizada(int posicao);

}