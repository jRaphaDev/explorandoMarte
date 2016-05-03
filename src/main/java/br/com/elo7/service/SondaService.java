package br.com.elo7.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/sonda")
public interface SondaService {

	@POST
	@Path("/x/{posicaoX}/y/{posicaoY}/sentido/{sentido}")
	Response setarPosicao(@PathParam("posicaoX")int posicaoX, @PathParam("posicaoY")int posicaoY, @PathParam("sentido")char sentido);
	
	@POST
	@Path("/instrucoes/{instrucoes}")
	Response setarInstrucoes(@PathParam("instrucoes")String instrucoes);

}
