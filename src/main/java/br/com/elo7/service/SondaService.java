package br.com.elo7.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sonda")
public interface SondaService {

	@GET
	@Path("/posicao/{posicao}")
	@Produces(MediaType.APPLICATION_JSON)
	Response posicionar(@PathParam("posicao") String posicao);
	
	@GET
	@Path("/instrucao/{instrucao}")
	@Produces(MediaType.APPLICATION_JSON)
	Response setarInstrucoes(@PathParam("instrucao") String instrucao);

}
