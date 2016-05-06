package br.com.elo7.logic;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.elo7.model.Sonda;

/**

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
	private Logger logger = LoggerFactory.getLogger(DefaultSondaLogic.class);

	public DefaultSondaLogic() {
		this.sonda = new Sonda();
	}

	/**
	 * Esse método é responsável em dizer onde estará o posicionamento da sonda.
	 * @param String posicao : String com as posições para posicionar a sonda.
	 */
	@Override
	public void posicionar(String posicao) throws Exception {
		try {

		    posicao = verificaPosicao(posicao);

			sonda.setPosicaoX(Integer.parseInt(String.valueOf(posicao.charAt(0))));
			sonda.setPosicaoY(Integer.parseInt(String.valueOf(posicao.charAt(1))));
			sonda.setSentido(posicao.charAt(2));
			sonda.setIniciada(true);

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	/**
	 * Esse método é responsável em destrinchar as instruções enviadas, e direcionar cada uma para sua respectiva função, seja mover a sonda uma casa ou direciona-la 90° para direita ou para esquerdar.
	 * @param String instrucoes : instruções envidas pelo usuario, quando na instrução conter 'M' a função moverSonda() é chamada ou seja conter 'L' ou 'R', a função direcionaSonda(instruçao) é chamada.
	 * @return retorna Posição atual quando as intruções forem validadas e retornar true ou "A sonda nao recebeu as posicoes para ser iniciada." quando as instruções forem validadas e retornar false.
	 */
	@Override
	public String setarInstrucoes(String instrucao) throws Exception {
		try {
		    if (sonda.isIniciada()) {
    			instrucao = verificaInstrucao(instrucao);
    			for (int a = 0; a < instrucao.length(); a++) {
    				if (instrucao.charAt(a) == 'M') {
    					moverSonda();
    				} else {
    					direcionarSonda(instrucao.charAt(a));
    				}
    			}
    			sonda.setIniciada(false);
    			return pegarPosicaoAtual();
		    } else {
		        logger.error("A sonda nao recebeu as posicoes para ser iniciada.");
                throw new Exception("A sonda nao recebeu as posicoes para ser iniciada.");
		    }
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 
	 * @param posicao as posicoes enviadas pelo usuario para serem validadas, e de acordo com o erro fazer um throw com a respectiva menssagem.
	 * @return posicao tratada, sem espaços e tudo maiusculo.
	 * @throws Exception
	 */
	private String verificaPosicao(String posicao) throws Exception {
		try {
			posicao = posicao.replace(" ", "");
			posicao = posicao.toUpperCase();
			
			if (sonda.isIniciada()) {
				logger.error("A sonda já foi iniciada, envie as instrucoes.");
				throw new Exception("A sonda já foi iniciada, envie as instrucoes.");
			}
			
			if (posicao.length() > 3) {
				logger.error("So e permitido 3 caracteres sendo dois numeros e uma letra.");
				throw new Exception("So e permitido 3 caracteres sendo dois numeros e uma letra.");
			}
			
			if (posicao.length() < 3) {
                logger.error("Nao e permitido menos de 3 caracteres ");
                throw new Exception("Nao e permitido menos de 3 caracteres ");
            }

			int posicaoX = Integer.parseInt(String.valueOf(posicao.charAt(0)));
            int posicaoY = Integer.parseInt(String.valueOf(posicao.charAt(1)));
            char sentido = posicao.charAt(2);

			if (posicaoX > 8 || posicaoX < 0) {
				logger.error("Posicionamento invalido, a posição x so e valida entre no minimo 0 e no maximo 8");
				throw new Exception("Posicionamento invalido, a posição x so e valida entre no minimo 0 e no maximo 8");
			}
			
			if (posicaoY > 5 || posicaoY < 0) {
				logger.error("Posicionamento invalido, a posicao y so e valida entre no minimo 0 e no maximo 5");
				throw new Exception("Posicionamento invalido, a posicao y so e valida entre no minimo 0 e no maximo 5");
			}
			
			if (sentido != 'N' && sentido != 'S' && sentido != 'E' && sentido != 'W') {
				logger.error("Ultimo argumento invalido, o sentido da sonda deve ser 'N', 'S', 'E' ou 'W'");
				throw new Exception("Ultimo argumento invalido, o sentido da sonda deve ser 'N', 'S', 'E' ou 'W'");
			}

			return posicao;
		} catch (NumberFormatException nfe) {
			
			logger.error("Posicionamento inválido, só é permitido um numero para x, um numero para y e uma letra para o sentido.");
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
			logger.error(e.getMessage());
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
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Esse método é responsável em verificar se na instrução enviada existe algum caracter diferente de 'M', 'L' e 'R'
	 * @param instrucao
	 * @return retorna instrucao tratada, sem espaços e tudo maiusculo.
	 * @throws Exception 
	 */
	private String verificaInstrucao(String instrucao) throws Exception {
		try {
		    instrucao = instrucao.toUpperCase();
		    instrucao = instrucao.replace(" ", "");
		    for (int a=0; a<instrucao.length(); a++) {
				if (instrucao.charAt(a) != 'M' && instrucao.charAt(a) != 'L' && instrucao.charAt(a) != 'R') {
					logger.error("O caracter " + instrucao.charAt(a) + " nao e valido.");
					throw new Exception("O caracter " + instrucao.charAt(a) + " nao e valido.");
				}
			}
			return instrucao;
		} catch (Exception e) {
			logger.error(e.getMessage());
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
