package softblue.jogodavelha;

import softblue.jogodavelha.io.Console;

public class Jogador {

	/**
	 * Nome do Jogador.
	 */
	private String nome;
	
	/**
	 * Símbolo usado pelo jogador. Ex. 'X' ou 'O'
	 */
	private char simbolo;
	
	
	/**
	 * Construtor
	 * @param nome Nome do Jogador
	 * @param simbolo 'X' ou 'O', usado pelo jogador em suas jogadas
	 */
	public Jogador(String nome, char simbolo) {
		this.nome = nome;
		this.simbolo = simbolo;
	}
	
	
	public String getNome() {
		return this.nome;
	}
	
	
	public char getSimbolo() {
		return this.simbolo;
	}
	
	
	/**
	 * Lê a jogada do jogador do teclado. Deve ser feita no formato 'i', 'j';
	 * @return Jogada realizada
	 * @throws JogadaInvalidaException Lançada se a jogada for inválida
	 */
	public Jogada obterJogada() throws JogadaInvalidaException {
		String str = Console.readString();
		return new Jogada(str);
	}
}
