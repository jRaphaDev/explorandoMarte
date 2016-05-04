package br.com.elo7.exec;

import java.io.IOException;

import br.com.elo7.logic.DefaultSondaLogic;

public class Programa {

public static void main(String[] args) throws Exception {
		
		DefaultSondaLogic defaultSondaLogic = new DefaultSondaLogic();
		defaultSondaLogic.posicionar("1 2 N");
		System.out.println(defaultSondaLogic.setarInstrucoes("LMLMLMLMM"));
		
		//defaultSondaLogic.setarPosicao(3, 3, 'E');
		//System.out.println(defaultSondaLogic.setarInstrucoes("MMRMMRMRRM"));
		
	}
}
