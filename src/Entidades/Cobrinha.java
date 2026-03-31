package Entidades;
import java.util.List;
import Cartas.Carta;
import Cartas.CartaDano;
import Cartas.CartaEscudo;
import Cartas.CartaForca;
import Cartas.CartaVeneno;
import java.util.Random;

public class Cobrinha extends Inimigo{
    
    public Cobrinha(String nome, int vida, int escudo, int dano, int velocidade){
        super(nome, vida, escudo, velocidade);
        determinarAcoes();
    }
    private void determinarAcoes(){
        acoes = new Carta[10];
        CartaDano espada = new CartaDano("Cauda de chicote", "causa 10 de dano ao inimigo ",2); /*custo só serve para determinar o dano */
        CartaEscudo escudo = new CartaEscudo("Pele endurecida","concede 5 de escudo ao usuário ", 1);
        CartaForca bomba = new CartaForca("Esteróide", "concede +1 de força ", 1);
        CartaVeneno dardo = new CartaVeneno("Mordida venenosa", "concede +1 de veneno ", 1);
        for (int i = 0; i < 2; i += 5){ /*40% cartaDano, 20% cartaEscudo, 20% cartaForca, 20% cartaVeneno */
            acoes[i] = espada;
            acoes[i + 1] = escudo;
            acoes[i + 2] = bomba;
            acoes[i + 3] = dardo;
            acoes[i + 4] = espada;
        }
    }

    public void anuncio(Heroi heroi, List<Inimigo> inimigos){
        Random gerador = new Random();
        mudarEscolha(gerador.nextInt(10));
        if (getAcoes()[getAcaoEscolhida()].getMomentos() == 2 || getAcoes()[getAcaoEscolhida()].getMomentos() == 8){   /*afeta o heroi */
            mudarAlvo(heroi);
        }else if (getAcoes()[getAcaoEscolhida()].getMomentos() == 6){ /* carta de buff */
            mudarAlvo(inimigos.get(0));     /*afeta o inimigo que estiver com mais vida*/
            for (int i = 1; i < inimigos.size(); i++){
                if (inimigos.get(i - 1).getVida() < inimigos.get(i).getVida()){
                    mudarAlvo(inimigos.get(i));
                }
            }
        }else{  /*carta de defesa */
            mudarAlvo(inimigos.get(0));     /*afeta o inimigo que estiver com menos vida*/
            for (int i = 1; i < inimigos.size(); i++){
                if (inimigos.get(i - 1).getVida() > inimigos.get(i - 1).getVida()){
                    mudarAlvo(inimigos.get(i - 1));
                }
            }
        }        
        System.out.println(getNome() + " está se preparando para usar " + getAcoes()[getAcaoEscolhida()].getNome() + ". Esse ataque " + getAcoes()[getAcaoEscolhida()].getDescricao()); /*mudar a saida */
    }
}