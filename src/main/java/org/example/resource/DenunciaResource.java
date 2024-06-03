package org.example.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.Denuncia;
import org.example.repository.DenunciaRepository;
import org.example.service.DenunciaService;

import java.util.List;

@Path("denuncia")
public class DenunciaResource {
    DenunciaRepository denunciaRepository = new DenunciaRepository();
    DenunciaService denunciaService = new DenunciaService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Denuncia> getAll(){
        return denunciaRepository.ReadAll();
    }

    @GET
    @Produces
    @Path("{usuario}")
    public List<Denuncia> getAllDenunciaByUser(@PathParam("usuario") String nomeUsuario) {
        return denunciaRepository.ReadDenunciaByUser(nomeUsuario);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDenuncia(Denuncia denuncia, @QueryParam("usuario") String nomeUsuario){
        return denunciaService.insereDenuncia(denuncia, nomeUsuario);
    }
}
