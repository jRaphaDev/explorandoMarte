package br.com.elo7.service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.elo7.logic.SondaLogic;
/**
 * * @author Raphael Freitas
* 
* A classe DefaultSondaService é responsável por chamar os metodos da camada de logica
* e retornar o response de acordo com a resposta
 *
 */
public class DefaultSondaService implements SondaService{

	private SondaLogic sondaLogic;
	private Logger logger = LoggerFactory.getLogger(DefaultSondaService.class);
	
	@Inject
	public DefaultSondaService(SondaLogic sondaLogic) {
		this.sondaLogic = sondaLogic;
	}
	
	/**
	 * Metodo responsavel em posicionar a sonda.
	 * @return 200 quando Ok, 500 quando cair em algum tipo de validação ou erro não esperado.
	 */
	@Override
	public Response posicionar(String posicao) {
		try {
			sondaLogic.posicionar(posicao);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	/**
	 * Metodo responsavel em enviar as intruções para sonda.
	 * @return 200 quando Ok, 500 quando cair em algum tipo de validação ou erro não esperado.
	 */
	@Override
	public Response setarInstrucoes(String instrucao) {
		try {
			String posicaoAtual = sondaLogic.setarInstrucoes(instrucao);
			return Response.status(Status.OK).entity(posicaoAtual).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

}
