package br.com.elo7.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import br.com.elo7.logic.DefaultSondaLogic;

public class SondaLogicTest {
	
	private DefaultSondaLogic defaultSondaLogic;
	
	@Before
	public void iniciarClasse() {
		this.defaultSondaLogic = new DefaultSondaLogic(); 
	}

	//Teste Ok
	@Test
	public void setarPosicaoEInstrucoesTest() throws Exception {
		defaultSondaLogic.posicionar("1 2 N");
		String retorno = defaultSondaLogic.setarInstrucoes("LMLMLMLMM");

		//test
		assertNotNull(retorno);
		assertFalse(retorno.isEmpty());
		assertEquals("1 3 N", retorno);
	}

    @Test(expected=Exception.class)
    public void setarInstrucoesSemSetarPosicoesTest() throws Exception {
        defaultSondaLogic.setarInstrucoes("M");
    }

	//Teste numero de argumentos a mais.
	@Test(expected=Exception.class)
	public void setarPosicaoExecaoNumeroDeArgumentosTest() throws Exception {
		defaultSondaLogic.posicionar("1 2 N 4 ");
	}

	@Test(expected=Exception.class)
    public void setarPosicaoExecaoNumeroDeArgumentosMenorTest() throws Exception {
        defaultSondaLogic.posicionar("1 ");
    }

	//Teste posicaoX é maior que o limite.
	@Test(expected=Exception.class)
	public void setarPosicaoExecaoPosicaoXMaiorQueNecessarioTest() throws Exception {
		defaultSondaLogic.posicionar("9 2 N");
	}

	//Teste posicaoX é menor que o limite.
	@Test(expected=Exception.class)
	public void setarPosicaoExecaoPosicaoXMenorQueNecessarioTest() throws Exception {
		defaultSondaLogic.posicionar("-1 2 N");
	}

	//Teste posicaoY é maior que o limite.
	@Test(expected=Exception.class)
	public void setarPosicaoExecaoPosicaoYMaiorQueNecessarioTest() throws Exception {
		defaultSondaLogic.posicionar("1 6 N");
	}

	//Teste posicaoY é menor que o limite.
	@Test(expected=Exception.class)
	public void setarPosicaoExecaoPosicaoYMenorQueNecessarioTest() throws Exception {
		defaultSondaLogic.posicionar("1 -1 N");
	}

	//Teste sentido inválido.
	@Test(expected=Exception.class)
	public void setarPosicaoSentidoInvalidoTest() throws Exception {
		defaultSondaLogic.posicionar("1 2 F");
	}

	//Teste posicaoX recebendo uma letra.
	@Test(expected=NumberFormatException.class)
	public void setarPosicaoXInvalidoTest() throws Exception {
		defaultSondaLogic.posicionar("X 2 N");
	}

	//Teste sentido recebendo numero.
	@Test(expected=Exception.class)
	public void setarPosicaSentidoInvalidoTest() throws Exception {
		defaultSondaLogic.posicionar("1 2 8");
	}

	//Teste instruções contem caracter inválido 'S'.
	@Test(expected=Exception.class)
	public void setarInstrucaoInvalidaTest() throws Exception {
		defaultSondaLogic.posicionar("1 2 N");
		defaultSondaLogic.setarInstrucoes("MSSSSMMLS");
	}

	//Test instruções contem caracter inválido '-'.
	@Test(expected=Exception.class)
	public void setarInstrucaoInvalidaCharacterTest() throws Exception {
		defaultSondaLogic.posicionar("1 2 N");
		defaultSondaLogic.setarInstrucoes("-----");
	}

	//Test instruções contem numeros
	@Test(expected=Exception.class)
	public void setarInstrucaoInvalidaNumeroTest() throws Exception {
		defaultSondaLogic.posicionar("1 2 N");
		defaultSondaLogic.setarInstrucoes("1");
	}

	@Test
	public void verificarInstrucaoParaNorte() throws Exception {
		//M quando em direcao para norte = (x, (y+1))
		defaultSondaLogic.posicionar("1 2 N");
		String retorno = defaultSondaLogic.setarInstrucoes("M");
		
		//test
		assertNotNull(retorno);
		assertFalse(retorno.isEmpty());
		assertEquals("1 3 N", retorno);
	}

	@Test
	public void verificarInstrucaoParaEast() throws Exception {
		//M quando em direcao para lest = ((x+1), y)
		defaultSondaLogic.posicionar("1 2 E");
		String retorno = defaultSondaLogic.setarInstrucoes("M");
		
		//test
		assertNotNull(retorno);
		assertFalse(retorno.isEmpty());
		assertEquals("2 2 E", retorno);
	}

	@Test
	public void verificarInstrucaoParaSul() throws Exception {
		//M quando em direcao para sul = (x, (y-1))
		defaultSondaLogic.posicionar("1 2 S");
		String retorno = defaultSondaLogic.setarInstrucoes("M");
		
		//test
		assertNotNull(retorno);
		assertFalse(retorno.isEmpty());
		assertEquals("1 1 S", retorno);
	}

	@Test
	public void verificarInstrucaoParaWest() throws Exception {
		//M quando em direcao para oeste = ((x-1), y)
		defaultSondaLogic.posicionar("1 2 W");
		String retorno = defaultSondaLogic.setarInstrucoes("M");
		
		//test
		assertNotNull(retorno);
		assertFalse(retorno.isEmpty());
		assertEquals("0 2 W", retorno);
	}

}
