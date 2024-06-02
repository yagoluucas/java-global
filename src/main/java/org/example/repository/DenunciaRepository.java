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

    }

    public List<Denuncia> ReadDenunciaByUser(int idUsuario) {
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
            logInfo("Sucesso ao recuperar denuncias do usuário com o id: " + idUsuario);
            return denuncias;
        }catch (SQLException e) {
            logError("Erro ao recuperar denuncias do usuário com o id :" + idUsuario + ", erro :" + e.getMessage());
        }
        return denuncias;
    }

}
