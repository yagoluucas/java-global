package org.example.service;

import jakarta.ws.rs.core.Response;
import org.example.entities.AtividadeNoSite;
import org.example.entities.Doacao;
import org.example.repository.AtividadeNoSiteRepository;
import org.example.repository.DoacaoRepository;
import org.example.repository.UsuarioRepository;

import java.time.LocalDateTime;

public class DoacaoService {
    private static final DoacaoRepository doacaoRepository = new DoacaoRepository();
    private static final UsuarioRepository usuarioRepository = new UsuarioRepository();
    private static final AtividadeNoSiteRepository atividadeDoSite = new AtividadeNoSiteRepository();
    public Response validaDoacao(Doacao doacao, String nome) {
        if (!nome.equals("null")) {
            doacao.setUsuario(usuarioRepository.ReadUserByName(nome));
        }
        if (doacaoRepository.Create(doacao)) {
            atividadeDoSite.Create(new AtividadeNoSite("S", LocalDateTime.now()));
            return Response.ok("00020126360014BR.GOV.BCB." +
                    "PIX0114+5561999999998520400005303986540410.005802BR5910" +
                    "Mr.Turtle6008Brasilia62070503***63041D3A").build();
        }
        return Response.serverError().entity("Erro ao adicionar doação").build();
    }
}
