package de.pitchMen.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import de.pitchMen.shared.bo.Person;

/**
 * Die Klasse PersonMapper bildet Person-Objekte auf eine relationale Datenbank
 * ab. Ebenfalls ist es moeglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden (insert, search, delete, update).
 * 
 * @author Lars
 */
public class PersonMapper {

	/**
	 * Die Klasse PersonMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfuellt werden kann,
	 * wird zunaechst eine Variable mit dem Schluesselwort static und dem
	 * Standardwert null erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	private static PersonMapper personMapper = null;

	/**
	 * Ein geschuetzter Konstruktor verhindert das erneute erzeugen von weiteren
	 * Instanzen dieser Klasse.
	 */
	protected PersonMapper() {
	}

	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafuer,
	 * dass nur eine einzige Instanz der PersonMapper-Klasse existiert.
	 * Aufgerufen wird die Klasse somit ueber PersonMapper.personMapper() und
	 * nicht ueber den New-Operator.
	 * 
	 * @return personMapper
	 */
	public static PersonMapper personMapper() {
		if (personMapper == null) {
			personMapper = new PersonMapper();
		}
		return personMapper;
	}

	/**
	 * Fuegt ein Person-Objekt der Datenbank hinzu.
	 * 
	 * @param person
	 * @return person
	 */
	public Person insert(Person person) {
		/**
		 * DB-Verbindung holen.
		 */
		Connection con = DBConnection.connection();

		try {
			/**
			 * leeres SQL-Statement (JDBC) anlegen.
			 */
			Statement stmt = con.createStatement();
			/**
			 * Abfrage des zuletzt hinzugefuegten Primaerschluessel (id). Die
			 * aktuelle id wird um eins erhoeht. Statement ausfuellen und als
			 * Query an die Datenbank senden.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM person");

			if (rs.next()) {
				person.setId(rs.getInt("maxid") + 1);
			}
			stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Einfuegen des neuen Person-Tupels in die
			 * Datenbank.
			 */
			stmt.executeUpdate("INSERT INTO person (id, name, description, firstName, email) VALUES (" + person.getId()
					+ ", '" + person.getName() + "', '" + person.getDescription() + "', '" + person.getFirstName()
					+ "', '" + person.getEmailAdress() + "')");
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen
			 * dazu ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return person;
	}

	/**
	 * Aktualisiert ein Person-Objekt in der Datenbank.
	 * 
	 * @param person
	 * @return person
	 */
	public Person update(Person person) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Aktualisieren des uebergebenen Datensatzes in
			 * der Datenbank.
			 */
			stmt.executeUpdate("UPDATE person SET name='" + person.getName() + "', description= '"
					+ person.getDescription() + "', firstName= '" + person.getFirstName() + "', email= '"
					+ person.getEmailAdress() + "' WHERE id= " + person.getId());
		}
		/**
		 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return person;
	}

	/**
	 * Loescht ein Person-Objekt aus der Datenbank.
	 * 
	 * @param person
	 */
	public void delete(Person person) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Loeschen des uebergebenen Datensatzes in der
			 * Datenbank.
			 */
			stmt.executeUpdate("DELETE FROM person WHERE id=" + person.getId());
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
	 * Findet ein Person-Objekt anhand der Übergebenen Id in der Datenbank.
	 * 
	 * @param id
	 * @return person
	 */
	public Person findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des uebergebenen Datensatzes, anhand der
			 * Id, in der Datenbank.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT id, name, description, firstName, email FROM person WHERE id=" + id);
			/**
			 * Zu einem Primaerschluessel exisitiert nur maximal ein
			 * Datenbank-Tupel, somit kann auch nur einer zurueckgegeben werden.
			 * Es wird mit einer If-Abfragen geprueft, ob es fuer den
			 * angefragten Primaerschluessel ein DB-Tupel gibt.
			 */
			if (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setDescription(rs.getString("description"));
				person.setFirstName(rs.getString("firstName"));
				person.setEmailAdress(rs.getString("email"));
				return person;
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen
			 * dazu ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}

	/**
	 * Findet alle Person-Objekte in der Datenbank.
	 * 
	 * @return ArrayList<Person>
	 */
	public ArrayList<Person> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Person> result = new ArrayList<Person>();
		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden aller Datensaetze in der Datenbank,
			 * sortiert nach der Id.
			 */
			ResultSet rs = stmt.executeQuery("SELECT id, name, description, firstName, email FROM person ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle person vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschliessend der ArrayList hinzugefuegt.
			 */

			while (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setDescription(rs.getString("description"));
				person.setFirstName(rs.getString("firstName"));
				person.setEmailAdress(rs.getString("email"));
				result.add(person);
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen
			 * dazu ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet Person-Objekte anhand des uebergebenen Namens in der Datenbank.
	 * 
	 * @param name
	 * @return ArrayList<Person>
	 */
	public ArrayList<Person> findByName(String name) {
		Connection con = DBConnection.connection();

		ArrayList<Person> result = new ArrayList<Person>();
		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, anhand des uebergebenen
			 * Namens, in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT id, name, description, firstName, email FROM person WHERE name LIKE '" + name
							+ "' ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle Person mit dem uebergebenen Namen vorhanden ist, muss das
			 * Abfragen des ResultSet so oft erfolgen (while-Schleife), bis alle
			 * Tupel durchlaufen wurden. Die DB-Tupel werden in Java-Objekte
			 * transformiert und anschliessend der ArrayList hinzugefuegt.
			 */
			while (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setDescription(rs.getString("description"));
				person.setFirstName(rs.getString("firstName"));
				person.setEmailAdress(rs.getString("email"));
				result.add(person);
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen
			 * dazu ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein Person-Objekt anhand des uebergebenen Vornamens in der
	 * Datenbank.
	 * 
	 * @param firstName
	 * @return ArrayList<Person>
	 */
	public ArrayList<Person> findByFirstName(String firstName) {
		Connection con = DBConnection.connection();

		ArrayList<Person> result = new ArrayList<Person>();
		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden aller Datensaetze, anhand des Vornamens,
			 * in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt.executeQuery("SELECT id, name, description, firstName FROM person WHERE name LIKE '"
					+ firstName + "' ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle Person mit dem uebergebenen Vornamen vorhanden ist, muss
			 * das Abfragen des ResultSet so oft erfolgen (while-Schleife), bis
			 * alle Tupel durchlaufen wurden. Die DB-Tupel werden in
			 * Java-Objekte transformiert und anschliessend der ArrayList
			 * hinzugefuegt.
			 */
			while (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setDescription(rs.getString("description"));
				person.setFirstName(rs.getString("firstName"));
				person.setEmailAdress(rs.getString("email"));
				result.add(person);
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen
			 * dazu ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein Person-Objekt anhand der uebergebenen eMail in der Datenbank.
	 * 
	 * @param email
	 * @return person
	 */
	public Person findByEmail(String email) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden aller Datensaetze, anhand der eMail, in
			 * der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT id, name, description, firstName, email FROM person WHERE email LIKE '"
							+ email + "' ORDER BY id");
			/**
			 * Zu einem eindeutigen Wert exisitiert nur maximal ein
			 * Datenbank-Tupel, somit kann auch nur einer zurueckgegeben werden.
			 * Es wird mit einer If-Abfragen geprueft, ob es fuer den
			 * angefragten Primaerschluessel ein DB-Tupel gibt.
			 */
			if (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setDescription(rs.getString("description"));
				person.setFirstName(rs.getString("firstName"));
				person.setEmailAdress(rs.getString("email"));
				return person;
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen
			 * dazu ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}

}