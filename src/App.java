import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import Entidades.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Digite o nome do seu personagem:"); /*definindo a fase do meu jogo */
        Scanner comandoScanner = new Scanner (System.in);
        String nome = comandoScanner.nextLine(); 
        Heroi heroi1 = new Heroi(nome, 40, 5,10);  /*declarando heroi, inimigo e cartas */
        Inimigo inimigo1 = new Cobrinha("Cobrinha1", 10, 5, 10, 8);
        Inimigo inimigo2 = new Cobrinha("Cobrinha2", 10, 5, 10, 8);
        Inimigo inimigo3 = new Cobrinha("Cobrinha3", 10, 5, 10, 8);
        Combate combate = new Combate(heroi1, new ArrayList<>(List.of(inimigo1, inimigo2, inimigo3)));

        while (combate.realizarTurno(comandoScanner)); /*continua o jogo até que o heroi morra ou que todos os inimigos vençam */
        comandoScanner.close();

    }
}