package br.com.elo7.logic;

import javax.inject.Named;

import br.com.elo7.model.Sonda;

/**
* 
* @author Raphael Freitas
* 
* A classe DefaultSondaLogic é responsavel por ter os metodos dos quais foram criados na interface SondaLogic e 
* mais métodos privados
* 
*@version 1.0
*/

@Named
public class DefaultSondaLogic implements SondaLogic{

	private Sonda sonda;
	
	public DefaultSondaLogic() {
		this.sonda = new Sonda();
	}

	/**
	 * Esse método é responsável em dizer onde estará o posicionamento da sonda.
	 * @param int posicaoX : posição x onde a sonda será posicionada (para cima ou para baixo).
	 * @param int posicaoY : posição y onde a sonda será posicionada (para esquerda ou para direita).
	 * @param String sentido : sentido para onde a sonda estará apontado
	 * @return String "Posição iniciada" quando o sentido for validado e retornar true ou String "Sentido inválido" quando o sentido for validado e retornar false.
	 */
	@Override
	public String setarPosicao(int posicaoX, int posicaoY, char sentido) {
		try {
			if (verificaSentido(sentido)) {
				sonda.setPosicaoX(posicaoX);
				sonda.setPosicaoY(posicaoY);
				sonda.setSentido(sentido);
				return "Posicao iniciada";
			} else {
				return "Sentido inválido";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * Esse método é responsável em destrinchar as instruções enviadas, e direcionar cada uma para sua respectiva função, seja mover a sonda uma casa ou direciona-la 90° para direita ou para esquerdar.
	 * @param String instrucoes : instruções envidas pelo usuario, quando na instrução conter 'M' a função moverSonda() é chamada ou seja conter 'L' ou 'R', a função direcionaSonda(instruçao) é chamada.
	 * @return retorna Posição atual quando as intruções forem validadas e retornar true ou "Instrução inválida" quando as instruções forem validadas e retornar false.
	 */
	@Override
	public String setarInstrucoes(String instrucoes) {
		try {
			if (verificaInstrucao(instrucoes)) {
				for (int a = 0; a < instrucoes.length(); a++) {
					if (instrucoes.charAt(a) == 'M') {
						moverSonda();
					} else {
						direcionarSonda(instrucoes.charAt(a));
					}
				}
				return pegarPosicaoAtual();
			} else {
				return "Instrução inválida";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * Esse método é responsavel em mover a sonda de acordo com o sentido que ele ja estava determinado,
	 * quando o sentido for para o norte(N) (x, (y+1)), quando o sentido for para o sul(S) (x, (y-1)), 
	 * quandO o sentido for para leste(E) ((x+1), y) ou quando o sentido for para oeste(W) ((x-1), y). 
	 */
	private void moverSonda() {
		try {
			switch (sonda.getSentido()) {
				case 'N':
					sonda.setPosicaoY(sonda.getPosicaoY()+1);
					break;
				case 'S':
					sonda.setPosicaoY(sonda.getPosicaoY()-1);
					break;
				case 'W':
					sonda.setPosicaoX(sonda.getPosicaoX()-1);
					break;
				case 'E':
					sonda.setPosicaoX(sonda.getPosicaoX()+1);
					break;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Esse método é responsavel em direcionar a sonda, girar ela em 90° de acordo com o parametro sentido.
	 * @param sentido quando 'L' a sonda irá viras 90° para a esquerda, quando o sentido for 'R' a sonda irá virar 90° para a direita.
	 */
	private void direcionarSonda(char sentido) {
		try {
			
			switch (sonda.getSentido()) {
				case 'N':
					sentido = (sentido=='L') ? 'W':'E';
					break;
				case 'S':
					sentido = (sentido=='L') ? 'E':'W';
					break;
				case 'W':
					sentido = (sentido=='L') ? 'S':'N';
					break;
				case 'E':
					sentido = (sentido=='L') ? 'N':'S';
					break;
			}
			sonda.setSentido(sentido);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Esse método é responsável em verificar se na instrução enviada existe algum caracter diferente de 'M', 'L' e 'R'
	 * @param instrucao
	 * @return retorna true se não existir nenhum caracter alem do esperado e retorna false se vier algum caracter inválido.
	 */
	private boolean verificaInstrucao(String instrucao) {
		try {
			for (int a=0; a<instrucao.length(); a++) {
				if (instrucao.charAt(a) != 'M' && instrucao.charAt(a) != 'L' && instrucao.charAt(a) != 'R') {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	
	/**
	 * Esse método é responsável em verificar se o sentido passado pelo usuário é diferente de 'N', 'S', 'E' e 'W'.
	 * @param sentido
	 * @return retorna true se não existir nenhum caracter alem do esperado e retorna false se vier alguma caracter inválido.
	 */
	private boolean verificaSentido(char sentido) {
		try {
			if (sentido == 'N' || sentido == 'S' || sentido == 'E' || sentido == 'W') {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * @return posicaoX, posicaoY e o sentido em que a sonda se encontra.
	 */
	private String pegarPosicaoAtual() {
		return sonda.getPosicaoX() + " " + sonda.getPosicaoY() + " " + sonda.getSentido();
	}
}
