package org.example.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.Doacao;
import org.example.repository.DoacaoRepository;
import org.example.service.DoacaoService;

import java.util.List;

@Path("doacao")
public class DoacaoResource {
    private static final DoacaoRepository doacaoRepository = new DoacaoRepository();
    private static final DoacaoService doacaoService = new DoacaoService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDoacao(@QueryParam("nome") String nome, Doacao doacao) {
        return doacaoService.validaDoacao(doacao, nome);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Doacao> getDoacao() {
        return doacaoRepository.ReadAll();
    }
}
