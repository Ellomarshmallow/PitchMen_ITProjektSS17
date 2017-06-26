package de.pitchMen.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.pitchMen.shared.bo.JobPosting;

/**
 * Die Klasse JobPostingMapper bildet JobPosting-Objekte auf einer relationale
 * Datenbank ab. Ebenfalls ist es m�glich aus Datenbank-Tupel Java-Objekte zu
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
	 * Variable mit dem Schl�sselwort static und dem Standardwert null erzeugt.
	 * Sie speichert die Instanz dieser Klasse.
	 */
	private static JobPostingMapper jobPostingMapper = null;

	/**
	 * Ein gesch�tzter Konstrukter verhindert eine neue Instanz dieser Klasse zu
	 * erzeugen.
	 */
	protected JobPostingMapper() {
	}

	/**
	 * Methode zum sicherstellen der Singleton-Eigenschaft. Es wird somit
	 * sichergestellt, dass nur eine einzige Instanz der JobPostingMapper existiert.
	 * Aufgerufen wird die Klasse somit �ber JobPostingMapper.jobPostingMapper() und nicht
	 * �ber den New-Operator.
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
	 * F�gt ein JobPosting-Objekt der Datenbank hinzu. 
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
			 * Abfrage des zuletzt hinzugef�gten Prim�rschl�ssels (id). Die aktuelle id wird um eins erh�ht.
			 * Statement ausf�llen und als Query an die Datenbank senden.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM jobPosting");

			if (rs.next()) {
				jobPosting.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();
				/**
				 * SQL-Anweisung zum Einf�gen des neuen Datensatzes in die Datenbank.
				 */
				stmt.executeUpdate("INSERT INTO jobPosting (id, title, text, deadline, project_id)" + " VALUES ( "
						+ jobPosting.getId() + ", '" + jobPosting.getTitle() + "' ,'" + jobPosting.getText()
						+ "' ,'" + jobPosting.getDeadline() + "' ," + jobPosting.getProjectId() + ")");
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die M�glichkeit, die
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
			 * SQL-Anweisung zur Aktualisierung des �bergebenen Datensatzes in der Datenbank.
			 */
			stmt.executeUpdate(
					"UPDATE jobPosting SET Title='" + jobPosting.getTitle() + "', " + " Text='" + jobPosting.getText()
							+ "', " + "deadline='" + jobPosting.getDeadline()+ "', " + " status= '" 
							+ jobPosting.getStatus() + " WHERE id=" + jobPosting.getId());
		}
		/**
		 * Das Aufrufen des printStackTrace bietet die M�glichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return jobPosting;
	}

	/**
	 * L�scht ein JobPosting-Objekt aus der Datenbank.
	 * 
	 * @param jobPosting
	 */
	public void delete(JobPosting jobPosting) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum L�schen des �bergebenen Datensatzes in der Datenbank.
			 */
			stmt.executeUpdate("DELETE FROM jobPosting" + " WHERE id=" + jobPosting.getId());
		}
		/**
		 * Das Aufrufen des printStackTrace bietet die M�glichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */
		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Findet ein JobPosting-Objekt anhand der �bergebenen Id in der Datenbank.
	 * 
	 * @param id
	 * @return jobPosting 
	 */
	public JobPosting findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, anhand der �bergebenen Id, in der Datenbank.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT id, title, text, deadline, project_id, "
							+ "status FROM jobPosting" + " WHERE id =" + id);
			/**
			 * Der Prim�rschl�ssel (id) wird als eine Tupel zur�ckgegeben. Es
			 * wird gepr�ft ob ein Ergebnis vorliegt Das Ergebnis-Tupel wird in
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
			 * Das Aufrufen des printStackTrace bietet die M�glichkeit, die
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM jobPosting ORDER BY status");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle jobPosting vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschlie�end der ArrayList hinzugef�gt.
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
			 * Das Aufrufen des printStackTrace bietet die M�glichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein JobPosting-Objekt anhand des �bergebenen Textes in der
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
			 * anschlie�end der ArrayList hinzugef�gt.
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
			 * Das Aufrufen des printStackTrace bietet die M�glichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
			} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein JobPosting-Objekt anhand des �bergebenen Titels in der
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
			 * anschlie�end der ArrayList hinzugef�gt.
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
			 * Das Aufrufen des printStackTrace bietet die M�glichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein JobPosting-Objekt anhand des �bergebenen Deadline in der
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
			 * anschlie�end der ArrayList hinzugef�gt.
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
			 * Das Aufrufen des printStackTrace bietet die M�glichkeit, die
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
	 * �bergibt ein Ausschreibungs-Objekt zur�ck.
	 * 
	 * Mit der Inner-Join-Klausel wird erreicht, dass nur die Datens�tze zusammengef�gt
	 * werden, zu den es jeweils auch ein Gegenst�ck in der verkn�pften 
	 * Tabelle gibt. Da es m�glich ist, dass eine Person mehrere Ausschreibungen hat,
	 * m�ssen die Ausschreibungs-Objekte in einer ArrayList gespeichert werden.
	 * 
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
			 * anschlie�end der ArrayList hinzugef�gt.
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
			 * Das Aufrufen des printStackTrace bietet die M�glichkeit, die
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
	 * zu der �bergebenen projectId in der Datenbank ab und setzt diese in eine ArrayList.
	 * Die Methode ist zur Umsetzung der Anforderung, ein Projekt zu l�schen, aber davor dazugeh�rige 
	 * Tabellen-Beziehungen ebenfalls zu l�schen.
	 * 
	 * Mit der Inner-Join-Klausel wird erreicht, dass nur die Datens�tze zusammengef�gt
	 * werden, zu den es jeweils auch ein Gegenst�ck in der verkn�pften 
	 * Tabelle gibt. Da es m�glich ist, dass ein Projekt mehrere Ausschreibungen hat,
	 * m�ssen die Ausschreibungs-Objekte in einer ArrayList gespeichert werden.
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
			 * anschlie�end der ArrayList hinzugef�gt.
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
			 * Das Aufrufen des printStackTrace bietet die M�glichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
}
