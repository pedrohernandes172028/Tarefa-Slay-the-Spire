package Entidades;
import java.util.List;
import Cartas.*;
import java.util.Stack;
import java.util.Collections;
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
    
    private Stack<Carta> iniciarDeck(){     /*por enquanto, esse é o deck inicial do nosso heroi */
        Stack<Carta> compra = new Stack<>();
        CartaDano espada = new CartaDano("Espada de cobre", "causa 10 de dano ao inimigo / custa 2 energias",2);
        CartaEscudo escudo = new CartaEscudo("Escudo de madeira","concede 5 de escudo ao usuário / custa 1 energia", 1);
        CartaForca bomba = new CartaForca("Esteróide", "concede +1 de força / custa 1 energia", 1);
        CartaVeneno dardo = new CartaVeneno("dardo envenenado", "concede +1 de veneno / custa 1 energia", 1);
        for (int i = 0; i < 2; i++){    /*coloquei 8 cartas na pilha de compras, 2 de cada tipo*/
            compra.push(espada);
            compra.push(escudo);
            compra.push(bomba);
            compra.push(dardo);
        }
        Collections.shuffle(compra);  /*embaralhando */
        return compra;
    }
    public void comprarCartas(){
            while (cartasNaMao.size() < 5){    /*comprando cartas*/
                if (deckCompra.isEmpty()){
                    Collections.shuffle(deckDescarte);
                    deckCompra.addAll(deckDescarte);
                    deckDescarte.clear();
                }
                cartasNaMao.add(deckCompra.pop());
            }
    }
    public void cartaUsada(int comando){
        deckDescarte.push(cartasNaMao.remove(comando - 1));
    }
}