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
	 * @param String posicao : String com as posições para posicionar a sonda.
	 * @return String "Posição iniciada" quando a posição for validado e retornar a posicao modificada.
	 * @throws Exception 
	 */
	@Override
	public String posicionar(String posicao) throws Exception {
		try {

			String posicaoNova = verificaPosicao(posicao);
			sonda.setPosicaoX(Integer.parseInt(String.valueOf(posicaoNova.charAt(0))));
			sonda.setPosicaoY(Integer.parseInt(String.valueOf(posicaoNova.charAt(1))));
			sonda.setSentido(posicaoNova.charAt(2));

			return "Posição iniciada";
			
		} catch (Exception e) {
			throw e;
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
			verificaInstrucao(instrucoes);
			for (int a = 0; a < instrucoes.length(); a++) {
				if (instrucoes.charAt(a) == 'M') {
					moverSonda();
				} else {
					direcionarSonda(instrucoes.charAt(a));
				}
			}
			return pegarPosicaoAtual();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	private String verificaPosicao(String posicao) throws Exception {
		try {
			posicao = posicao.replace(" ", "");
			posicao = posicao.toUpperCase();
			
			int posicaoX = Integer.parseInt(String.valueOf(posicao.charAt(0)));
			int posicaoY = Integer.parseInt(String.valueOf(posicao.charAt(1)));
			char sentido = posicao.charAt(2);
			
			if (posicao.length() > 3) {
				System.out.println("Só é permitido 3 caracteres sendo dois numeros e uma letra.");
				throw new Exception("Só é permitido 3 caracteres sendo dois numeros e uma letra.");
			}

			if (posicaoX > 8 || posicaoX < 0) {
				System.out.println("Posicionamento inválido, a posição x só é valida entre no minimo 0 e no máximo 8");
				throw new Exception("Posicionamento inválido, a posição x só é valida entre no minimo 0 e no máximo 8");
			}
			
			if (posicaoY > 5 || posicaoY < 0) {
				System.out.println("Posicionamento inválido, a posição y só é valida entre no minimo 0 e no máximo 5");
				throw new Exception("Posicionamento inválido, a posição y só é valida entre no minimo 0 e no máximo 5");
			}
			
			if (sentido != 'N' && sentido != 'S' && sentido != 'E' && sentido != 'W') {
				System.out.println("Ultimo argumento inválido, o sentido da sonda deve ser 'N', 'S', 'E' ou 'W'");
				throw new Exception("Ultimo argumento inválido, o sentido da sonda deve ser 'N', 'S', 'E' ou 'W'");
			}

			return posicao;		
		} catch (NumberFormatException nfe) {
			
			System.out.println("Posicionamento inválido, só é permitido um numero para x, um numero para y e uma letra para o sentido.");
			throw new NumberFormatException("Posicionamento inválido, só é permitido um numero para x, um numero para y e uma letra para o sentido.");
			
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
	 * @throws Exception 
	 */
	private void verificaInstrucao(String instrucao) throws Exception {
		try {
			for (int a=0; a<instrucao.length(); a++) {
				if (instrucao.charAt(a) != 'M' && instrucao.charAt(a) != 'L' && instrucao.charAt(a) != 'R') {
					throw new Exception("O caracter " + instrucao.charAt(a) + " não é valido.");
				}
			}
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
