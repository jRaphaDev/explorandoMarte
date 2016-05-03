package br.com.elo7.service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.elo7.logic.SondaLogic;

public class DefaultSondaService implements SondaService{

	private SondaLogic sondaLogic;
	
	@Inject
	public DefaultSondaService(SondaLogic sondaLogic) {
		this.sondaLogic = sondaLogic;
	}
	
	@Override
	public Response setarPosicao(int posicaoX, int posicaoY, char sentido) {
		try {
			
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return null;
	}

	@Override
	public Response setarInstrucoes(String instrucoes) {
		try {
			
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return null;
	}

}
