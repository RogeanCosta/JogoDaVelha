package softblue.jogodavelha;

import java.util.ArrayList;
import java.util.List;

import softblue.jogodavelha.io.Console;
import softblue.jogodavelha.pontuacao.GerenciadorPontuacao;
import softblue.jogodavelha.pontuacao.GerenciadorPontuacaoArquivo;

/**
 *	Possui a l�gica do jogo 
 */
public class Jogo {
	/**
	 * Tabuleiro do jogo
	 */
	private Tabuleiro tabuleiro = new Tabuleiro();
	
	/**
	 * Lista com os dois jogadores que ir�o jogar
	 */
	private List<Jogador> jogadores= new ArrayList<>();

	/**
	 * Gerenciado de pontua��o
	 */
	private GerenciadorPontuacao gerenciadorPontuacao;

	
	/**
	 * Inicia o jogo
	 * @throws JogoDaVelhaException Lan�ada de ocorrer algum problema durante o andamento do jogo
	 */
	public void jogar() throws JogoDaVelhaException {
		
		System.out.println("=================");
		System.out.println("| JOGO DA VELHA |");
		System.out.println("=================\n");
		
		// Inicializa o gerenciador de pontua��o
		gerenciadorPontuacao = new GerenciadorPontuacaoArquivo();
		
		// Solicita o nome do primeiro jogador, cria o jogador e o adiciona na lista
		System.out.print("Nome do Jogador 1: ");
		String nome = Console.readString();
		jogadores.add(new Jogador(nome, 'X'));
		
		// Se o jogador informado j� tiver alguma pontua��o gravada no arquivo, mostrar� o n�mero de vit�rias
		Integer pontuacao = gerenciadorPontuacao.getPontuacao(nome);
		String msg = "O jogador %s j� possui %d %s! \n";
		
		if(pontuacao != null) {
			System.out.format(msg, nome, pontuacao, pontuacao == 1? "vit�ria" : "vit�rias");
		}
		
		// Solicita o nome do segundo jogador, cria o jogador e o adiciona na lista
		System.out.print("Nome do Jogador 2: ");
		nome = Console.readString();
		jogadores.add(new Jogador(nome, 'O'));
		
		// Se o jogador informado j� tiver alguma pontua��o gravada no arquivo, mostrar� o n�mero de vit�rias
		pontuacao = gerenciadorPontuacao.getPontuacao(nome);
				
		if(pontuacao != null) {
			System.out.format(msg, nome, pontuacao, pontuacao == 1? "vit�ria" : "vit�rias");
		}
		
		// Controla se o jogo est� finalizado ou n�o
		boolean finalizado = false;
		
		// �ndice do jogador atual. 0 para o jogador 1 e 1 para o jogador 2
		int indexJogadorAtual = 0;
		
		// Armazena o jogador que venceu ou null se o jogo empatou
		Jogador vencedor = null;
		
		// Inicia o loop do jogo
		while(!finalizado) {
			// Mostra o tabuleiro
			tabuleiro.imprimir();
			
			// Obt�m o jogador
			Jogador jogador = jogadores.get(indexJogadorAtual);
			
			System.out.println("Jogador  '" + jogador.getNome() + "' => ");
		
			boolean sequenciaEncontrada;
			try {
				// Solicita jogada ao jogador
				Jogada jogada = jogador.obterJogada();
				
				//Efetua a jogada feita
				sequenciaEncontrada = tabuleiro.efetuarJogada(jogada, jogador.getSimbolo());
				
			} catch (JogadaInvalidaException e) {
				//Se a jogada foi inv�lida, mostra o erro e solicita a jogada novamente
				System.out.println("Erro: " + e.getMessage());
				continue;
			}
			
			if(sequenciaEncontrada) {
				// Se uma sequ�ncia foi encontrada, o jogador ganhou e o jogo pode ser finalizado
				vencedor = jogador;
				finalizado = true;
				
			} else if (tabuleiro.isCompleto()) {
				// Se o tabuleiro est� completo e n�o houve sequ�ncia, o jogo termina empatado
				finalizado = true;
			}
			
			// Muda o �ndice do jogador atual. Fica alternando entre 0 e 1
			indexJogadorAtual = (indexJogadorAtual + 1) % jogadores.size(); 
		}
		
		System.out.println();
		
		if(vencedor == null ) {
			System.out.println("O jogo terminou empatado");
			
		} else {
			System.out.println("O jogador '" + vencedor.getNome() + "' venceu o jogo!");
			
			// O jogador que venceu tem sua pontua��o gravada
			gerenciadorPontuacao.gravarPontuacao(vencedor.getNome());
		}
		
		tabuleiro.imprimir();
	}
}
