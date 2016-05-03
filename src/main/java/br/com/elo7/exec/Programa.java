package br.com.elo7.exec;

import java.io.IOException;

import br.com.elo7.logic.DefaultSondaLogic;

public class Programa {

public static void main(String[] args) throws IOException {
		
		DefaultSondaLogic defaultSondaLogic = new DefaultSondaLogic();
		defaultSondaLogic.setarPosicao(1, 2, 'N');
		System.out.println(defaultSondaLogic.setarInstrucoes("LMLMLMLMM"));
		
		defaultSondaLogic.setarPosicao(3, 3, 'E');
		System.out.println(defaultSondaLogic.setarInstrucoes("MMRMMRMRRM"));
		
	}
}
