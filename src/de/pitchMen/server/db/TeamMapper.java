package de.pitchMen.server.db;

/**
 * Bildet Team-Objekte auf eine relationale Datenbank ab. Ebenfalls ist es m�glich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 * 
 * @author Lars
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.pitchMen.shared.bo.Team;

public class TeamMapper {

	/**
	 * Die Klasse TeamMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erf�llt werden kann,
	 * wird zun�chst eine Variable mit dem Schl�sselwort static und dem
	 * Standardwert null erzeugt. Sie speichert die Instanz dieser Klasse.
	 */

	private static TeamMapper teamMapper = null;

	/**
	 * Ein gesch�tzter Konstruktor verhindert das erneute erzeugen von weiteren
	 * Instanzen dieser Klasse.
	 */

	protected TeamMapper() {

	}

	/**
	 * Methode zum sicherstellen der Singleton-Eigenschaft. Diese sorgt daf�r,
	 * dass nur eine einzige Instanz der TeamMapper-Klasse existiert. Aufgerufen
	 * wird die Klasse somit �ber TeamMapper.teamMapper() und nicht �ber den
	 * New-Operator.
	 * 
	 * @return teamMapper
	 */

	public static TeamMapper teamMapper() {
		if (teamMapper == null) {
			teamMapper = new TeamMapper();
		}
		return teamMapper;
	}

	/**
	 * F�gt ein Team-Objekt der Datenbank hinzu.
	 * 
	 * @param team
	 * @return trait
	 */
	public Team insert(Team team) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * Abfrage des zuletzt hinzugef�gten Prim�rschl�ssels (id). Die
			 * aktuelle id wird um eins erh�ht.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM team");
			if(rs.next()) {
			team.setId(rs.getInt("maxid") + 1);
			}
			
			stmt = con.createStatement();

			/**
			 * SQL-Anweisung zum Einf�gen des neuen Team-Tupels in die Datenbank
			 */
			stmt.executeUpdate("INSERT INTO team (id, name, desription, size)" + "VALUES (" + team.getId() + ", '"
					+ team.getName() + "', '" + team.getDescription() + "' , " + team.getSize() + ")");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return team;
	}

	/**
	 * Aktualisiert ein Team-Objekt in der Datenbank.
	 * 
	 * @param team
	 * @return team
	 */
	public Team update(Team team) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE team SET name='" + team.getName() + "', "
					+ "'description= '" + team.getDescription() + "'," + "'size='"+ team.getSize()	+ "' WHERE id= '" + team.getId());
		}

		/**
		 * Das Aufrufen des printStackTrace bietet die M�glichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert und wo im Code es passiert ist.
		 * 
		 */
		catch (SQLException e2) {
			e2.printStackTrace();
		}

		return team;
	}

	/**
	 * L�scht ein Team-Objekt aus der Datenbank.
	 * 
	 * @param team
	 */
	public void delete(Team team) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM team WHERE id=" + team.getId());
		}
		/**
		 * Das Aufrufen des printStackTrace bietet die M�glichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 * 
		 */
		catch (SQLException e2) {
			e2.printStackTrace();
		}

	}

	/**
	 * Findet ein Team-Objekt anhand der �bergebenen Id in der Datenbank.
	 * 
	 * @param id
	 * @return team
	 * 
	 */
	public Team findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name, description, size FROM team WHERE id=" + id);

			/**
			 * Zu einem Prim�rschl�ssel exisitiert nur max ein Datenbank-Tupel,
			 * somit kann auch nur einer zur�ckgegeben werden. Es wird mit einer
			 * IF-Abfragen gepr�ft, ob es f�r den angefragten Prim�rschl�ssel
			 * ein DB-Tupel gibt.
			 */

			if (rs.next()) {
				Team team = new Team();
				team.setId(rs.getInt("id"));
				team.setName(rs.getString("name"));
				team.setDescription(rs.getString("description"));
				team.setSize(rs.getInt("size"));
				return team;
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}

	/**
	 * Findet alle Team-Objekte in der Datenbank.
	 * 
	 * @return ArrayList<Team>
	 */
	public ArrayList<Team> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Team> result = new ArrayList<Team>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name, description, size FROM team ORDER BY id");

			while (rs.next()) {
				Team team = new Team();
				team.setId(rs.getInt("id"));
				team.setName(rs.getString("name"));
				team.setDescription(rs.getString("Description"));
				team.setSize(rs.getInt("size"));

				result.add(team);

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet Team-Objekte anhand des �bergebenen Namens in der Datenbank.
	 * 
	 * @param name
	 * @return ArrayList<Team>
	 */
	public ArrayList<Team> findByName(String name) {
		Connection con = DBConnection.connection();

		ArrayList<Team> result = new ArrayList<Team>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id, name, description, size FROM team WHERE name LIKE '" + name + "' ORDER BY id");

			while (rs.next()) {
				Team team = new Team();
				team.setId(rs.getInt("id"));
				team.setName(rs.getString("name"));
				team.setDescription(rs.getString("description"));
				team.setSize(rs.getInt("size"));

				result.add(team);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

}
