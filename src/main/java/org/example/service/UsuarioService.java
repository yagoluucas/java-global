package org.example.service;

import jakarta.ws.rs.core.Response;
import org.example.entities.Usuario;
import org.example.repository.UsuarioRepository;

public class UsuarioService {
    public static final UsuarioRepository usuarioRepository = new UsuarioRepository();
    public Response confirmaLogin(Usuario usuario){
        Usuario usuarioLocalizado = usuarioRepository.ReadUserByName(usuario.getNome());
        if(usuarioLocalizado.getNome() == null){
            return Response.ok("Usuário não localizado").build();
        }
        else if(!usuarioLocalizado.getNome().equals(usuario.getNome())
                || !usuarioLocalizado.getSenha().equals(usuario.getSenha())) {
            System.out.println(usuarioLocalizado.getNome().equals(usuario.getNome())
                    || usuarioLocalizado.getSenha().equals(usuario.getSenha()));
            return Response.ok("Nome do usuário ou senha incorretos").build();

        }
        return Response.ok("Usuário logado").build();
    }

    public Response validaNomeUsuario(Usuario usuario){
        Usuario usuarioLocalizado = usuarioRepository.readUserByNameOrEmail(usuario.getNome(), usuario.getEmail());
        if(usuarioLocalizado.getNome() == null){
            return null;
        }
        return Response.ok("Nome de usuário ou email já existente").build();
    }
}
