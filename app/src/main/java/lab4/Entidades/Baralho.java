package lab4.Entidades;
import java.util.List;
import lab4.Cartas.*;
import java.util.Stack;
import java.util.Collections;

/**
 * inicializa e controla as cartas do Heroi
 */
public class Baralho {
    private List<Carta> cartasNaMao;
    private Stack<Carta> deckCompra = new Stack<>();
    private Stack<Carta> deckDescarte = new Stack<>();
    public Baralho(){
        this.deckCompra = iniciarDeck();
        this.deckDescarte = new Stack<>();
        this.cartasNaMao = new Stack<>();
    }

    public List<Carta> getCartasNaMao(){
        return cartasNaMao;
    }
    public int getnCartasNaMao(){
        return this.cartasNaMao.size();
    }
    
    /**
     * Por enquanto, este é o deck inicial do nosso heroi  
     * @return  retorna o conjunto de cartas usadas na pilha de compras
     */
    private Stack<Carta> iniciarDeck(){     
        Stack<Carta> compra = new Stack<>();
        CartaDano espada = new CartaDano("Espada de cobre", "causa 10 de dano ao alvo / custa 2 energias",2);
        CartaEscudo escudo = new CartaEscudo("Escudo de madeira","concede 5 de escudo ao alvo / custa 1 energia", 1);
        CartaForca bomba = new CartaForca("Esteróide", "concede +1 de força ao alvo/ custa 1 energia", 1);
        CartaVeneno dardo = new CartaVeneno("dardo envenenado", "concede +1 de veneno ao alvo / custa 1 energia", 1);
        CartaCura pocao = new CartaCura("poção de cura", "concede 5 de cura / custa 1 energia", 1);
        CartaVelocidade agilidade = new CartaVelocidade("pés ágeis", "concede +2 de velocidade ao alvo / custa 1 energia", 1);
        CartaLentidao teia = new CartaLentidao("teia de aranha", "concede -2 de velocidade ao alvo / custa 1 energia", 1);
        CartaMortalForca mortalForca = new CartaMortalForca("Lança vampirica", "causa (9 vezes força do alvo) de dano e gasta todo o acúmulo de força do alvo / custa 3 de energia", 3);
        CartaMortalVeneno mortalVeneno = new CartaMortalVeneno("Líquido infectado", "causa (6 vezes veneno do alvo) de dano e gasta todo o acúmulo de veneno do alvo / custa 2 de energia", 2);;
        
        for (int i = 0; i < 3; i++){    /*coloquei 12 cartas na pilha de compras, 3 de cada tipo*/
            compra.push(espada);
            compra.push(escudo);
            compra.push(bomba);
            compra.push(dardo);
        }

        /*novas cartas */
        compra.push(pocao);  
        compra.push(agilidade);
        compra.push(teia);
        compra.push(mortalForca);
        compra.push(mortalVeneno);

        Collections.shuffle(compra);  /*embaralhando */
        return compra;
    }
    /**transfere cartas da pilha de compras para as cartasNamão até que nCartasNaMão seja igual a 5 */
    public void comprarCartas(){
            while (cartasNaMao.size() < 5){    /*comprando cartas*/
                if (deckCompra.isEmpty()){  /*passa as cartas da pilha de descarte para a pilha de compras */
                    Collections.shuffle(deckDescarte);
                    deckCompra.addAll(deckDescarte);
                    deckDescarte.clear();
                }
                cartasNaMao.add(deckCompra.pop());
            }
    }
    /** retira a carta com posicao comando de cartasNaMão e coloca na pilha de descarte*/
    public void cartaUsada(int comando){
        deckDescarte.push(cartasNaMao.remove(comando - 1));
    }
}