package de.pitchMen.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.pitchMen.shared.bo.Company;

/**
 * Die Klasse CompanyMapper bildet Company-Objekte auf einer relationale
 * Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu
 * erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden (Speichern, Suchen, Löschen, Bearbeiten).
 * 
 * @author Heike
 *
 */

public class CompanyMapper {

	/**
	 * Die Klasse CompanyMapper wird nur einmal instantiiert
	 * (Singelton-Eigenschaft). Die Variable ist mit static gekennzeichnet, da
	 * sie die einzige Instanz dieser Klasse speichert.
	 */

	private static CompanyMapper companyMapper = null;

	/**
	 * Ein geschützter Konstrukter verhindert eine neue Instanz dieser Klasse zu
	 * erzeugen.
	 */

	protected CompanyMapper() {
	}

	/**
	 * Methode zum sicherstellen der Singleton-Eigenschaft. Es wird somit
	 * sichergestellt, dass nur eine einzige Instanz der CompanyMapper
	 * existiert.
	 * 
	 * @return companyMapper
	 */

	public static CompanyMapper companyMapper() {
		if (companyMapper == null) {
			companyMapper = new CompanyMapper();
		}
		return companyMapper;
	}

	/**
	 * Fügt ein Company-Objekt der Datenbank hinzu. Und gibt das korrigierte
	 * Customerobjekt zurück.
	 * 
	 * @param company
	 * @return company
	 */
	public Company insert(Company company) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/**
			 * Abfrage des als letztes hinzugefügten Primärschlüssels des
			 * Datensatzes. Der aktuelle Primärschlüssel wird um eins erhöht.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM company");
			if (rs.next()) {
				company.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();

				/**
				 * Ausführen der Einfügeoperation
				 */
				stmt.executeUpdate("INSERT INTO company (id, name, description)" + "VALUES ( " + company.getId() + ", '"
						+ company.getName() + "' ,'" + company.getDescription() + "')");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return company;
	}

	/**
	 * Aktuallisiert ein Company-Objekt in der Datenbank.
	 * 
	 * @param company
	 * @return company
	 */
	public Company update(Company company) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE company SET Name='" + company.getName() + "', " + "description='"
					+ company.getDescription() + "' " + "WHERE id=" + company.getId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}

		return company;
	}

	/**
	 * Löscht ein Company-Objekt aus der Datenbank.
	 * 
	 * @param company
	 */
	public void delete(Company company) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM company " + "WHERE id=" + company.getId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Findet ein Company-Objekt anhand der übergebenen Id in der Datenbank.
	 * 
	 * @param id
	 * @return company
	 */
	public Company findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, name, description FROM company " + "WHERE id=" + id);

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben. Es
			 * wird geprüft ob ein Ergebnis vorliegt Das Ergebnis-Tupel wird in
			 * ein Objekt umgewandelt.
			 * 
			 */
			if (rs.next()) {
				Company company = new Company();
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setDescription(rs.getString("description"));

				return company;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return null;
	}

	/**
	 * Findet alle Company-Objekte in der Datenbank.
	 * 
	 * @return ArrayList<Company>
	 */
	public ArrayList<Company> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Company> result = new ArrayList<Company>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, name, description FROM company " + "ORDER BY id");

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben. Es
			 * wird geprüft ob ein Ergebnis vorliegt Das Ergebnis-Tupel wird in
			 * ein Objekt umgewandelt.
			 * 
			 */
			while (rs.next()) {
				Company company = new Company();
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setDescription(rs.getString("description"));

				result.add(company);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	/**
	 * Findet ein Company-Objekt anhand des übergebenen Namens in der Datenbank.
	 * 
	 * @param name
	 * @return ArryList<company>
	 */
	public ArrayList<Company> findByName(String name) {
		Connection con = DBConnection.connection();

		ArrayList<Company> result = new ArrayList<Company>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, name, description FROM company " + "WHERE name LIKE " + name + "ORDER BY id");

			/**
			 * Der Primärschlüssel (id) wird als eine TUpel zurück gegeben. Das
			 * Ergebnis-Tupel wird in ein Objekt umgewandelt.
			 * 
			 */
			while (rs.next()) {
				Company company = new Company();
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setDescription(rs.getString("description"));

				result.add(company);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

}
