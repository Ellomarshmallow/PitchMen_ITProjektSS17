package de.pitchMen.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.pitchMen.shared.bo.JobPosting;

/**
 * Die Klasse JobPostingMapper bildet JobPosting-Objekte auf einer relationale
 * Datenbank ab. Ebenfalls ist es moeglich aus Datenbank-Tupel Java-Objekte zu
 * erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden (insert, search, delete, update).
 * 
 * @author Heike
 */

public class JobPostingMapper {
	/**
	 * Die Klasse JobPostingMapper wird nur einmal instantiiert
	 * (Singelton-Eigenschaft).Damit diese Eigenschaft gegeben ist, wird eine
	 * Variable mit dem Schluesselwort static und dem Standardwert null erzeugt.
	 * Sie speichert die Instanz dieser Klasse.
	 */
	private static JobPostingMapper jobPostingMapper = null;

	/**
	 * Ein geschuetzter Konstrukter verhindert eine neue Instanz dieser Klasse zu
	 * erzeugen.
	 */
	protected JobPostingMapper() {
	}

	/**
	 * Methode zum sicherstellen der Singleton-Eigenschaft. Es wird somit
	 * sichergestellt, dass nur eine einzige Instanz der JobPostingMapper existiert.
	 * Aufgerufen wird die Klasse somit ueber JobPostingMapper.jobPostingMapper() und nicht
	 * ueber den New-Operator.
	 * 
	 * @return jobPostingMapper
	 */
	public static JobPostingMapper jobPostingMapper() {
		if (jobPostingMapper == null) {
			jobPostingMapper = new JobPostingMapper();
		}
		return jobPostingMapper;
	}

