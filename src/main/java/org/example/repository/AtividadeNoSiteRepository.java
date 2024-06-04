package org.example.repository;

import org.example.config.OracleDatabase;
import org.example.config._Logger;
import org.example.entities.AtividadeNoSite;
import java.sql.SQLException;
import java.util.Map;

public class AtividadeNoSiteRepository implements _Logger<AtividadeNoSite> {
    private static final String TABLE_NAME = "ATIVIDADE_NO_SITE_G";
    private static final Map<String, String> TABLE_COLUMS = Map.of(
            "ID", "ID_ATIVIDADE_NO_SITE",
            "ARRECADACAO", "ARRECADACAO",
            "DATA", "DATA",
            "ID_DENUNCIA", "ID_DENUNCIA",
            "ID_DOACAO", "ID_DOACAO"
    );
    private static final OracleDatabase oracle = new OracleDatabase();

    private static final DenunciaRepository denunciaRepository = new DenunciaRepository();

    private static final DoacaoRepository doacaoRepository = new DoacaoRepository();

    // precisa pegar o id da doação e da denuncia pois no request não vem o id
    public void Create(AtividadeNoSite entity) {
        try(var connection = oracle.getConnection()){
            var preparedStatement = connection.prepareStatement("INSERT INTO "
                    + TABLE_NAME + "("
                    + TABLE_COLUMS.get("ARRECADACAO") + ", "
                    + TABLE_COLUMS.get("DATA") + ", "
                    + TABLE_COLUMS.get("ID_DENUNCIA") + ", "
                    + TABLE_COLUMS.get("ID_DOACAO") + " ) VALUES (?, ?, ?, ? )");
            preparedStatement.setString(1, entity.getArrecadacao());
            preparedStatement.setDate(2, java.sql.Date.valueOf(entity.getDataAtividade().toLocalDate()));
            if (entity.getArrecadacao().equals("N")) {
                preparedStatement.setInt(3, denunciaRepository.getLastDenuncia().getId());
                preparedStatement.setInt(4, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setNull(3, java.sql.Types.INTEGER);
                preparedStatement.setInt(4, doacaoRepository.getLastDoacao().getId());
            }
            var resultSet = preparedStatement.executeUpdate();
            logInfo("Atividade no site cadastrada com sucesso, linhas afetadas: " + resultSet);
        }catch (SQLException e) {
            logError("Erro ao cadastrar atividade no site: " + e.getMessage());
        }
    }
}
