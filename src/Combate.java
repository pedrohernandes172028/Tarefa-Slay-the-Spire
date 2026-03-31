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
            statusPersonagens();
            heroi.resetarenergia();
            heroi.getBaralho().comprarCartas();
            boolean continua;
            verificarEfeitosGeral(1);
            if (verificarMortos()){
                return false;
            }
            List<Entidade> copiaOrdenado = new ArrayList<>(ordenado);

            for (int j = 0; j < copiaOrdenado.size(); j ++){
                continua = true;
                while (continua){   /*para que o heroi possa fazer mais de uma ação e o inimigo possa fazer mais de uma ação futuramente */
                    if (copiaOrdenado.get(j).estarVivo()){
                        if (copiaOrdenado.get(j) instanceof Heroi){ /*vez do heroi */
                            statusPersonagens();
                            continua = copiaOrdenado.get(j).realizarAcao(heroi, inimigos, comandoScanner);
                            if (heroi.getAlvo() != null){  /*significa que o heroi realizou uma ação */
                                System.out.println();
                                verificarEfeitosAlvo(copiaOrdenado.get(j).cartaUtilizada(copiaOrdenado.get(j).getAcaoEscolhida()).getMomentos(), copiaOrdenado.get(j));
                                verificarEfeitosAlvo(copiaOrdenado.get(j).cartaUtilizada(copiaOrdenado.get(j).getAcaoEscolhida()).getMomentos() + 1, copiaOrdenado.get(j).getAlvo());
                            }
                        }else{  /*vez do inimigo */
                            continua = copiaOrdenado.get(j).realizarAcao(heroi, inimigos, comandoScanner);
                            System.out.println();
                            verificarEfeitosAlvo(copiaOrdenado.get(j).cartaUtilizada(copiaOrdenado.get(j).getAcaoEscolhida()).getMomentos(), copiaOrdenado.get(j));
                            verificarEfeitosAlvo(copiaOrdenado.get(j).cartaUtilizada(copiaOrdenado.get(j).getAcaoEscolhida()).getMomentos() + 1, copiaOrdenado.get(j).getAlvo());
                        }
                        if (verificarMortos()){
                            return false;
                        }
                    }
                }
            }
            heroi.resetarEscudo();
            
            verificarEfeitosGeral(2);
            if (verificarMortos()){
                return false;
            }
            return true;
            }

        private void statusPersonagens(){
            heroi.getStatus();
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
                    if (desordenado.get(j).getVelocidade() < desordenado.get(j + 1).getVelocidade()){
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
            for (int j= alvo.getEfeitos().size() - 1; j >= 0; j--){    /*percorrendo todos os efeitos da entidade*/
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

        private boolean verificarMortos(){  /*retorna false para o jogo continuar, true para morte de todos os inimigos ou para morte do heroi */
            for (int alvo = 0; alvo < inimigos.size(); alvo++){
                if (inimigos.get(alvo).estarVivo() == false){
                    System.out.println(inimigos.get(alvo).getNome() + " foi derrotado!");
                    ordenado.remove(inimigos.get(alvo));
                    inimigos.remove(alvo);
                    alvo--;
                }
            }
            if (inimigos.isEmpty()){    /*acabou o jogo */
                System.out.println("Todos os inimigos foram derrotados! Você venceu.");
                return true;
            }else if (heroi.estarVivo() == false){
                System.out.println(heroi.getNome() + "foi derrotado! Você perdeu.");
                return true;
            }
            return false;
        }

    }