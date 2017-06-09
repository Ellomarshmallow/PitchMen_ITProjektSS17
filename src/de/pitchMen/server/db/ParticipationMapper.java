package de.pitchMen.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.pitchMen.shared.bo.Participation;

/**
 * Die Klasse ParticipationMapper bildet Participation-Objekte auf eine 
 * relationale Datenbank ab. Ebenfalls ist es möglich aus 
 * Datenbank-Tupel Java-Objekte zu erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden (Insert, Search, delete, update).
 * 
 * @author Heike
 */
public class ParticipationMapper {

	/**
	 * Die Klasse ParticipationMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann,
	 * wird zunächst eine Variable mit dem Schlüsselwort static und dem
	 * Standardwert null erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	private static ParticipationMapper participationMapper = null;

	/**
	 * Ein geschützter Konstruktor verhindert das erneute erzeugen von weiteren
	 * Instanzen dieser Klasse.
	 */
	protected ParticipationMapper() {
	}

	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür,
	 * dass nur eine einzige Instanz der ParticipationMapper-Klasse existiert.
	 * Aufgerufen wird die Klasse somit über ParticipationMapper.participationMapper() 
	 * und nicht über den New-Operator.
	 * 
	 * @return ParticipationMapper
	 */
	public static ParticipationMapper participationMapper() {
		if (participationMapper == null) {
			participationMapper = new ParticipationMapper();
		}
		return participationMapper;
	}

	/**
	 * Fügt ein Participation-Objekt der Datenbank hinzu.
	 * 
	 * @param participation
	 * @param project
	 * @param person
	 * @return participation
	 */
	public Participation insert(Participation participation) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * Abfrage des zuletzt hinzugefügten Primärschlüssels (id). Die
			 * aktuelle id wird um eins erhöht.
			 */			
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM participation");
			participation.setId(rs.getInt("maxid") + 1);
			
			String insert1 = "INSERT INTO participation (id, workload, dateOpened, dateClosed)" + "VALUES ("
					+ participation.getId() + ", '" + participation.getDateOpened() + "', '"
					+ participation.getDateClosed() + "'";
			String insert2 = "INSERT INTO participation_has_project (participation_id, project_id)" + "VALUES ("
					+ participation.getId() + ", " + participation.getProjectId();
			String insert3 = "INSERT INTO person_has_participation (person_id, participation_id)" + "VALUES ("
					+ participation.getPersonId() + ", " + participation.getId();
			/**
			 * con.setAutoCommit(false) erlaubt es zwei oder mehrere Statements
			 * in einer Gruppe auszuführen und deaktiviert die auto-commit Funktion.
			 * 
			 */
			con.setAutoCommit(false);
			/**
			 * Fügt die oben gespeicherten SQL-Befehle der aktuellen Liste von
			 * SQL-Statements dem Statement-Objekt hinzu
			 */
			stmt.addBatch(insert1);
			stmt.addBatch(insert2);
			stmt.addBatch(insert3);
			/**
			 * Bestätigt alle gelisteten, in dem Statement-Objekt enthaltenen
			 * Statements, zur Ausführung in die Datenbank. Wenn alle Statements
			 * erfolgreich durchgeführt worden sind, gibt es eine ArrayListe mit der
			 * Anzahl der Updates zurück.
			 */
			stmt.executeBatch();

