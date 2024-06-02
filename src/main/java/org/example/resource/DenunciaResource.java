package org.example.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.entities.Denuncia;
import org.example.repository.DenunciaRepository;

import java.util.List;

@Path("denuncia")
public class DenunciaResource {
    DenunciaRepository denunciaRepository = new DenunciaRepository();
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
    public void addDenuncia(Denuncia denuncia) {
        denunciaRepository.Create(denuncia);
    }
}
