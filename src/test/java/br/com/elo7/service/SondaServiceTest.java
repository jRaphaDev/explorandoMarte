package br.com.elo7.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.elo7.logic.DefaultSondaLogic;

public class SondaServiceTest {
	
	private static SondaService sondaService;
	private static DefaultSondaLogic defaultSondaLogic;
	
	@BeforeClass
	public static void iniciarAntesDaClasse() {
		defaultSondaLogic = mock(DefaultSondaLogic.class);
		sondaService = new DefaultSondaService(defaultSondaLogic);
	}

	@Test
	public void setarPosicao() throws Exception {
		Response response = sondaService.posicionar("1 2 N");
		assertEquals(response.getStatus(), 200);
	}
	
	@Test
	public void setarPosicaoEInstrucao() throws Exception {
		when(defaultSondaLogic.setarInstrucoes("M")).thenReturn("1 3 N");
		sondaService.posicionar("1 2 N");
		
		Response response = sondaService.setarInstrucoes("M");
		assertEquals(response.getEntity(), "1 3 N");
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void setarInstrucaoInvalida() throws Exception {
		sondaService.posicionar("1 2 N");
		when(defaultSondaLogic.setarInstrucoes("AAAAA")).thenThrow(new Exception("O caracter A nao e valido"));
		
		Response response = sondaService.setarInstrucoes("AAAAA");
		assertEquals(500, response.getStatus());
		assertTrue(response.getEntity().equals("O caracter A nao e valido"));
	}
	
	@Test
	public void setarInstrucaoSemIniciaAplicacao() throws Exception {
		when(defaultSondaLogic.setarInstrucoes("MM")).thenThrow(new Exception("A sonda nao recebeu as posicoes para ser iniciada."));
		
		Response response = sondaService.setarInstrucoes("MM");
		assertEquals(500, response.getStatus());
		assertTrue(response.getEntity().equals("A sonda nao recebeu as posicoes para ser iniciada."));
	}

}
