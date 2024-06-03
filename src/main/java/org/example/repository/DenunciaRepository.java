package org.example.repository;

import org.example.config.OracleDatabase;
import org.example.config._Logger;
import org.example.entities.Denuncia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DenunciaRepository implements _BaseRepository<Denuncia>, _Logger<Denuncia> {

    private static final OracleDatabase oracle = new OracleDatabase();
    private static final UsuarioRepository usuarioRepository = new UsuarioRepository();
    private static final String TABLE_NAME = "DENUNCIA_G";
    private static final Map<String, String> TABLE_COLUMNS = Map.of(
            "ID", "ID_DENUNCIA",
            "TITULO", "TITULO",
            "DESCRICAO", "DESCRICAO",
            "LOCAL_DENUNCIA", "LOCAL_DENUNCIA",
            "ID_USUARIO", "ID_USUARIO"
    );

    @Override
    public void Create(Denuncia entity) {
        try(var connection = oracle.getConnection()){
            var preparedStatement = connection.prepareStatement("INSERT INTO "+ TABLE_NAME +" (" +
                    TABLE_COLUMNS.get("TITULO") + ", " +
                    TABLE_COLUMNS.get("DESCRICAO") + ", " +
                    TABLE_COLUMNS.get("LOCAL_DENUNCIA") + ", " +
                    TABLE_COLUMNS.get("ID_USUARIO") + ") VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, entity.getTituloDenuncia());
            preparedStatement.setString(2, entity.getDescricaoDenuncia());
            preparedStatement.setString(3, entity.getLocalDenuncia());
            preparedStatement.setInt(4,entity.getUsuario().getId());
            var resultSet = preparedStatement.executeUpdate();
            logInfo("Sucesso ao inserir denunia, linhas afetadas: " + resultSet);
        }catch (SQLException e) {
            logError("Erro ao cadastrar denuncia: " + e.getMessage());
        }
    }

    public boolean Create(Denuncia entity, String nomeUsuario) {
        try(var connection = oracle.getConnection()){
            var preparedStatement = connection.prepareStatement("INSERT INTO "+ TABLE_NAME +" (" +
                    TABLE_COLUMNS.get("TITULO") + ", " +
                    TABLE_COLUMNS.get("DESCRICAO") + ", " +
                    TABLE_COLUMNS.get("LOCAL_DENUNCIA") + ", " +
                    TABLE_COLUMNS.get("ID_USUARIO") + ") VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, entity.getTituloDenuncia());
            preparedStatement.setString(2, entity.getDescricaoDenuncia());
            preparedStatement.setString(3, entity.getLocalDenuncia());
            preparedStatement.setInt(4, usuarioRepository.ReadUserByName(nomeUsuario).getId());
            var resultSet = preparedStatement.executeUpdate();
            logInfo("Sucesso ao inserir denunia, linhas afetadas: " + resultSet);
            return true;
        }catch (SQLException e) {
            logError("Erro ao cadastrar denuncia: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Denuncia> ReadAll() {
        List<Denuncia> denuncias = new ArrayList<>();
        try(var connection = oracle.getConnection()) {
            var preparedStatement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            var resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                denuncias.add(
                        new Denuncia(
                                resultSet.getInt(TABLE_COLUMNS.get("ID")),
                                resultSet.getString(TABLE_COLUMNS.get("TITULO")),
                                resultSet.getString(TABLE_COLUMNS.get("DESCRICAO")),
                                resultSet.getString(TABLE_COLUMNS.get("LOCAL_DENUNCIA"))
                        )
                );
            }
        }catch (SQLException e) {
            logError("Erro ao recuperar denuncias: " + e.getMessage());
        }
        return denuncias;
    }

    @Override
    public Denuncia Read(int id) {
        Denuncia denuncia = new Denuncia();
        try (var connection = oracle.getConnection()) {
            var preparedStatement = connection.prepareStatement("SELECT * FROM " +
                    TABLE_NAME + " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                denuncia = new Denuncia(
                        resultSet.getInt(TABLE_COLUMNS.get("ID")),
                        resultSet.getString(TABLE_COLUMNS.get("TITULO")),
                        resultSet.getString(TABLE_COLUMNS.get("DESCRICAO")),
                        resultSet.getString(TABLE_COLUMNS.get("LOCAL_DENUNCIA"))
                );
            }
        }catch (SQLException e) {
            logError("Erro ao recuperar denuncia com o id: " + id);
        }
        return denuncia;
    }

    @Override
    public void Update(Denuncia entity) {

    }

    @Override
    public void Delete(int id) {
        try (var connection = oracle.getConnection()) {
            var preparedStatement = connection.prepareStatement("DELETE FROM "
                    + TABLE_NAME + " WHERE "
                    + TABLE_COLUMNS.get("ID") + " = ?");
            var resultSet = preparedStatement.executeUpdate();
            logInfo("Sucesso ao deletar denuncia com o id: " + id);
        }catch (SQLException e) {
            logError("Erro ao deletar denuncia com o id: " + id + ", erro: " + e.getMessage());
        }
    }

    public List<Denuncia> ReadDenunciaByUser(String nomeUsuario) {
        List<Denuncia> denuncias = new ArrayList<>();
        try(var connection = oracle.getConnection()) {
            var preparedStatement = connection.prepareStatement("SELECT A."
                    +TABLE_COLUMNS.get("ID") +", A."
                    + TABLE_COLUMNS.get("TITULO") +
                    ", A." + TABLE_COLUMNS.get("DESCRICAO") +
                    ", A." + TABLE_COLUMNS.get("LOCAL_DENUNCIA") +
                    " FROM " + TABLE_NAME + " A INNER JOIN USUARIO_G B ON A." +
                    TABLE_COLUMNS.get("ID_USUARIO") + " = B.ID_USUARIO " + " WHERE B.NOME = ?" );
            preparedStatement.setString(1, nomeUsuario);
            var resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                denuncias.add(
                        new Denuncia(
                            resultSet.getInt(TABLE_COLUMNS.get("ID")),
                            resultSet.getString(TABLE_COLUMNS.get("TITULO")),
                            resultSet.getString(TABLE_COLUMNS.get("DESCRICAO")),
                            resultSet.getString(TABLE_COLUMNS.get("LOCAL_DENUNCIA"))
                        )
                );
            }
            logInfo("Sucesso ao recuperar denuncias do usuário : " + nomeUsuario);
            return denuncias;
        }catch (SQLException e) {
            logError("Erro ao recuperar denuncias do usuário:" + nomeUsuario + ", erro :" + e.getMessage());
        }
        return denuncias;
    }

}
