package org.example.repository;

import org.example.config.OracleDatabase;
import org.example.config._Logger;
import org.example.entities.Doacao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DoacaoRepository implements _Logger {
    private static final OracleDatabase oracle = new OracleDatabase();

    private static final String TABLE_NAME = "DOACAO_G";

    private static final Map<String, String> TABLE_COLUMS = Map.of(
            "ID", "ID_DOACAO",
            "VALOR", "VALOR",
            "ID_USUARIO", "ID_USUARIO"
    );

    public boolean Create(Doacao entity) {
        try(var connection = oracle.getConnection()) {
            var preparedStatement = connection.prepareStatement("INSERT INTO "
                    + TABLE_NAME + "("
                    + TABLE_COLUMS.get("VALOR") + ", " + TABLE_COLUMS.get("ID_USUARIO") + " ) VALUES (?, ? ") ;
            preparedStatement.setDouble(1, entity.getValor());
            preparedStatement.setInt(2, entity.getUsuario() == null ? null : entity.getUsuario().getId());
            var resultSet = preparedStatement.executeUpdate();
            logInfo("Sucesso ao cadastrar doação, linhas afetadas: " + resultSet);
            return true;
        }catch (SQLException e) {
            logError("tes: " + e.getMessage());
            return false;
        }
    }


    public List<Doacao> ReadAll() {
        List<Doacao> doacaos = new ArrayList<>();
        try (var connection = oracle.getConnection()) {
            var preparedStatement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            var resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                doacaos.add(new Doacao(
                        resultSet.getInt(TABLE_COLUMS.get("ID")),
                        resultSet.getDouble(TABLE_COLUMS.get("VALOR"))
                ));
            }
            logInfo("Sucesso ao recuperar doações");
            return doacaos;
        }catch (SQLException e) {
            logError("Erro ao buscar doações: " + e.getMessage());
        }
        return doacaos;
    }
}
