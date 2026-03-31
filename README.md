História:
Por enquanto, você é um herói lutando contra um filhote de cobra. Derrote ela ou morra tentando!

Descrição:
Crie o seu personagem e lute contra uma cobrinha. No começo de cada turno, compre cartas da pilha de compras até ficar com 5 na sua mão. Durante esse processo, se ficar sem cartas na pilha de compras, embaralhe a sua pilha de descarte e use-a como a sua nova pilha de compras.
Com as cartas em mãos, use elas gastando suas 3 energias por turno e as descarte na pilha de descarte. Depois que estiver sem energia ou quiser passar sua vez, a cobrinha irá atacar! 
Depois do ataque dela, será seu turno novamente. Assim, o jogo acaba quando os pontos de vida de um dos dois chegue a zero.

Explicação das novidades de cada classe:
Main: apenas declara quais vão ser os inimigos e heroi da fase
Combate: realiza o processo de turnos, ordena a vez de cada personagem e controla quando ocorre efeitos, mortes e uso de cartas
Entidade: possuem o método realizar ação, ou seja, a partir de uma carta e um alvo, esse método realiza o uso da sua respectiva carta; além disso, possuem os atributos acaoescolhida, velocidade e alvo (explicados na própria entidade)
Inimigos: o método anuncio cria uma estratégia arcaica para cada tipo de inimigo, escolhendo alvo e posicao da ação em relação ao array acoes. O atributo acoes é um array de cartas, ou seja, os inimigos também podem usar as mesmas cartas que o heroi. Ainda pretendemos criar cartas que apenas os inimigos podem usar.
Baralho: serve apenas para cuidar das cartas do heroi.
Efeitos: possuem o atributo momentos, que serve para armazenar todos os momentos com que um efeito é ativado, sejá pela ativação das cartas ou pelo começo e  final do turno.
Cartas: as cartas que envolvem efeitos já declaram os atributos desses efeitos, pretendemos adicionar mais cartas com os mesmos efeitos mas com mais pontos de dano, etc. Além disso, as cartas possuem o atributo momentos, que serve para ser comparado com os momentos dos efeitos, por exemplo, ao usar a cartaDano, o efeito força é ativado pelo seu dono. Além disso, o momento da carta somado a 1 representa o momento quando alguem recebe essa carta, por exemplo, ao usar a cartaDano, o efeito Destreza (que ainda não foi implementado) é ativado  pelo alvo dessa carta.
Lista da representação de cada inteiro para os momentos:
0 = inicio de turno
1 = final de turno
2 = ao usar cartaDano
3 = ao receber cartaDano
4= ao usar cartaEscudo
5 = ao receber cartaEscudo 
6 = ao usar cartaForca
7 = ao receber cartaForca
8 = ao usar cartaVeneno
9 = ao receber cartaVeneno

Compilar:
javac -d bin $(find src -name "*.java")

Executar:
java -cp bin App