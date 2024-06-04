package org.example.service;

import jakarta.ws.rs.core.Response;
import org.example.entities.AtividadeNoSite;
import org.example.entities.Denuncia;
import org.example.repository.AtividadeNoSiteRepository;
import org.example.repository.DenunciaRepository;
import org.example.repository.UsuarioRepository;

import java.time.LocalDateTime;

public class DenunciaService {
    private static final AtividadeNoSiteRepository atividadeNoSiteRepository = new AtividadeNoSiteRepository();
    private static final DenunciaRepository denunciaRepository = new DenunciaRepository();

    public Response insereDenuncia(Denuncia denuncia, String nomeUsuario) {
        if(denunciaRepository.Create(denuncia, nomeUsuario)) {
            atividadeNoSiteRepository.Create(new AtividadeNoSite("N", LocalDateTime.now()));
            return Response.ok("Denuncia inserida com sucesso").build();
        }
        return Response.serverError().entity("Erro ao adicionar denuncia").build();
    }
}
