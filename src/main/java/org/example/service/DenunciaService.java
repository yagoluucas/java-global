package org.example.service;

import jakarta.ws.rs.core.Response;
import org.example.entities.Denuncia;
import org.example.repository.DenunciaRepository;
import org.example.repository.UsuarioRepository;

public class DenunciaService {
    private static final UsuarioRepository usuarioRepository = new UsuarioRepository();
    private static final DenunciaRepository denunciaRepository = new DenunciaRepository();

    public Response insereDenuncia(Denuncia denuncia, String nomeUsuario) {
        if(denunciaRepository.Create(denuncia, nomeUsuario)) {
            return Response.ok("Denuncia inserida com sucesso").build();
        }
        return Response.serverError().entity("Erro ao adicionar denuncia").build();
    }
}
