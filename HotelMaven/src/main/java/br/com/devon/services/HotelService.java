package br.com.devon.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.devon.bo.HotelBO;
import br.com.devon.dto.HospedagemDTO;
import br.com.devon.entity.Hospede;

@Path("/")
public class HotelService {
	
	private HotelBO hotelBo = new HotelBO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscarHospedePorNome/{nomeHospede}")
	public Response buscarHospedePorNome(@PathParam("nomeHospede") String nomeHospede) {
		return Response.ok(hotelBo.buscarHospedePorNome(nomeHospede)).build();
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscarHospedagensPorHospedeNome/{nomeHospede}")
	public Response buscarHospedagensPorHospedeNome(@PathParam("nomeHospede") String nomeHospede) {
		return Response.ok(hotelBo.buscarHospedagensPorHospedeNome(nomeHospede)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscarHospedes/")
	public Response buscarHospedes() {
		return Response.ok(hotelBo.buscarHospedes()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscarHospedesResidentes/")
	public Response buscarHospedesResidentes() {
		return Response.ok(hotelBo.buscarHospedesResidentes()).build();
	}
	
	@POST
	@Produces("application/json;odata=verbose")
	@Consumes("application/json;odata=verbose")
	@Path("/novoHospede")
	public String novoHospede(Hospede hospede) {
		try {
			hotelBo.novoHospede(hospede.getNome(), hospede.getDocumento(), hospede.getTelefone());
		} catch (Exception e) {
			return e.getMessage();
		}
			
		return "Inserido com sucesso";
	}
	
	@POST
	@Produces("application/json;odata=verbose")
	@Consumes("application/json;odata=verbose")
	@Path("/novaHospedagem")
	public String novaHospedagem(HospedagemDTO hospedagem) {
		try {
			hotelBo.novaHospedagem(hospedagem.getHospede(), hospedagem.getDataEntrada(), hospedagem.getDataSaida(), hospedagem.isAdicionalVeiculo());
		} catch (Exception e) {
			return e.getMessage();
		}
		return "";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscarGastosHospedes")
	public Response buscarGastosHospedes() {
		return Response.ok(hotelBo.buscarGastosHospedes()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscarGastoTotalHospede/{nome}")
	public Response buscarGastoTotalHospede(@PathParam("nome") String nome) {
		return Response.ok(hotelBo.buscarGastoTotalHospede(nome)).build();
	}
	
}
