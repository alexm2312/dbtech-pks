package de.htwberlin.dbtech.aufgaben.ue02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.htwberlin.dbtech.exceptions.DataException;

public class CoolingJdbc implements ICoolingJdbc {

    private static final Logger L = LoggerFactory.getLogger(CoolingJdbc.class);
    private Connection connection;

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @SuppressWarnings("unused")
    private Connection useConnection() {
        if (connection == null) {
            throw new DataException("Connection not set");
        }
        return connection;
    }

    @Override
    public List<String> getSampleKinds() {
        // wird an sql-Statement übergeben, um die Samplekinds zu holen. Nutzt den inhalt von string sql, um die Samplekinds zu holen. 
		PreparedStatement pStmt = null;
        // enthält später das ergebnis der sql abfrage, also die samplekinds
		ResultSet rs = null;
        // Liste welche alle Samplekinds enthält, sortiert nach text aufsteigend
		List<String> sampleKind = null;

		try {
			String sql = "Select text from Samplekind order by text asc";
			sampleKind = new LinkedList<String>();
            // Es wird auf dem conenction objekt die mehtode prepareStatement aufgerufen, um ein PreparedStatement zu erstellen, welches das sql-Statement enthält. Das PreparedStatement wird in der Variable pStmt gespeichert. Auf Objekt wird eine methode aufgerufen.
			pStmt = useConnection().prepareStatement(sql);
            // Führt die Abfrage aus und speichert das Ergebnis in der Variable rs. 
			rs = pStmt.executeQuery();
            // Solange es weitere Zeilen im Ergebnis gibt, wird die Schleife ausgeführt. In jeder Iteration wird der Wert der Spalte "text" aus der aktuellen Zeile des ResultSets abgerufen und zur Liste sampleKind hinzugefügt.
			while (rs.next()) {
				sampleKind.add(rs.getString("text"));
			}
		} catch (SQLException e) {
			throw new DataException(e);
		}
		return sampleKind;
	    }

        
    // Benny und Alex
    @Override
    public Sample findSampleById(Integer sampleId) {
        L.info("findSampleById: sampleId: " + sampleId);
        // TODO Auto-generated method stub
        return null;
    }

    // Benny und Alex
    @Override
    public void createSample(Integer sampleId, Integer sampleKindId) {
        L.info("createSample: sampleId: " + sampleId + ", sampleKindId: " + sampleKindId);
        // TODO Auto-generated method stub

    }

    // Alina
    @Override
    public void clearTray(Integer trayId) {
        L.info("clearTray: trayId: " + trayId);
        // TODO Auto-generated method stub

    }

}