	/**
	 * Fuegt ein JobPosting-Objekt der Datenbank hinzu. 
	 * 
	 * @param jobPosting
	 * @return jobPosting
	 */
	public JobPosting insert(JobPosting jobPosting) {
		/**
		 *  DB-Verbindung holen.
		 */
		Connection con = DBConnection.connection();

		try {
			/**
			 * leeres SQL-Statement (JDBC) anlegen.
			 */
			Statement stmt = con.createStatement();
			/**
			 * Abfrage des zuletzt hinzugefuegten Primaerschluessels (id). Die aktuelle id wird um eins erhoeht.
			 * Statement ausfuellen und als Query an die Datenbank senden.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM jobPosting");

			if (rs.next()) {
				jobPosting.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();
				/**
				 * SQL-Anweisung zum Einfuegen des neuen Datensatzes in die Datenbank.
				 */
				stmt.executeUpdate("INSERT INTO jobPosting (id, title, text, deadline, project_id)" + " VALUES ( "
						+ jobPosting.getId() + ", '" + jobPosting.getTitle() + "' ,'" + jobPosting.getText()
						+ "' ,'" + jobPosting.getDeadline() + "' ," + jobPosting.getProjectId() + ")");
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */		
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return jobPosting;
	}

	/**
	 * Aktuallisiert ein JobPosting-Objekt in der Datenbank.
	 * 
	 * @param jobPosting
	 * @return jobPosting
	 */
	public JobPosting update(JobPosting jobPosting) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zur Aktualisierung des uebergebenen Datensatzes in der Datenbank.
			 */
			stmt.executeUpdate(
					"UPDATE jobPosting SET Title='" + jobPosting.getTitle() + "', " + " Text='" + jobPosting.getText()
							+ "', " + "deadline='" + jobPosting.getDeadline()+ "', " + " status= '" 
							+ jobPosting.getStatus() + "' WHERE id=" + jobPosting.getId());
		}
		/**
		 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return jobPosting;
	}

	/**
	 * Loescht ein JobPosting-Objekt aus der Datenbank.
	 * 
	 * @param jobPosting
	 */
	public void delete(JobPosting jobPosting) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Loeschen des uebergebenen Datensatzes in der Datenbank.
			 */
			stmt.executeUpdate("DELETE FROM jobPosting" + " WHERE id=" + jobPosting.getId());
		}
		/**
		 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */
		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Findet ein JobPosting-Objekt anhand der uebergebenen Id in der Datenbank.
	 * 
	 * @param id
	 * @return jobPosting 
	 */
	public JobPosting findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, anhand der uebergebenen Id, in der Datenbank.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT id, title, text, deadline, project_id, "
							+ "status FROM jobPosting" + " WHERE id =" + id);
			/**
			 * Der Primaerschluessel (id) wird als eine Tupel zurueckgegeben. Es
			 * wird geprueft ob ein Ergebnis vorliegt Das Ergebnis-Tupel wird in
			 * ein Objekt umgewandelt.
			 * 
			 */
			if (rs.next()) {
				JobPosting jobPosting = new JobPosting();
				jobPosting.setId(rs.getInt("id"));
				jobPosting.setTitle(rs.getString("title"));
				jobPosting.setText(rs.getString("text"));
				jobPosting.setDeadline(rs.getDate("deadline"));
				jobPosting.setProjectId(rs.getInt("project_id"));
				jobPosting.setStatus(rs.getString("status"));
				return jobPosting;
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}

	/**
	 * Findet alle JobPosting-Objekte in der Datenbank.
	 * 
	 * @return ArrayList<JobPosting>
	 */
	public ArrayList<JobPosting> findAll() {
		Connection con = DBConnection.connection();
		/**
		 * Erzeugen einer ArrayList.
		 */
		ArrayList<JobPosting> result = new ArrayList<JobPosting>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden aller Datensatzes in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt.executeQuery("SELECT * FROM jobPosting ORDER BY status, title");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle jobPosting vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschliessend der ArrayList hinzugefuegt.
			 */
			while (rs.next()) {
				JobPosting jobPosting = new JobPosting();
				jobPosting.setId(rs.getInt("id"));
				jobPosting.setTitle(rs.getString("title"));
				jobPosting.setText(rs.getString("text"));
				jobPosting.setDeadline(rs.getDate("deadline"));
				jobPosting.setProjectId(rs.getInt("project_id"));
				jobPosting.setStatus(rs.getString("status"));
				result.add(jobPosting);
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein JobPosting-Objekt anhand des uebergebenen Textes in der
	 * Datenbank.
	 * 
	 * @param text
	 * @return ArrayList<JobPosting>
	 */
	public ArrayList<JobPosting> findByText(String text) {
		Connection con = DBConnection.connection();

		ArrayList<JobPosting> result = new ArrayList<JobPosting>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach dem gesuchten Test, in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt.executeQuery("SELECT id, title, text, deadline, project_id, status FROM jobPosting"
					+ " WHERE text LIKE '" + text + "' ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle jobPosting vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschliessend der ArrayList hinzugefuegt.
			 */
			while (rs.next()) {
				JobPosting jobPosting = new JobPosting();
				jobPosting.setId(rs.getInt("id"));
				jobPosting.setTitle(rs.getString("title"));
				jobPosting.setText(rs.getString("text"));
				jobPosting.setDeadline(rs.getDate("deadline"));
				jobPosting.setProjectId(rs.getInt("project_id"));
				jobPosting.setStatus(rs.getString("status"));
				result.add(jobPosting);
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
			} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein JobPosting-Objekt anhand des uebergebenen Titels in der
	 * Datenbank.
	 * 
	 * @param titel
	 * @return ArrayList<JobPosting>
	 */
	public ArrayList<JobPosting> findByTitel(String titel) {
		Connection con = DBConnection.connection();

		ArrayList<JobPosting> result = new ArrayList<JobPosting>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach dem gesuchten Titel, in der Datenbank, sortiert nach der Id.
			 */			
			ResultSet rs = stmt.executeQuery("SELECT id, title, text, deadline, project_id, "
					+ "status FROM jobPosting WHERE title LIKE '" + titel + "' ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle jobPosting vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschliessend der ArrayList hinzugefuegt.
			 */
			while (rs.next()) {
				JobPosting jobPosting = new JobPosting();
				jobPosting.setId(rs.getInt("id"));
				jobPosting.setTitle(rs.getString("title"));
				jobPosting.setText(rs.getString("text"));
				jobPosting.setDeadline(rs.getDate("deadline"));
				jobPosting.setProjectId(rs.getInt("project_id"));
				jobPosting.setStatus(rs.getString("status"));
				result.add(jobPosting);
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein JobPosting-Objekt anhand des uebergebenen Deadline in der
	 * Datenbank.
	 * 
	 * @param deadline
	 * @return ArrayList<JobPosting>
	 */
	public ArrayList<JobPosting> findByDeadline(String deadline) {
		Connection con = DBConnection.connection();

		ArrayList<JobPosting> result = new ArrayList<JobPosting>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach der gesuchten Deadline, in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt.executeQuery("SELECT id, title, text, deadline, project_id, status FROM jobPosting "
					+ "WHERE deadline LIKE '" + deadline + "' ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle jobPosting vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschliessend der ArrayList hinzugefuegt.
			 */
			while (rs.next()) {
				JobPosting jobPosting = new JobPosting();
				jobPosting.setId(rs.getInt("id"));
				jobPosting.setTitle(rs.getString("title"));
				jobPosting.setText(rs.getString("text"));
				jobPosting.setDeadline(rs.getDate("deadline"));
				jobPosting.setProjectId(rs.getInt("project_id"));
				jobPosting.setStatus(rs.getString("status"));
				result.add(jobPosting);
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet die Ausschreibungen passend zur Person-Id, innerhalb der jobPosting-Tabelle. 
	 * uebergibt ein Ausschreibungs-Objekt zurueck.
	 * 
	 * Mit der Inner-Join-Klausel wird erreicht, dass nur die Datensaetze zusammengefuegt
	 * werden, zu den es jeweils auch ein Gegenstueck in der verknuepften 
	 * Tabelle gibt. Da es moeglich ist, dass eine Person mehrere Ausschreibungen hat,
	 * muessen die Ausschreibungs-Objekte in einer ArrayList gespeichert werden.
	 * 
	 * @param personId
	 * @return ArryList<JobPosting>
	 */

	public ArrayList<JobPosting> findByPersonId(int personId) {
		Connection con = DBConnection.connection();

		ArrayList<JobPosting> result = new ArrayList<JobPosting>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach der gesuchten PersonenId, in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM jobPosting " + 
							"INNER JOIN project "
							+ "ON jobPosting.project_id = project.id "
							+ "WHERE project.person_id =" + personId);
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle jobPosting vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschliessend der ArrayList hinzugefuegt.
			 */
			while (rs.next()) {
				JobPosting jobPosting = new JobPosting();
				jobPosting.setId(rs.getInt("id"));
				jobPosting.setTitle(rs.getString("title"));
				jobPosting.setText(rs.getString("text"));
				jobPosting.setDeadline(rs.getDate("deadline"));
				jobPosting.setProjectId(rs.getInt("project_id"));
				jobPosting.setStatus(rs.getString("status"));
				result.add(jobPosting);
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * Die Methode findJobPostingByProjectId sucht alle jobPosting-Tupel
	 * zu der uebergebenen projectId in der Datenbank ab und setzt diese in eine ArrayList.
	 * Die Methode ist zur Umsetzung der Anforderung, ein Projekt zu loeschen, aber davor dazugehoerige 
	 * Tabellen-Beziehungen ebenfalls zu loeschen.
	 * 
	 * Mit der Inner-Join-Klausel wird erreicht, dass nur die Datensaetze zusammengefuegt
	 * werden, zu den es jeweils auch ein Gegenstueck in der verknuepften 
	 * Tabelle gibt. Da es moeglich ist, dass ein Projekt mehrere Ausschreibungen hat,
	 * muessen die Ausschreibungs-Objekte in einer ArrayList gespeichert werden.
	 * 
	 * 
	 * @param projectId
	 * @return ArrayList<JobPosting>
	 */
	public ArrayList<JobPosting> findJobPostingsByProjectId(int projectId) {
		Connection con = DBConnection.connection();

		ArrayList<JobPosting> result = new ArrayList<JobPosting>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM jobPosting "
					+ "INNER JOIN project"
					+ " ON project.id = jobPosting.project_id WHERE jobPosting.project_id = "+ projectId);
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle jobPosting vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschliessend der ArrayList hinzugefuegt.
			 */
			while (rs.next()) {
				JobPosting jobPosting = new JobPosting();
				jobPosting.setId(rs.getInt("id"));
				jobPosting.setTitle(rs.getString("title"));
				jobPosting.setText(rs.getString("text"));
				jobPosting.setDeadline(rs.getDate("deadline"));
				jobPosting.setProjectId(rs.getInt("project_id"));
				jobPosting.setStatus(rs.getString("status"));
				result.add(jobPosting);
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * Findet ein JobPosting-Objekt anhand der uebergebenen Id in der Datenbank.
	 * 
	 * @param partnerProfilId
	 * @return jobPosting 
	 */
	public JobPosting findJobPostingByPPId(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, anhand der uebergebenen Id, in der Datenbank.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM jobPosting INNER JOIN partnerProfile ON "
							+ "jobPosting.id = partnerProfile.jobPosting_id WHERE partnerProfile.id = " +  id);
			/**
			 * Der Primaerschluessel (id) wird als eine Tupel zurueckgegeben. Es
			 * wird geprueft ob ein Ergebnis vorliegt Das Ergebnis-Tupel wird in
			 * ein Objekt umgewandelt.
			 * 
			 */
			if (rs.next()) {
				JobPosting jobPosting = new JobPosting();
				jobPosting.setId(rs.getInt("id"));
				jobPosting.setTitle(rs.getString("title"));
				jobPosting.setText(rs.getString("text"));
				jobPosting.setDeadline(rs.getDate("deadline"));
				jobPosting.setProjectId(rs.getInt("project_id"));
				jobPosting.setStatus(rs.getString("status"));
				return jobPosting;
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}

	
}
