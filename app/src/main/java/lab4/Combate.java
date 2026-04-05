package lab4;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lab4.Entidades.Entidade;
    import lab4.Entidades.Heroi;
    import lab4.Entidades.Inimigo;

    /**
     * vai controlar ordem de turnos, realização de ações de inimigos e heroi, notificar efeitos, controlar decks de cartas 
     */
    public class Combate {  
        /** lista de todas as entidades ordenadas por ordem de velocidade, ela diminui quando algum inimigo morre  */
        private List<Entidade> ordenado; 
        private List<Inimigo> inimigos;
        private Heroi heroi; 

        public Combate(Heroi heroi, List<Inimigo> inimigos){
            this.heroi = heroi;
            this.inimigos = inimigos;
        }

        /**
         * realiza um turno do jogo
         * @param comandoScanner serve apenas para abrir uma unica instancia de scanner, que esá localizada no App
         * @return retorna true para continuar o jogo ou retorna false se o heroi morrer ou todos os inimigos morrerem
         */
        public boolean realizarTurno(Scanner comandoScanner){
            ordenado = ordenar();  /* a ordem das entidades será atualizada no começo de cada turno geral */
            printordenado(); 
            heroi.resetarenergia();
            heroi.getBaralho().comprarCartas();
            boolean continua;
            Entidade jogando;    /*personagem que está realizando a ação */
            verificarEfeitosGeral(0);
            if (verificarMortos()){
                if (heroi.estarVivo() == false || inimigos.isEmpty()){
                    return false;
                }
            }
            statusPersonagens();
            decidirAcoes();
            for (int j = 0; j < ordenado.size(); j ++){
                jogando = ordenado.get(j);
                if (jogando.estarVivo() == false){
                    continue;
                }
                continua = true;
                while (continua){   /*para que o heroi possa fazer mais de uma ação e o inimigo possa fazer mais de uma ação futuramente */
                    if (jogando instanceof Heroi && j != 0){  /*o jogador vai receber os status de todos os personagens caso ele não seja o primeiro a jogar no turno*/
                        statusPersonagens();
                    }
                    continua = jogando.realizarAcao(heroi, inimigos, comandoScanner);
                    if (jogando.getAcaoEscolhida() != null && ordenado.get(j).getAlvo() != null) {    /*verifica efeitos apenas quando o heroi escolheu uma ação */
                        System.out.println();   
                        verificarEfeitosAlvo(jogando.getAcaoEscolhida().getMomentos(), jogando);
                        verificarEfeitosAlvo(jogando.getAcaoEscolhida().getMomentos() + 1, jogando.getAlvo());
                    }
                    if (verificarMortos()){
                        if (heroi.estarVivo() == false || inimigos.isEmpty()){
                            return false;
                        }
                        j = ordenado.indexOf(jogando);   /*conserta a ordenação de j caso verificarMortos desloque a lista ordenados ao deletar algum inimigo*/
                        
                    }
                    
                }
                
            }
            heroi.resetarEscudo();
            verificarEfeitosGeral(1);
            if (verificarMortos()){
                if (heroi.estarVivo() == false || inimigos.isEmpty()){
                    return false;
                }
            }
            return true;
            }

        /**
         * Usado no começo de todos os turnos e, as vezes, antes do turno do Heroi para fornecer os status dos personagens quando o jogador mais precisa
         */
        private void statusPersonagens(){
            System.out.println("Status dos personagens:");
            heroi.getStatus();
            for (int l = 0; l < inimigos.size(); l++){
                inimigos.get(l).getStatus();
            }
            System.out.println();
        }    

        /** Usado no começo de todos os turnos para os inimigos definirem suas ações */
        private void decidirAcoes(){
            System.out.println("Anúncio dos inimigos:");
            for (int l = 0; l < inimigos.size(); l++){
                inimigos.get(l).anuncio(heroi, inimigos); 
            }
            System.out.println();
        }

        /**
         * Ordena o conjunto de personagens presentes no jogo com base no atributo velocidade na ordem decrescente. Em caso de empate, o heroi sempre agirá depois do inimigo de mesma velocidade.
         * @return retorna uma lista com todos os personagens do jogo ordenados com base no atributo velocidade na ordem decrescente
         */
        private ArrayList<Entidade> ordenar(){   /*ideia do bubble sort*/
            Entidade guarda;
            ArrayList<Entidade> desordenado = new ArrayList<>(inimigos);
            desordenado.add(heroi);
            for (int i = desordenado.size(); i > 0; i--){
                for (int j = 0; j < i - 1; j++){
                    if (desordenado.get(j).getVelocidade() < desordenado.get(j + 1).getVelocidade()){   /*maiores velocidades vão ser os primeiros da lista*/
                        guarda = desordenado.get(j);
                        desordenado.set(j, desordenado.get(j + 1));
                        desordenado.set(j + 1, guarda);
                    }
                }
            }
            return desordenado;
        }

        /**
         * Informa o usuário, pelo terminal, a ordem dos personagens em jogo. Usado no começo de um turno geral.
         */
        private void printordenado(){
            System.out.println("Ordem dos personagens:");
            for (int i = 0; i < ordenado.size(); i++){
                System.out.println((i + 1) + " -> " + ordenado.get(i).getNome());
            }
            System.out.println();
        }

        /**
         * Método usado após: o inicio e o final do turno geral
         * verifica se algum personagem com vida > 0 possui algum efeito que é ativado nos momentos analizados
         * @param momento  um inteiro que pode representar: 0 para o inicio do turno geral e 1 para o final do turno geral
         */
        private void verificarEfeitosGeral(int momento){
            for (int i = 0; i < ordenado.size(); i ++){    /*percorrendo todas as entidades */
                verificarEfeitosAlvo(momento, ordenado.get(i));
            }
        }

        /**
         * verifica se um personagem específico (alvo) possui algum efeito que é ativado pelo parâmetro momento
         * @param momento um inteiro que pode representar qualquer momento do jogo
         * @param alvo  entidade que terá seus efeitos verificados 
         */
        private void verificarEfeitosAlvo(int momento, Entidade alvo){
            boolean acionou = false;
            for (int j = alvo.getEfeitos().size() - 1; j >= 0; j--){    /*percorrendo todos os efeitos da entidade*/
                for (int k = 0; k < alvo.getEfeitos().get(j).getMomentos().length; k++){    /*percorrendo todos os momentos em que um efeito é ativado */
                    if (alvo.getEfeitos().get(j).getMomentos()[k] == momento){
                        alvo.getEfeitos().get(j).acionado();
                        acionou = true;
                        break;
                    }
                }
            }
            if (acionou){
                System.out.println();
            }
        }

        /**
         * Usado após alguma verificação de efeito ou do uso de alguma carta
         * Verifica se algum personagem morreu;
         * @return retorna false para o jogo continuar, true para morte de algum personagem 
         */
        private boolean verificarMortos(){  
            boolean houveMorte = false;
            for (int i = inimigos.size() - 1; i >= 0; i--) {
                    if (!inimigos.get(i).estarVivo()) {
                        System.out.println(inimigos.get(i).getNome() + " foi derrotado!");
                        ordenado.remove(inimigos.get(i));
                        inimigos.remove(i);
                        houveMorte = true;
                    }
                }
            if (inimigos.isEmpty()){    /*acabou o jogo, esse if serve mais para a saida do terminar do que para retornar true */
                System.out.println("Todos os inimigos foram derrotados! Você venceu.");
                return true;
            }else if (heroi.estarVivo() == false){
                System.out.println(heroi.getNome() + " foi derrotado! Você perdeu.");
                return true;
            }
            return houveMorte;
        }

    }