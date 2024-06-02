package org.example.repository;

import org.example.config.OracleDatabase;
import org.example.config._Logger;
import org.example.entities.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsuarioRepository implements _BaseRepository<Usuario>, _Logger<Usuario> {

    private static final OracleDatabase oracle = new OracleDatabase();

    private static final String TABLE_NAME = "USUARIO_G";

    private static final Map<String, String> TABLE_COLUMNS = Map.of(
        "ID", "ID_USUARIO",
        "NOME", "NOME",
        "EMAIL", "EMAIL",
        "SENHA", "SENHA"
    );

    @Override
    public void Create(Usuario entity) {
        try(var connection = oracle.getConnection()) {
            var preparedStatement = connection.prepareStatement("INSERT INTO " +
                    TABLE_NAME + " (" + TABLE_COLUMNS.get("NOME") + ", " +
                    TABLE_COLUMNS.get("EMAIL") + ", " +
                    TABLE_COLUMNS.get("SENHA") + ") " + "VALUES (?, ?, ?)");
            preparedStatement.setString(1, entity.getNome());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getSenha());
            var resultSet = preparedStatement.executeUpdate();
            logInfo("Usuário criado com sucesso: " + resultSet);

        }catch (SQLException e) {
            logError("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @Override
    public List<Usuario> ReadAll() {
        List<Usuario> usuarios = new ArrayList<>();
        try (var connection = oracle.getConnection()) {
            var preparedStatement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            var resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                usuarios.add(new Usuario(
                        resultSet.getInt(TABLE_COLUMNS.get("ID")),
                        resultSet.getString(TABLE_COLUMNS.get("NOME")),
                        resultSet.getString(TABLE_COLUMNS.get(("EMAIL"))),
                        resultSet.getString(TABLE_COLUMNS.get(("SENHA")))
                ));
            }
            logInfo("Sucesso ao recuperar usuários");
            return usuarios;
        }catch (SQLException e) {
            logError("Erro ao buscar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    @Override
    public Usuario Read(int id) {
        return null;
    }

    public Usuario ReadUserByName(String nomeUsuario) {
        Usuario usuario = new Usuario();
        try(var connection = oracle.getConnection()) {
            var preparedStatement = connection.prepareStatement("SELECT * FROM "
                    + TABLE_NAME + " WHERE " + TABLE_COLUMNS.get("NOME") + " = ?");
            preparedStatement.setString(1, nomeUsuario);
            var resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                usuario = new Usuario(
                        resultSet.getInt(TABLE_COLUMNS.get("ID")),
                        resultSet.getString(TABLE_COLUMNS.get("NOME")),
                        resultSet.getString(TABLE_COLUMNS.get("SENHA"))
                );
            }
            logInfo("Sucesso ao recuperar usuário com o nome: " + nomeUsuario);
        }catch (SQLException e){
            logError("Erro ao recuperar usuario com o nome: " + nomeUsuario);
        }
        return usuario;
    }

    @Override
    public void Update(Usuario entity) {

    }

    @Override
    public void Delete(int id) {

    }
}
