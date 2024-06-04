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
                        resultSet.getString(TABLE_COLUMNS.get(("SENHA"))),
                        resultSet.getString(TABLE_COLUMNS.get(("EMAIL")))
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
        Usuario usuario = new Usuario();
        try(var connection = oracle.getConnection()) {
            var preparedStatement = connection.prepareStatement("SELECT * FROM "
                    + TABLE_NAME + " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                usuario = new Usuario(
                        resultSet.getInt(TABLE_COLUMNS.get("ID")),
                        resultSet.getString(TABLE_COLUMNS.get("NOME")),
                        resultSet.getString(TABLE_COLUMNS.get("SENHA")),
                        resultSet.getString(TABLE_COLUMNS.get("EMAIL"))
                );
            }
            logInfo("Sucesso ao recuperar usuário com o id: " + id);
        }catch (SQLException e) {
            logError("Erro ao recuperar usuário com o id: " + id);
        }
        return usuario;
    }

    public Usuario readUserByNameOrEmail(String nome, String email) {
        Usuario usuario = new Usuario();
        try(var connection = oracle.getConnection()) {
            var preparedStatement = connection.prepareStatement("SELECT * FROM "
                    + TABLE_NAME + " WHERE " + TABLE_COLUMNS.get("NOME") + " = ? OR " + TABLE_COLUMNS.get("EMAIL") + " = ?");
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, email);
            var resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                usuario = new Usuario(
                        resultSet.getInt(TABLE_COLUMNS.get("ID")),
                        resultSet.getString(TABLE_COLUMNS.get("NOME")),
                        resultSet.getString(TABLE_COLUMNS.get("SENHA"))
                );
            }
            logInfo("Sucesso ao recuperar usuário com o nome: " + nome);
        }catch (SQLException e){
            logError("Erro ao recuperar usuario com o nome: " + nome);
        }
        return usuario;
    }

    public Usuario ReadUserByName(String nomeUsuario) {
        Usuario usuario = new Usuario();
        try (var connection = oracle.getConnection()) {
            var preparedStatement = connection.prepareStatement(
                    "SELECT * FROM " + TABLE_NAME + " WHERE " + TABLE_COLUMNS.get("NOME") + " = ?"
            );
            preparedStatement.setString(1, nomeUsuario);

            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                usuario = new Usuario(
                        resultSet.getInt(TABLE_COLUMNS.get("ID")),
                        resultSet.getString(TABLE_COLUMNS.get("NOME")),
                        resultSet.getString(TABLE_COLUMNS.get("SENHA"))
                );
            }
            logInfo("Sucesso ao recuperar usuário com o nome: " + nomeUsuario);

        } catch (SQLException e) {
            logError("Erro ao recuperar usuario com o nome: " + nomeUsuario);
        }

        return usuario;
    }
    @Override
    public void Update(Usuario entity) {
        try(var connecton = oracle.getConnection()) {
            var preparedStatement = connecton.prepareStatement("UPDATE "
                    + TABLE_NAME + " SET " +
                    TABLE_COLUMNS.get("NOME") + " = ?, " +
                    TABLE_COLUMNS.get("EMAIL") + " = ?, " +
                    TABLE_COLUMNS.get("SENHA") + " = ? WHERE " +
                    TABLE_COLUMNS.get("ID") + " = ?");
            preparedStatement.setString(1, entity.getNome());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getSenha());
            preparedStatement.setInt(4, entity.getId());
            var resultSet = preparedStatement.executeUpdate();
            logInfo("Usuário atualizado com sucesso: " + resultSet);
        }catch (SQLException e) {
            logError("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    @Override
    public void Delete(int id) {
        try(var connection = oracle.getConnection()) {
            var preparedStatement = connection.prepareStatement("DELETE FROM "
                    + TABLE_NAME + " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeUpdate();
            logInfo("Usuário deletado com sucesso: " + resultSet);
        }catch (SQLException e) {
            logError("Erro ao deletar usuário: " + e.getMessage());
        }
    }
}
