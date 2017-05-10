package de.pitchMen.server.db;

import java.sql.*;
import java.util.ArrayList;
import de.pitchMen.shared.bo.Project;

/**
 * Bildet Project-Objekte auf eine relationale Datenbank ab. Ebenfalls ist es
 * möglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 *
 * @author Lars
 */
public class ProjectMapper {

	/**
	 * Die Klasse ProjectMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann,
	 * wird zunächst eine Variable mit dem Schlüsselwort static und dem
	 * Standardwert null erzeugt. Sie speichert die Instanz dieser Klasse.
	 */

	private static ProjectMapper projectMapper = null;

	/**
	 * Ein geschützter Konstruktor verhindert das erneute erzeugen von weiteren
	 * Instanzen dieser Klasse.
	 */

	protected ProjectMapper() {

	}

	/**
	 * Methode zum sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür,
	 * dass nur eine einzige Instanz der ProjectMapper-Klasse existiert.
	 * Aufgerufen wird die Klasse somit über ProjectMapper.projectMapper() und
	 * nicht über den New-Operator.
	 * 
	 * @return projectMapper
	 */

	public static ProjectMapper projectMapper() {
		if (projectMapper == null) {
			projectMapper = new ProjectMapper();
		}
		return projectMapper;

	}

	/**
	 * Fügt ein Project-Objekt der Datenbank hinzu.
	 * 
	 * @param project
	 * @return project
	 */
	public Project insert(Project project) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * Abfrage des zuletzt hinzugefügten Primärschlüssels (id). Die
			 * aktuelle id wird um eins erhöht.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM project");

			project.setId(rs.getInt("maxid") + 1);
			stmt = con.createStatement();

			/**
			 * SQL-Anweisung zum Einfügen des neuen Project-Tupels in die
			 * Datenbank
			 */
			stmt.executeUpdate("INSERT INTO project (id, title, description, dateOpened, dateClosed)" + "VALUES ("
					+ project.getId() + ", '" + project.getTitle() + "', '" + project.getDescription() + "', '"
					+ project.getDateOpened() + "', '" + project.getDateClosed() + "')");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return project;
	}

	/**
	 * Aktualisiert ein Project-Objekt in der Datenbank.
	 * 
	 * @param project
	 * @throws ClassNotFoundException
	 * @return project
	 */
	public Project update(Project project) throws ClassNotFoundException {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE project SET title='" + project.getTitle() + "', description= '"
					+ project.getDescription() + "', dateOpened= '" + project.getDateOpened() + "', dateClosed= '"
					+ project.getDateClosed() + "' WHERE id= " + project.getId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return project;
	}

	/**
	 * Löscht ein Project-Objekt aus der Datenbank.
	 * 
	 * @param project
	 * @return
	 */
	public void delete(Project project) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM project WHERE id=" + project.getId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}

	}

	/**
	 * Findet ein Project-Objekt anhand der übergebenen ID in der Datenbank.
	 * 
	 * @param id
	 * @return
	 */
	public Project findById(int id) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id, title, description, dateOpened, dateClosed FROM project WHERE id=" + id);

			/**
			 * Zu einem Primärschlüssel exisitiert nur max ein Datenbank-Tupel,
			 * somit kann auch nur einer zurückgegeben werden. Es wird mit einer
			 * IF-Abfragen geprüft, ob es für den angefragten Primärschlüssel
			 * ein DB-Tupel gibt.
			 */

			if (rs.next()) {
				Project project = new Project();
				project.setId(rs.getInt("id"));
				project.setTitle(rs.getString("title"));
				project.setDescription(rs.getString("description"));
				project.setDateOpened(rs.getDate("dateOpened"));
				project.setDateClosed(rs.getDate("dateClosed"));
				return project;
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return null;
	}

	/**
	 * Findet alle Project-Objekte in der Datenbank.
	 * 
	 * @return
	 */
	public ArrayList<Project> findAll() throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		ArrayList<Project> result = new ArrayList<Project>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id, title, description, dateOpened, dateClosed FROM project ORDER BY id");

			while (rs.next()) {
				Project project = new Project();
				project.setId(rs.getInt("id"));
				project.setTitle(rs.getString("title"));
				project.setDescription(rs.getString("description"));
				project.setDateOpened(rs.getDate("dateOpened"));
				project.setDateClosed(rs.getDate("dateClosed"));

				result.add(project);

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet Project-Objekte anhand des übergebenen Start-Datums in der
	 * Datenbank.
	 * 
	 * @param dateOpened
	 * @return
	 */
	public ArrayList<Project> findByDateOpened(Date dateOpened) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		ArrayList<Project> result = new ArrayList<Project>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, title, description, dateOpened, dateClosed FROM project WHERE dateOpened= '"
							+ dateOpened + "' ORDER BY id");

			while (rs.next()) {
				Project project = new Project();
				project.setId(rs.getInt("id"));
				project.setTitle(rs.getString("title"));
				project.setDescription(rs.getString("description"));
				project.setDateOpened(rs.getDate("dateOpened"));
				project.setDateClosed(rs.getDate("dateClosed"));

				result.add(project);

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein Project-Objekt anhand des übergebenen End-Datums in der
	 * Datenbank.
	 * 
	 * @param dateClosed
	 * @return
	 */
	public ArrayList<Project> findByDateClosed(Date dateClosed) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		ArrayList<Project> result = new ArrayList<Project>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, title, description, dateOpened, dateClosed FROM project WHERE dateClosed= '"
							+ dateClosed + "' ORDER BY id");

			while (rs.next()) {
				Project project = new Project();
				project.setId(rs.getInt("id"));
				project.setTitle(rs.getString("title"));
				project.setDescription(rs.getString("description"));
				project.setDateOpened(rs.getDate("dateOpened"));
				project.setDateClosed(rs.getDate("dateClosed"));

				result.add(project);

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein Project-Objekt anhand des übergebenen Titels in der Datenbank.
	 * 
	 * @param title
	 * @return result
	 */
	public ArrayList<Project> findByTitle(String title) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		ArrayList<Project> result = new ArrayList<Project>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, title, description, dateOpened, dateClosed FROM project WHERE title= '"
							+ title + "' ORDER BY id");

			while (rs.next()) {
				Project project = new Project();
				project.setId(rs.getInt("id"));
				project.setTitle(rs.getString("title"));
				project.setDescription(rs.getString("description"));
				project.setDateOpened(rs.getDate("dateOpened"));
				project.setDateClosed(rs.getDate("dateClosed"));

				result.add(project);

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

}