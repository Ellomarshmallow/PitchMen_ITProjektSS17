package de.pitchMen.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.pitchMen.shared.bo.JobPosting;
//ProjectID FK als getter in JobPosting.java implementiert



/**
 * Die Klasse JobPostingMapper bildet JobPosting-Objekte auf einer relationale Datenbank ab. 
 * Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende 
 * Methoden (Speichern, Suchen, Löschen, Bearbeiten).
 * 
 * @author Heike
 *
 */

public class JobPostingMapper {
	/**
	 * Die Klasse JobPostingMapper wird nur einmal instantiiert (Singelton-Eigenschaft). Die Variable ist
	 * mit static gekennzeichnet, da sie die einzige Instanz dieser Klasse speichert.
	 */
	private static JobPostingMapper jobPostingMapper = null;

	/**
	 * Ein geschützter Konstrukter verhindert eine neue Instanz dieser Klasse zu erzeugen.
	 */
	protected JobPostingMapper() {
	}

	/**
	 * Methode zum sicherstellen der Singleton-Eigenschaft. Es wird somit sichergestellt, 
	 * dass nur eine einzige Instanz der JobPostingMapper existiert.
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
	 * Fügt ein JobPosting-Objekt der Datenbank hinzu.
	 * Und gibt das korrigierte JobPosting-Objekt zurück. 
	 * 
	 * @param jobPosting 
	 * @return jobPosting
	 * @throws ClassNotFoundException
	 */
	public JobPosting insert(JobPosting jobPosting) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/** Abfrage des als letztes hinzugefügten Primärschlüssels des Datensatzes.
			 * Der aktuelle Primärschlüssel wird um eins erhöht.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM jobPosting");

			if (rs.next()) {
				jobPosting.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();

				/**
				 * Ausführen der Einfügeoperation
				 */
				stmt.executeUpdate("INSERT INTO jobPosting (id, title, text, deadline project_id)"
						+ "VALUES ( " + jobPosting.getId() + ", '" + jobPosting.getTitle() + "' ,'" 
						+ jobPosting.getText() + jobPosting.getDeadline() + jobPosting.getProjectId() + "')");
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();    
		}
		return jobPosting;
	}

	/**
	 * Aktuallisiert ein JobPosting-Objekt in der Datenbank.
	 * 
	 * @param jobPosting
	 * @return jobPosting
	 * @throws ClassNotFoundException
	 */
	public JobPosting update(JobPosting jobPosting) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE jobPosting SET Title='" + jobPosting.getTitle() + "', "
					+ "Text='" + jobPosting.getText() + "', " + "deadline='" + jobPosting.getDeadline()  
					+ "WHERE id=" + jobPosting.getId());
		}

		catch (SQLException e2){
			e2.printStackTrace();
		}

		return jobPosting;
	}

	/**
	 * Löscht ein JobPosting-Objekt aus der Datenbank.
	 * 
	 * @param jobPosting 
	 * @throws ClassNotFoundException 
	 */
	public void delete(JobPosting jobPosting) throws ClassNotFoundException {
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
	 * @throws ClassNotFoundException 
	 * @return jobPosting
	 * 
	 */
	public JobPosting findById(int id) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, title, text, deadline FROM jobPosting " 
					+ "WHERE id =" + id);

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben.
			 * Es wird geprüft ob ein Ergebnis vorliegt
			 * Das Ergebnis-Tupel wird in ein Objekt umgewandelt.
			 * 
			 */		        	
			if (rs.next()) {
				JobPosting jobPosting = new JobPosting();
				jobPosting.setId(rs.getInt("id"));
				jobPosting.setTitle(rs.getString("title"));
				jobPosting.setText(rs.getString("text"));
				jobPosting.setDeadline(rs.getDate("deadline"));
				jobPosting.setProjectID(rs.getInt("project_id"));
				

				return jobPosting;
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Findet alle JobPosting-Objekte in der Datenbank.
	 * 
	 * @return ArrayList<JobPosting>
	 * @throws ClassNotFoundException
	 */
	public ArrayList<JobPosting> findAll() throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		ArrayList<JobPosting> result = new ArrayList<JobPosting>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, title, text, deadline FROM jobPosting " 
					+ "ORDER BY id");

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben.
			 * Es wird geprüft ob ein Ergebnis vorliegt
			 * Das Ergebnis-Tupel wird in ein Objekt umgewandelt.
			 * 
			 */		        	
			while (rs.next()) {
				JobPosting jobPosting = new JobPosting();
				jobPosting.setId(rs.getInt("id"));
				jobPosting.setTitle(rs.getString("title"));
				jobPosting.setText(rs.getString("text"));
				jobPosting.setDeadline(rs.getDate("deadline"));
				jobPosting.setProjectID(rs.getInt("project_id"));

				result.add(jobPosting);
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}


	/**
	 * Findet ein JobPosting-Objekt anhand des übergebenen Textes in der Datenbank.
	 * 
	 * @throws ClassNotFoundException
	 * @param text 
	 * @return ArrayList<JobPosting>
	 */
	public ArrayList<JobPosting> findByText(String text) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		ArrayList<JobPosting> result = new ArrayList<JobPosting>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, title, text, deadline FROM jobPosting " 
					+ "WHERE text LIKE" + text + "ORDER BY id");

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben.
			 * Das Ergebnis-Tupel wird in ein Objekt umgewandelt.
			 * 
			 */		        	
			while (rs.next()) {
				JobPosting jobPosting = new JobPosting();
				jobPosting.setId(rs.getInt("id"));
				jobPosting.setTitle(rs.getString("title"));
				jobPosting.setText(rs.getString("text"));
				jobPosting.setDeadline(rs.getDate("deadline"));
				jobPosting.setProjectID(rs.getInt("project_id"));
				
				result.add(jobPosting);
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein JobPosting-Objekt anhand des übergebenen Titels in der Datenbank.
	 * 
	 * @throws ClassNotFoundException
	 * @param titel 
	 * @return ArrayList<JobPosting>
	 */
	public ArrayList<JobPosting> findByTitel(String titel) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		ArrayList<JobPosting> result = new ArrayList<JobPosting>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, title, text, deadline FROM jobPosting " 
					+ "WHERE title LIKE" + titel + "ORDER BY id");

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben.
			 * Das Ergebnis-Tupel wird in ein Objekt umgewandelt.
			 * 
			 */		        	
			if (rs.next()) {
				JobPosting jobPosting = new JobPosting();
				jobPosting.setId(rs.getInt("id"));
				jobPosting.setTitle(rs.getString("title"));
				jobPosting.setText(rs.getString("text"));
				jobPosting.setDeadline(rs.getDate("deadline"));
				jobPosting.setProjectID(rs.getInt("project_id"));
				
				result.add(jobPosting);
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}


	/**
	 * Findet ein JobPosting-Objekt anhand des übergebenen Deadline in der Datenbank.
	 * 
	 * @throws ClassNotFoundException
	 * @param deadline 
	 * @return ArrayList<JobPosting>
	 */
	public ArrayList<JobPosting> findByDeadline(String deadline) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		ArrayList<JobPosting> result = new ArrayList<JobPosting>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, title, text, deadline FROM jobPosting " 
					+ "WHERE deadline LIKE" + deadline + "ORDER BY id");

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben.
			 * Das Ergebnis-Tupel wird in ein Objekt umgewandelt.
			 * 
			 */		        	
			if (rs.next()) {
				JobPosting jobPosting = new JobPosting();
				jobPosting.setId(rs.getInt("id"));
				jobPosting.setTitle(rs.getString("title"));
				jobPosting.setText(rs.getString("text"));
				jobPosting.setDeadline(rs.getDate("deadline"));
				jobPosting.setProjectID(rs.getInt("project_id"));
				
				result.add(jobPosting);
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

}
