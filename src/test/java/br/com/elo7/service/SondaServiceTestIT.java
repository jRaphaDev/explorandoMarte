package br.com.elo7.service;

import static com.jayway.restassured.RestAssured.expect;
import org.junit.Test;

public class SondaServiceTestIT {
	
	private static final String PATH = "/explorandoMarte/rest/sonda";
	
	@Test
	public void posicionarTest() {
		expect().
        statusCode(200).
        when().
        get(PATH+"/posicao/12n");
	}
	
	@Test
	public void posicionarTamanhoInvalidoTest() {
		expect().
        statusCode(500).
        when().
        get(PATH+"/posicao/12nn");
	}
	
	@Test
	public void posicionarPrimeiroCaracterInvalidoTest() {
		expect().
        statusCode(500).
        when().
        get(PATH+"/posicao/x2n");
	}
	
	@Test
	public void posicionarSegundoCaracterInvalidoTest() {
		expect().
        statusCode(500).
        when().
        get(PATH+"/posicao/1xN");
	}
	
	@Test
	public void posicionarTerceiroCaracterInvalidoTest() {
		expect().
        statusCode(500).
        when().
        get(PATH+"/posicao/122");
	}
	
	@Test
	public void posicionarSentidoInvalidoTest() {
		expect().
        statusCode(500).
        when().
        get(PATH+"/posicao/12F");
	}
	
	@Test
	public void posicionarXMaiorTest() {
		expect().
        statusCode(500).
        when().
        get(PATH+"/posicao/92N");
	}
	
	@Test
	public void posicionarYMaiorTest() {
		expect().
        statusCode(500).
        when().
        get(PATH+"/posicao/19N");
	}
	
	@Test
	public void setarInstrucao() {
		expect().
        statusCode(200).
        when().
        get(PATH+"/posicao/12N");
		
		expect().
        statusCode(200).
        when().
        get(PATH+"/instrucao/M");
	}
	
	@Test
	public void setarInstrucaoInvalida() {
		expect().
        statusCode(200).
        when().
        get(PATH+"/posicao/12N");
		
		expect().
        statusCode(500).
        when().
        get(PATH+"/instrucao/MS");
	}

}