			/**
			 * Durch das Deaktivieren des AutoCommits dem Aufruf
			 * con.setAutoCommit(false), muss das Ausführen des Commits explizit
			 * gestartet werden.
			 */
			con.commit();
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return participation;
	}

	/**
	 * Aktualisiert ein Participation-Objekt in der Datenbank.
	 * 
	 * @param participation
	 * @return participation
	 */
	public Participation update(Participation participation) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Aktualisieren des Datensatzes Participation in der Datenbank.
			 */
			stmt.executeUpdate("UPDATE participation SET workload= " + participation.getWorkload() + ", dateOpened= '"
					+ participation.getDateOpened() + "', dateClosed= '" + participation.getDateClosed()
					+ "' WHERE id= " + participation.getId());
		}
		/**
		 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */		
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return participation;
	}

	/**
	 * Löscht ein Participation-Objekt aus der Datenbank.
	 * 
	 * @param participation
	 */
	public void delete(Participation participation) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Löschen des Datensatzes in der Datenbank.
			 */
			stmt.executeUpdate("DELETE FROM participation WHERE id=" + participation.getId());
		/**
		 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 * 
		 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Findet ein Participation-Objekt anhand der übergebenen Id in der
	 * Datenbank.
	 * 
	 * @param id
	 * @return person
	 */
	public Participation findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes in der Datenbank.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT id, workload, dateOpened, dateClosed FROM participation WHERE id=" + id);
			/**
			 * Zu einem Primärschlüssel exisitiert nur max ein Datenbank-Tupel,
			 * somit kann auch nur einer zurückgegeben werden. Es wird mit einer
			 * If-Abfragen geprüft, ob es für den angefragten Primärschlüssel
			 * ein DB-Tupel gibt.
			 */
			if (rs.next()) {
				Participation participation = new Participation();
				participation.setId(rs.getInt("id"));
				participation.setWorkload(rs.getFloat("workload"));
				participation.setDateOpened(rs.getDate("dateOpened"));
				participation.setDateClosed(rs.getDate("dateClosed"));
				return participation;
			}
		/**
		 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}

	/**
	 * Findet alle Participation-Objekte in der Datenbank.
	 * 
	 * @return ArrayList<Participation>
	 */
	public ArrayList<Participation> findAll() {
		Connection con = DBConnection.connection();
		/**
		 * Erzeugen einer ArrayList.
		 */
		ArrayList<Participation> result = new ArrayList<Participation>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden aller Datensatzes in der Datenbank.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT id, workload, dateOpened, dateClosed FROM participation ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle participation vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschließend der ArrayList hinzugefügt.
			 */
			while (rs.next()) {
				Participation participation = new Participation();
				participation.setId(rs.getInt("id"));
				participation.setWorkload(rs.getFloat("workload"));
				participation.setDateOpened(rs.getDate("dateOpened"));
				participation.setDateClosed(rs.getDate("dateClosed"));
				result.add(participation);
			}
		/**
		 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 * 
		 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet Participation-Objekte anhand des übergebenen Start-Datums in der Datenbank.
	 * 
	 * @param dateOpened
	 * @return ArrayList<Participation>
	 */
	public ArrayList<Participation> findByDateOpened(Date dateOpened) {
		Connection con = DBConnection.connection();
		/**
		 * Erzeugen einer ArrayList
		 */
		ArrayList<Participation> result = new ArrayList<Participation>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden der Datensätze, anahnd des Start-Datums, in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT id, workload, dateOpened, dateClosed FROM participation WHERE dateOpened= '"
							+ dateOpened + "' ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle participation vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschließend der ArrayList hinzugefügt.
			 */
			while (rs.next()) {
				Participation participation = new Participation();
				participation.setId(rs.getInt("id"));
				participation.setWorkload(rs.getFloat("workload"));
				participation.setDateOpened(rs.getDate("dateOpened"));
				participation.setDateClosed(rs.getDate("dateClosed"));
				result.add(participation);
			}
		/**
		 * Das aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 * 
		 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet Participation-Objekte anhand des übergebenen personId in der
	 * Datenbank. 
	 * Mit der Inner-Join-Klausel wird erreicht, dass nur die
	 * Datensätze zusammengefügt werden, zu den es jeweils auch ein Gegenstück
	 * in der verknüpften Tabelle gibt. Da es möglich ist, dass eine Person
	 * mehrere Participations (Beteiligungen) hat, müssen die
	 * Participation-Objekte in einer ArrayList gespeichert werden.
	 * 
	 * @param personId
	 * @return ArrayList<Participation>
	 */
	public ArrayList<Participation> findParticipationsByPersonId(int personId) {
		Connection con = DBConnection.connection();

		ArrayList<Participation> result = new ArrayList<Participation>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach der gesuchten personId, in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery("SELECT * FROM participation " 
					+ "INNER JOIN person_has_participation"
					+ "ON participation.id = person_has_participation.participation_id"
					+ "WHERE person_has_participation.person_id = " + personId);
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle participation mit dem übergebenen Wert (value) vorhanden ist,
			 * muss das Abfragen des ResultSet so oft erfolgen (while-Schleife),
			 * bis alle Tupel durchlaufen wurden. Die DB-Tupel werden in
			 * Java-Objekte transformiert und anschließend der ArrayList
			 * hinzugefügt.
			 */
			while (rs.next()) {
				Participation participation = new Participation();
				participation.setId(rs.getInt("id"));
				participation.setWorkload(rs.getFloat("workload"));
				participation.setDateOpened(rs.getDate("dateOpened"));
				participation.setDateClosed(rs.getDate("dateClosed"));
				result.add(participation);
			}
		/**
		 * Das aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 * 
		 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet Participation-Objekte anhand der übergebenen teamId in der
	 * Datenbank. 
	 * Mit der Inner-Join-Klausel wird erreicht, dass nur die
	 * Datensätze zusammengefügt werden, zu den es jeweils auch ein Gegenstück
	 * in der verknüpften Tabelle gibt. Da es möglich ist, dass ein Team 
	 * mehrere Participations (Beteiligungen) hat, müssen die Participation-Objekte in
	 * einer ArrayList gespeichert werden.
	 * 
	 * @param teamId
	 * @return ArrayList<Participation>
	 */
	public ArrayList<Participation> findParticipationsByTeamId(int teamId) {
		Connection con = DBConnection.connection();

		ArrayList<Participation> result = new ArrayList<Participation>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach der gesuchten teamId, in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery("SELECT * FROM participation" 
					+ " INNER JOIN participation_has_team "
					+ "ON participation.id = participation_has_team.participation_id "
					+ "WHERE participation_has_team.team_id = " + teamId);
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle participation vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschließend der ArrayList hinzugefügt.
			 */
			while (rs.next()) {
				Participation participation = new Participation();
				participation.setId(rs.getInt("id"));
				participation.setWorkload(rs.getFloat("workload"));
				participation.setDateOpened(rs.getDate("dateOpened"));
				participation.setDateClosed(rs.getDate("dateClosed"));
				result.add(participation);
			}
		/**
		 * Das aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 * 
		 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet Participation-Objekte anhand der übergebenen companyId in der
	 * Datenbank. Da es möglich ist, dass ein Unternehmen mehrere Participations
	 * (Beteiligungen) hat, müssen die Participation-Objekte in einer ArrayList
	 * gespeichert werden
	 * 
	 * @param companyId
	 * @return ArrayList<Participation>
	 */
	public ArrayList<Participation> findParticipationsByCompanyId(int companyId) {
		Connection con = DBConnection.connection();

		ArrayList<Participation> result = new ArrayList<Participation>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach der gesuchten companyId, in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery("SELECT * FROM participation " 
					+ "INNER JOIN company_has_participation "
					+ "ON participation.id = company_has_participation.participation_id "
					+ "WHERE company_has_participation.company_id = " + companyId);
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle participation vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschließend der ArrayList hinzugefügt.
			 */
			while (rs.next()) {
				Participation participation = new Participation();
				participation.setId(rs.getInt("id"));
				participation.setWorkload(rs.getFloat("workload"));
				participation.setDateOpened(rs.getDate("dateOpened"));
				participation.setDateClosed(rs.getDate("dateClosed"));
				result.add(participation);
			}
		/**
		 * Das aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 * 
		 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

}
