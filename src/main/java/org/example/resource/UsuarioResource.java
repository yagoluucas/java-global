package org.example.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.Usuario;
import org.example.repository.UsuarioRepository;
import org.example.service.UsuarioService;

import java.util.List;

@Path("usuario")
public class UsuarioResource {
    UsuarioRepository usuarioRepository = new UsuarioRepository();
    UsuarioService usuarioService = new UsuarioService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getAll(){
        return usuarioRepository.ReadAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("login")
    public Response getUsuarioByName(@QueryParam("nome") String nome, @QueryParam("senha") String senha){
        Usuario usuario = new Usuario(nome, senha);
        return usuarioService.confirmaLogin(usuario);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUsuario(Usuario usuario){
        Response response = usuarioService.validaNomeUsuario(usuario);
        if(response == null) {
            usuarioRepository.Create(usuario);
            return Response.ok("Sucesso ao adicionar usuario").build();
        }
        return response;
    }
}
