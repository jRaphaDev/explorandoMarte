package br.com.elo7.service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.elo7.logic.SondaLogic;

public class DefaultSondaService implements SondaService{

	private SondaLogic sondaLogic;
	private Logger logger = LoggerFactory.getLogger(DefaultSondaService.class);
	
	@Inject
	public DefaultSondaService(SondaLogic sondaLogic) {
		this.sondaLogic = sondaLogic;
	}
	
	@Override
	public Response posicionar(String posicao) {
		try {
			sondaLogic.posicionar(posicao);
			return Response.noContent().build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

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
