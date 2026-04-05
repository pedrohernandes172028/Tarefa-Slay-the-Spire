package lab4.Entidades;
import java.util.List;
import lab4.Cartas.*;
import java.util.Random;

/** tipo de inimigo focado em causar veneno */
public class Cobrinha extends Inimigo{
    
    public Cobrinha(String nome, int vida, int escudo, int dano, int velocidade){
        super(nome, vida, escudo, velocidade);
        determinarAcoes();   
    }

    /** cria o conjunto de ações que a cobrinha vai ter*/
    private void determinarAcoes(){
        acoes = new Carta[15];
        
        CartaDano espada = new CartaDano("Cauda de chicote", "causa 10 de dano ao inimigo ",1); /*custo só serve para determinar o dano */
        CartaEscudo escudo = new CartaEscudo("Pele endurecida","concede 5 de escudo ao usuário ", 1);
        CartaForca bomba = new CartaForca("Esteróide", "concede +1 de força ", 1);
        CartaVeneno dardo = new CartaVeneno("Mordida venenosa", "concede +1 de veneno ", 1);
        /*por enquanto, as cobrinhas não podem usar as cartas novas */
        
        for (int i = 0; i < 10; i += 5){ /*40% cartaDano, 20% cartaEscudo, 20% cartaForca, 20% cartaVeneno */
            acoes[i] = espada;
            acoes[i + 1] = escudo;
            acoes[i + 2] = bomba;
            acoes[i + 3] = dardo;
            acoes[i + 4] = espada;
        }
    }

    /**
     * possui um estratégia própria para a cobrinha
     */
    public void anuncio(Heroi heroi, List<Inimigo> inimigos){
        Random gerador = new Random();
        mudarEscolha(gerador.nextInt(10));
        if (getAcaoEscolhida().getMomentos() == 2 || getAcaoEscolhida().getMomentos() == 8){   /*afeta o heroi */
            mudarAlvo(heroi);
        }else if (getAcaoEscolhida().getMomentos() == 6){ /* carta de buff */
            mudarAlvo(inimigos.get(0));     /*afeta o inimigo que estiver com mais vida*/
            for (int i = 1; i < inimigos.size(); i++){
                if (inimigos.get(i - 1).getVida() < inimigos.get(i).getVida()){
                    mudarAlvo(inimigos.get(i));
                }
            }
        }else{  /*carta de defesa */
            mudarAlvo(inimigos.get(0));     /*afeta o inimigo que estiver com menos vida*/
            for (int i = 1; i < inimigos.size(); i++){
                if (inimigos.get(i - 1).getVida() > inimigos.get(i).getVida()){
                    mudarAlvo(inimigos.get(i - 1));
                }
            }
        }        
        System.out.println(getNome() + " está se preparando para usar " + getAcaoEscolhida().getNome() + " em " + getAlvo().getNome() +". Esse ataque " + getAcaoEscolhida().getDescricao()); /*mudar a saida */
    }
}