package de.pitchMen.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.pitchMen.shared.bo.JobPosting;
//ProjectID FK als getter in JobPosting.java implementiert

/**
 * Die Klasse JobPostingMapper bildet JobPosting-Objekte auf einer relationale
 * Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu
 * erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden (Speichern, Suchen, Löschen, Bearbeiten).
 * 
 * @author Heike
 *
 */

public class JobPostingMapper {
	/**
	 * Die Klasse JobPostingMapper wird nur einmal instantiiert
	 * (Singelton-Eigenschaft). Die Variable ist mit static gekennzeichnet, da
	 * sie die einzige Instanz dieser Klasse speichert.
	 */
	private static JobPostingMapper jobPostingMapper = null;

	/**
	 * Ein geschützter Konstrukter verhindert eine neue Instanz dieser Klasse zu
	 * erzeugen.
	 */
	protected JobPostingMapper() {
	}

	/**
	 * Methode zum sicherstellen der Singleton-Eigenschaft. Es wird somit
	 * sichergestellt, dass nur eine einzige Instanz der JobPostingMapper
	 * existiert.
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
	 * Fügt ein JobPosting-Objekt der Datenbank hinzu. Und gibt das korrigierte
	 * JobPosting-Objekt zurück.
	 * 
	 * @param jobPosting
	 * @return jobPosting
	 */
	public JobPosting insert(JobPosting jobPosting) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/**
			 * Abfrage des als letztes hinzugefügten Primärschlüssels des
			 * Datensatzes. Der aktuelle Primärschlüssel wird um eins erhöht.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM jobPosting");

			if (rs.next()) {
				jobPosting.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();

				/**
				 * Ausführen der Einfügeoperation
				 */
				stmt.executeUpdate("INSERT INTO jobPosting (id, title, text, deadline project_id)" + "VALUES ( "
						+ jobPosting.getId() + ", '" + jobPosting.getTitle() + "' ,'" + jobPosting.getText()
						+ "' ,'" + jobPosting.getDeadline() + "' ," + jobPosting.getProjectId() + ")");
			}
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

			stmt.executeUpdate(
					"UPDATE jobPosting SET Title='" + jobPosting.getTitle() + "', " + "Text='" + jobPosting.getText()
							+ "', " + "deadline='" + jobPosting.getDeadline()+ "', " + "status='" 
							+ jobPosting.getStatus() + "WHERE id=" + jobPosting.getId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}

		return jobPosting;
	}

	/**
	 * Löscht ein JobPosting-Objekt aus der Datenbank.
	 * 
	 * @param jobPosting
	 */
	public void delete(JobPosting jobPosting) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM jobPosting " + "WHERE id=" + jobPosting.getId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Findet ein JobPosting-Objekt anhand der übergebenen Id in der Datenbank.
	 * 
	 * @param id
	 * @return jobPosting
	 * 
	 */
	public JobPosting findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id, title, text, deadline, projcet_id, "
							+ "status FROM jobPosting " + "WHERE id =" + id);

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben. Es
			 * wird geprüft ob ein Ergebnis vorliegt Das Ergebnis-Tupel wird in
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
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
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

		ArrayList<JobPosting> result = new ArrayList<JobPosting>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id, title, text, deadline, projcet_id, "
							+ "status FROM jobPosting ORDER BY status");

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben. Es
			 * wird geprüft ob ein Ergebnis vorliegt Das Ergebnis-Tupel wird in
			 * ein Objekt umgewandelt.
			 * 
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
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein JobPosting-Objekt anhand des übergebenen Textes in der
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
			ResultSet rs = stmt.executeQuery("SELECT id, title, text, deadline, projcet_id, status FROM jobPosting "
					+ "WHERE text LIKE '" + text + "' ORDER BY id");

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben. Das
			 * Ergebnis-Tupel wird in ein Objekt umgewandelt.
			 * 
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
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein JobPosting-Objekt anhand des übergebenen Titels in der
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
			ResultSet rs = stmt.executeQuery("SELECT id, title, text, deadline, projcet_id, "
					+ "status FROM jobPosting WHERE title LIKE '" + titel + "' ORDER BY id");

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben. Das
			 * Ergebnis-Tupel wird in ein Objekt umgewandelt.
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

				result.add(jobPosting);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein JobPosting-Objekt anhand des übergebenen Deadline in der
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
			ResultSet rs = stmt.executeQuery("SELECT id, title, text, deadline, projcet_id, status FROM jobPosting "
					+ "WHERE deadline LIKE '" + deadline + "' ORDER BY id");

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben. Das
			 * Ergebnis-Tupel wird in ein Objekt umgewandelt.
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

				result.add(jobPosting);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * Bei einer JOIN-Klausel werden Zeilen aus zwei Tabellen zusammengeführt.
	 * Bei dem INNER JOIN verbundenen Tabellen werden nur die Datensätze
	 * übernommen / angezeigt die in beiden Tabellen einen Treffer haben.
	 * Methode u.a. für Aufgabenstellung Nr. 6
	 * 
	 * @return ArryList<JobPosting>
	 */

	public ArrayList<JobPosting> findByPersonId(int personId) {
		Connection con = DBConnection.connection();

		ArrayList<JobPosting> result = new ArrayList<JobPosting>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM jobPosting " + 
							"INNER JOIN project "
							+ "ON jobPosting.project_id = project.id "
							+ "WHERE project.person_id =" + personId);

			/**
			 * Anhand der übergebenen PersonId werden die dazugehörigen
			 * JobPosting-Tupel (Ausschreibungen) aus der Datenbank abgefragt.
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
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * Die Methode findJobPostingByProjectId sucht alle jobPosting-Tupel
	 * zu der übergebenen projectId in der Datenbank ab und setzt diese in eine ArrayList.
	 * Die Methode ist zur Umsetzung der Anforderung, ein Projekt zu löschen, aber davor dazugehörige 
	 * Tabellen-Beziehungen ebenfalls zu löschen.
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
					+ "ON project.id = jobPosting.project_id WHERE jobPosting.project_id="+ projectId);

			/**
			 * Anhand der übergebenen projectId werden die dazugehörigen
			 * JobPosting-Tupel (Ausschreibungen) aus der Datenbank abgefragt.
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
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	
	


}
