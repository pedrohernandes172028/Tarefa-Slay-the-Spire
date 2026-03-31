import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Entidades.Entidade;
    import Entidades.Heroi;
    import Entidades.Inimigo;

    public class Combate {  /*vai controlar ordem de turnos, realização de ações de inimigos e heroi, notificar efeitos, controlar decks de cartas */
        private List<Entidade> ordenado; /*lista de todas as entidades ordenadas por ordem de velocidade */
        private List<Inimigo> inimigos;
        private Heroi heroi; 

        public Combate(Heroi heroi, List<Inimigo> inimigos){
            this.heroi = heroi;
            this.inimigos = inimigos;
            this.ordenado = ordenar(inimigos, heroi);
        }

        public boolean realizarTurno(Scanner comandoScanner){
            heroi.resetarenergia();
            heroi.getBaralho().comprarCartas();
            boolean continua;
            Entidade personagem;    /*personagem que está realizando a ação */
            verificarEfeitosGeral(0);
            if (verificarMortos()){
                if (heroi.estarVivo() == false || inimigos.isEmpty()){
                    return false;
                }
            }
            statusPersonagens();
            for (int j = 0; j < ordenado.size(); j ++){
                personagem = ordenado.get(j);
                if (personagem.estarVivo() == false){
                    continue;
                }
                continua = true;
                while (continua){   /*para que o heroi possa fazer mais de uma ação e o inimigo possa fazer mais de uma ação futuramente */
                    continua = personagem.realizarAcao(heroi, inimigos, comandoScanner);
                    if (personagem.getAcaoEscolhida() != null && ordenado.get(j).getAlvo() != null) {    /*verifica efeitos apenas quando o heroi escolheu uma ação */
                        System.out.println();   
                        verificarEfeitosAlvo(personagem.getAcaoEscolhida().getMomentos(), personagem);
                        verificarEfeitosAlvo(personagem.getAcaoEscolhida().getMomentos() + 1, personagem.getAlvo());
                    }
                    if (verificarMortos()){
                        if (heroi.estarVivo() == false || inimigos.isEmpty()){
                            return false;
                        }
                        j = ordenado.indexOf(personagem);   /*conserta a ordenação de j caso verificarMortos desloque a lista ordenados */
                        
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

        private void statusPersonagens(){
            heroi.getStatus();
            System.out.println();
            for (int l = 0; l < inimigos.size(); l++){
                inimigos.get(l).getStatus();
                inimigos.get(l).anuncio(heroi, inimigos);  /*já escolhe a ação do inimigo*/
                System.out.println();
            }
        }    

        private ArrayList<Entidade> ordenar(List<Inimigo> inimigos, Heroi heroi){   /*ideia do bubble sort*/
            Entidade guarda;
            ArrayList<Entidade> desordenado = new ArrayList<>(inimigos);
            desordenado.add(heroi);
            for (int i = desordenado.size(); i > 0; i--){
                for (int j = 0; j < i - 1; j++){
                    if (desordenado.get(j).getVelocidade() < desordenado.get(j + 1).getVelocidade()){   /*maiores velocidades vão ser os primeiros da lista */
                        guarda = desordenado.get(j);
                        desordenado.set(j, desordenado.get(j + 1));
                        desordenado.set(j + 1, guarda);
                    }
                }
            }
            return desordenado;
        }
        private void verificarEfeitosGeral(int momento){
            for (int i = 0; i < ordenado.size(); i ++){    /*percorrendo todas as entidades */
                verificarEfeitosAlvo(momento, ordenado.get(i));
            }
        }
        private void verificarEfeitosAlvo(int momento, Entidade alvo){    /*analizar os efeitos da entidade que são ativados por momento */
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

        private boolean verificarMortos(){  /*retorna false para o jogo continuar, true para morte de algum personagem */
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