package br.com.elo7.exec;

import java.util.Scanner;


import br.com.elo7.logic.DefaultSondaLogic;

public class Programa {

@SuppressWarnings("resource")
public static void main(String[] args) throws Exception {
		DefaultSondaLogic defaultSondaLogic = new DefaultSondaLogic();
		
		Scanner scanner = new Scanner(System.in);
		System.out.printf("Digite as posicoes : ");
		String posicoes = scanner.nextLine();
		defaultSondaLogic.posicionar(posicoes);
		
		System.out.printf("Digite as instruções : ");
		String instrucoes = scanner.nextLine();
		String posicaoAtual = defaultSondaLogic.setarInstrucoes(instrucoes);
		
		System.out.println(posicaoAtual);
		
		
	}
}
