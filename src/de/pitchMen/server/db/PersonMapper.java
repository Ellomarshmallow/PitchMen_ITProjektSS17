package de.pitchMen.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.pitchMen.shared.bo.Application;
import de.pitchMen.shared.bo.Person;

/**
 * Die Klasse PersonMapper bildet Person-Objekte auf eine relationale Datenbank ab. 
 * Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden (insert, search, delete, update).
 * 
 * @author Lars
 */
public class PersonMapper {

	/**
	 * Die Klasse PersonMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann,
	 * wird zunächst eine Variable mit dem Schlüsselwort static und dem
	 * Standardwert null erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	private static PersonMapper personMapper = null;

	/**
	 * Ein geschützter Konstruktor verhindert das erneute erzeugen von weiteren
	 * Instanzen dieser Klasse.
	 */
	protected PersonMapper() {
	}

	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür,
	 * dass nur eine einzige Instanz der PersonMapper-Klasse existiert.
	 * Aufgerufen wird die Klasse somit über PersonMapper.personMapper() und
	 * nicht über den New-Operator.
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
	 * Fügt ein Person-Objekt der Datenbank hinzu.
	 * 
	 * @param person
	 * @return person
	 */
	public Person insert(Person person) {
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
			 * Abfrage des zuletzt hinzugefuegten Primaerschluessel (id). Die
			 * aktuelle id wird um eins erhoeht. 
			 * Statement ausfüllen und als Query an die Datenbank senden.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM person");

			if(rs.next()) {
				person.setId(rs.getInt("maxid") + 1);
			}
			stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Einfügen des neuen Person-Tupels in die Datenbank.
			 */
			stmt.executeUpdate("INSERT INTO person (id, name, description, firstName, email) VALUES (" + person.getId()
					+ ", '" + person.getName() + "', '" + person.getDescription() + "', '" + person.getFirstName()
					+ "', '" + person.getEmailAdress() + "')");
			/**
			 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
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
			 * SQL-Anweisung zum Aktualisieren des übergebenen Datensatzes in der Datenbank.
			 */		
			stmt.executeUpdate("UPDATE person SET name='" + person.getName() + "', description= '"
					+ person.getDescription() + "', firstName= '" + person.getFirstName() + "', email= '"
					+ person.getEmailAdress() + "' WHERE id= " + person.getId());
		}
		/**
		 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */	
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return person;
	}

	/**
	 * Löscht ein Person-Objekt aus der Datenbank.
	 * 
	 * @param person
	 */
	public void delete(Person person) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Löschen des übergebenen Datensatzes in der Datenbank.
			 */
			stmt.executeUpdate("DELETE FROM person WHERE id=" + person.getId());
		}
		/**
		 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
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
			 * SQL-Anweisung zum Finden des übergebenen Datensatzes, anhand der Id, in der Datenbank.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT id, name, description, firstName, email FROM person WHERE id=" + id);
			/**
			 * Zu einem Primärschlüssel exisitiert nur maximal ein
			 * Datenbank-Tupel, somit kann auch nur einer zurückgegeben werden.
			 * Es wird mit einer If-Abfragen geprüft, ob es für den
			 * angefragten Primärschlüssel ein DB-Tupel gibt.
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
			 * SQL-Anweisung zum Finden aller Datensätze in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt.executeQuery("SELECT id, name, description, firstName, email FROM person ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle person vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschließend der ArrayList hinzugefügt.
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
			 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet Person-Objekte anhand des übergebenen Namens in der Datenbank.
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
			 * SQL-Anweisung zum Finden des Datensatzes, anhand des übergebenen Namens, in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT id, name, description, firstName, email FROM person WHERE name LIKE '" + name
							+ "' ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle Person mit dem übergebenen Namen vorhanden ist, muss das
			 * Abfragen des ResultSet so oft erfolgen (while-Schleife), bis alle
			 * Tupel durchlaufen wurden. Die DB-Tupel werden in Java-Objekte
			 * transformiert und anschließend der ArrayList hinzugefügt.
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
			 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein Person-Objekt anhand des übergebenen Vornamens in der Datenbank.
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
			 * SQL-Anweisung zum Finden aller Datensätze, anhand des Vornamens, in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt.executeQuery("SELECT id, name, description, firstName FROM person WHERE name LIKE '"
					+ firstName + "' ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle Person mit dem übergebenen Vornamen vorhanden ist, muss das
			 * Abfragen des ResultSet so oft erfolgen (while-Schleife), bis alle
			 * Tupel durchlaufen wurden. Die DB-Tupel werden in Java-Objekte
			 * transformiert und anschließend der ArrayList hinzugefügt.
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
			 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein Person-Objekt anhand der übergebenen eMail in der Datenbank.
	 * 
	 * @param email
	 * @return person
	 */	
	public Person findByEmail(String email) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden aller Datensätze, anhand der eMail, in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT id, name, description, firstName, email FROM person WHERE email LIKE '"
							+ email + "' ORDER BY id");
			/**
			 * Zu einem eindeutigen Wert exisitiert nur maximal ein
			 * Datenbank-Tupel, somit kann auch nur einer zurückgegeben werden.
			 * Es wird mit einer If-Abfragen geprüft, ob es für den
			 * angefragten Primärschlüssel ein DB-Tupel gibt.
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
			 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}

	//FIXME von Elli. Stimmt das so oder muss man etwas beim PartnerProfil beachten?
	/**
	 * Findet ein Person-Objekt anhand der übergebenen applicationId in der Datenbank.
	 * 
	 * @param applicationId
	 * @return person
	 */	
	public Person findByApplicationId(int applicationId) {
		Connection con = DBConnection.connection();
		
		Person result = new Person();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung - Anhand der �bergebenen applicationId werden die dazugeh�rigen
			 * Application-Tupel (Bewerbungen) aus der Datenbank abgefragt.
			 */
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM person "
					+ "WHERE application_id = " + applicationId);

			/**
			 * Zu einem eindeutigen Wert exisitiert nur maximal ein
			 * Datenbank-Tupel, somit kann auch nur einer zurückgegeben werden.
			 * Es wird mit einer If-Abfragen geprüft, ob es für den
			 * angefragten Primärschlüssel ein DB-Tupel gibt.
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