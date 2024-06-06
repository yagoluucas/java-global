package org.example.service;

import jakarta.ws.rs.core.Response;
import org.example.entities.AtividadeNoSite;
import org.example.entities.Doacao;
import org.example.infrastrutue.QrCodeApi;
import org.example.repository.AtividadeNoSiteRepository;
import org.example.repository.DoacaoRepository;
import org.example.repository.UsuarioRepository;

import java.time.LocalDateTime;

public class DoacaoService {
    private static final DoacaoRepository doacaoRepository = new DoacaoRepository();
    private static final UsuarioRepository usuarioRepository = new UsuarioRepository();
    private static final AtividadeNoSiteRepository atividadeDoSite = new AtividadeNoSiteRepository();

    private static final QrCodeApi qrCodeApi = new QrCodeApi();
    public Response validaDoacao(Doacao doacao, String nome) {
        if (!nome.equals("null")) {
            doacao.setUsuario(usuarioRepository.ReadUserByName(nome));
        }
        if (doacaoRepository.Create(doacao)) {
            atividadeDoSite.Create(new AtividadeNoSite("S", LocalDateTime.now()));
            return Response.ok(qrCodeApi.createQrCodeUrl("valor%20doado:%20" + doacao.getValor() + "%20obrigado")).build();
        }
        return Response.serverError().entity("Erro ao adicionar doação").build();
    }
}
