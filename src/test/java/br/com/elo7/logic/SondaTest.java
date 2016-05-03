package br.com.elo7.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import br.com.elo7.logic.DefaultSondaLogic;

public class SondaTest {
	
	private DefaultSondaLogic defaultSondaLogic;
	
	@Before
	public void iniciarClasse() {
		this.defaultSondaLogic = new DefaultSondaLogic(); 
	}

	//Teste posições, sentidos e instruções Ok
	@Test
	public void setarPosicaoEInstrucoesTest() {
		defaultSondaLogic.setarPosicao(1, 2, 'N');
		String retorno = defaultSondaLogic.setarInstrucoes("LMLMLMLMM");
		
		//test
		assertNotNull(retorno);
		assertFalse(retorno.isEmpty());
		assertEquals("1 3 N", retorno);
	}
	
	//Teste posições e sentido Ok, instruções contem caracter inválido 'S'
	@Test
	public void setarInstrucaoInvalidaTest() {
		defaultSondaLogic.setarPosicao(1, 2, 'N');
		String retorno = defaultSondaLogic.setarInstrucoes("MSSSSMMLS");
		
		//test
		assertNotNull(retorno);
		assertFalse(retorno.isEmpty());
		assertEquals("Instrução inválida", retorno);
	}

	//Test posições e sentido Ok, instruções contem caracter inválido '-'
	@Test
	public void setarInstrucaoInvalidaCharacterTest() {
		defaultSondaLogic.setarPosicao(1, 2, 'N');
		String retorno = defaultSondaLogic.setarInstrucoes("-----");
		
		//test
		assertNotNull(retorno);
		assertFalse(retorno.isEmpty());
		assertEquals("Instrução inválida", retorno);
	}
	
	//Test posições e sentido Ok, instruções contem numeros
	@Test
	public void setarInstrucaoInvalidaNumeroTest() {
		defaultSondaLogic.setarPosicao(1, 2, 'N');
		String retorno = defaultSondaLogic.setarInstrucoes("12345");
		
		//test
		assertNotNull(retorno);
		assertFalse(retorno.isEmpty());
		assertEquals("Instrução inválida", retorno);
	}

	//Test posições Ok sentido inválio 
	@Test
	public void verificarSentidoDaSonda() {
		String retorno = defaultSondaLogic.setarPosicao(1, 2, 'K');
		
		//test
		assertNotNull(retorno);
		assertFalse(retorno.isEmpty());
		assertEquals("Sentido inválido", retorno);
	}
	
	@Test
	public void verificarInstrucaoParaNorte() {
		//M quando em direcao para norte = (x, (y+1))
		defaultSondaLogic.setarPosicao(1, 2, 'N');
		String retorno = defaultSondaLogic.setarInstrucoes("M");
		
		//test
		assertNotNull(retorno);
		assertFalse(retorno.isEmpty());
		assertEquals("1 3 N", retorno);
	}
	
	@Test
	public void verificarInstrucaoParaEast() {
		//M quando em direcao para lest = ((x+1), y)
		defaultSondaLogic.setarPosicao(1, 2, 'E');
		String retorno = defaultSondaLogic.setarInstrucoes("M");
		
		//test
		assertNotNull(retorno);
		assertFalse(retorno.isEmpty());
		assertEquals("2 2 E", retorno);
	}
	
	@Test
	public void verificarInstrucaoParaSul() {
		//M quando em direcao para sul = (x, (y-1))
		defaultSondaLogic.setarPosicao(1, 2, 'S');
		String retorno = defaultSondaLogic.setarInstrucoes("M");
		
		//test
		assertNotNull(retorno);
		assertFalse(retorno.isEmpty());
		assertEquals("1 1 S", retorno);
	}
	
	@Test
	public void verificarInstrucaoParaWest() {
		//M quando em direcao para oeste = ((x-1), y)
		defaultSondaLogic.setarPosicao(1, 2, 'W');
		String retorno = defaultSondaLogic.setarInstrucoes("M");
		
		//test
		assertNotNull(retorno);
		assertFalse(retorno.isEmpty());
		assertEquals("0 2 W", retorno);
	}
	
	
}
