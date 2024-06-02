package org.example.repository;

import org.example.config.OracleDatabase;
import org.example.config._Logger;
import org.example.entities.Usuario;

import java.sql.SQLException;
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
            var sql = "INSERT INTO " +
                    TABLE_NAME + " (" + TABLE_COLUMNS.get("NOME") + ", " +
                    TABLE_COLUMNS.get("EMAIL") + ", " +
                    TABLE_COLUMNS.get("SENHA") + ") " + "VALUES (?, ?, ?)";

            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getNome());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getSenha());
            var result = preparedStatement.executeUpdate();
            logInfo("Usuário criado com sucesso: " + result);

        }catch (SQLException e) {
            logError("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @Override
    public List<Usuario> ReadAll() {
        return null;
    }

    @Override
    public Usuario Read(int id) {
        return null;
    }

    @Override
    public void Update(Usuario entity) {

    }

    @Override
    public void Delete(int id) {

    }
}
