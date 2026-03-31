package Entidades;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Cartas.Carta;

import Efeitos.Efeito;
public abstract class Entidade {
    private String nome;
    private int vida;
    private int vidaMaxima; /*futuramente vai servir para alguma carta de cura */
    private int escudo;
    private ArrayList<Efeito> efeitos = new ArrayList<>();
    private int velocidade;
    private Carta acaoEscolhida;  /*a acao em acoes que o inimigo vai fazer ou a posicao da acao em cartasNaMao que o heroi vai fazer*/
    private Entidade alvo;  /*o alvo da sua ação */

    public Entidade(String nome, int vida,  int escudo, int velocidade){
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.velocidade = velocidade;
        this.vidaMaxima = vida;
    }
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
    public void ganharEscudo(int bonus){
        escudo += bonus;
    }
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
    public void mudarAlvo(Entidade alvo){
        this.alvo = alvo;
    }
    public abstract void mudarEscolha(int escolha);

    public void mudarAcaoEscolhida(Carta nova){
        acaoEscolhida = nova;
    }

    public void removerEfeito(Efeito removido){
        efeitos.remove(removido);
        System.out.println("O efeito " + removido.getNome() + " foi removido de " + getNome());
    }


    public void aplicarEfeito(Efeito novo){
        for (Efeito i : efeitos){
            if (novo.getNome().equals(i.getNome())){
                i.alterarAcumulo(novo.getAcumulo());
                return;
            }
        }
        efeitos.add(novo);
    
    }
    public void getStatus(){
        System.out.print(getNome() + " (" + getVida() + "/" + getVidaMaxima() + " de vida) (" + getEscudo() + " de escudo)");
        for (int i = 0; i < getEfeitos().size(); i++){
            System.out.print(" (" + getEfeitos().get(i).getAcumulo() + " de " + getEfeitos().get(i).getNome() + ")");
        }
        System.out.println();
    }
    public abstract boolean realizarAcao(Heroi heroi, List<Inimigo> inimigos, Scanner comandoScanner);
    public abstract Carta cartaUtilizada(int posicao);

}