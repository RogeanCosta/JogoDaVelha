package softblue.jogodavelha.pontuacao;

/**
 * Interface utilizada para gerencia a pontuação. Classes que implementam esta interface devem
 * implementar a lógica de como gravar e ler a pontuação.
 * 
 */
public interface GerenciadorPontuacao {

	/**
	 * Obtém a pontuação de determinado jogador.
	 * @param nome Nome do jogador para procurar a pontuação
	 * @return A pontuação do jogador ou null caso a pontuação não seja encontrada
	 */
	public Integer getPontuacao(String nome);
	
	
	/**
	 * Incrementa em uma unidade a pontuação do jogador
	 * @param nome Nome do jogador para procurar e/ou gravar a pontuação
	 * @throws PontuacaoException lançada caso ocorra algum problema ao gravar a pontuação
	 */
	public void gravarPontuacao(String nome) throws PontuacaoException;
}
