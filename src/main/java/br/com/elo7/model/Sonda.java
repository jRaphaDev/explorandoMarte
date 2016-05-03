package br.com.elo7.model;

/**
 * @author Raphael Freitas
 *
 * A classe Sonda é responsável em receber as combinações de coordenadas posicaoX, posicaoY e sentido,
 * caso seja 'N' para north, 'S' para south, 'E' para east e 'W' para Weast. 
 * 
 * @version 1.0
 *
 */
public class Sonda {

	private int posicaoX;
	private int posicaoY;
	private char sentido;
	
	public Sonda() {
		this.posicaoX = 8;
		this.posicaoY = 5;
		this.sentido = 'N';
	}

	public int getPosicaoX() {
		return posicaoX;
	}

	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}

	public int getPosicaoY() {
		return posicaoY;
	}

	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}
	
	public void setSentido(char sentido) {
		this.sentido = sentido;
	}
	
	public char getSentido(){
		return sentido;
	}

}
