package de.pitchMen.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.pitchMen.shared.bo.Person;

/**
 * Bildet Person-Objekte auf eine relationale Datenbank ab. Ebenfalls ist es m�glich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 * 
 * @author Lars
 */

public class PersonMapper {
	
	/**
	 * Die Klasse PersonMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erf�llt werden kann,
	 * wird zun�chst eine Variable mit dem Schl�sselwort static und dem
	 * Standardwert null erzeugt. Sie speichert die Instanz dieser Klasse.
	 */

	private static PersonMapper personMapper = null;

	/**
	 * Ein gesch�tzter Konstruktor verhindert das erneute erzeugen von weiteren
	 * Instanzen dieser Klasse.
	 */

	protected PersonMapper() {

	}

	/**
	 * Methode zum sicherstellen der Singleton-Eigenschaft. Diese sorgt daf�r,
	 * dass nur eine einzige Instanz der PersonMapper-Klasse existiert.
	 * Aufgerufen wird die Klasse somit �ber PersonMapper.personMapper() und
	 * nicht �ber den New-Operator.
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
     * F�gt ein Person-Objekt der Datenbank hinzu.
     * 
     * @param person 
     * @throws ClassNotFoundException
     * @return person
     */
	public Person insert(Person person) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * Abfrage des zuletzt hinzugef�gten Prim�rschl�ssels (id). Die
			 * aktuelle id wird um eins erh�ht.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM person");

			person.setId(rs.getInt("maxid") + 1);
			stmt = con.createStatement();

			/**
			 * SQL-Anweisung zum Einf�gen des neuen Person-Tupels in die
			 * Datenbank
			 */
			stmt.executeUpdate("INSERT INTO person (id, name, description, firstName, email) VALUES (" + person.getId() + ", '"
					+ person.getName() + "', '" + person.getDescription() + "', '" + person.getFirstName() + "', '" + person.getEmail() + "')");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return person;
	}

	/**
	 * Aktualisiert ein Person-Objekt in der Datenbank.
	 * 
	 * @param person
	 * @throws ClassNotFoundException
	 * @return person
	 */
	public Person update(Person person) throws ClassNotFoundException {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(
					"UPDATE person SET name='" + person.getName() + "', description= '" + person.getDescription()
							+ "', firstName= '" + person.getFirstName() + "' WHERE id= " + person.getId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return person;
	}

	/**
	 * L�scht ein Person-Objekt aus der Datenbank.
	 * 
	 * @param person
	 */
	public void delete(Person person) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM person WHERE id=" + person.getId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}

	}

    /**
     * Findet ein Person-Objekt anhand der �bergebenen Id in der Datenbank.
     * 
     * @param id 
     * @return person
     */
    public Person findById(int id) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id, name, description, firstName FROM person WHERE id=" + id);

			/**
			 * Zu einem Prim�rschl�ssel exisitiert nur max ein Datenbank-Tupel,
			 * somit kann auch nur einer zur�ckgegeben werden. Es wird mit einer
			 * IF-Abfragen gepr�ft, ob es f�r den angefragten Prim�rschl�ssel
			 * ein DB-Tupel gibt.
			 */

			if (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setDescription(rs.getString("description"));
				person.setFirstName(rs.getString("firstName"));
				
				return person;
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
        return null;
    }

	/**
	 * Findet alle Person-Objekte in der Datenbank.
	 * 
	 * @return result
	 */
	public ArrayList<Person> findAll() throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		ArrayList<Person> result = new ArrayList<Person>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name, description, firstName FROM person ORDER BY id");
		
			if (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setDescription(rs.getString("description"));
				person.setFirstName(rs.getString("firstName"));
				result.add(person);
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

    /**
     * Findet Person-Objekte anhand des �bergebenen Namens in der Datenbank.
     * 
     * @param name 
     * @return result
     */
    public ArrayList<Person> findByName(String name) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		ArrayList<Person> result = new ArrayList<Person>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name, description, firstName FROM person WHERE name LIKE '" + name + "' ORDER BY id");

			if (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setDescription(rs.getString("description"));
				person.setFirstName(rs.getString("firstName"));
				result.add(person);
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
        return result;
    }

    /**
     * Findet ein Person-Objekt anhand des �bergebenen Vornamens in der Datenbank.
     * 
     * @param firstName 
     * @return result
     */
    public ArrayList<Person> findByFirstName(String firstName)throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		ArrayList<Person> result = new ArrayList<Person>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name, description, firstName FROM person WHERE name LIKE '" + firstName + "' ORDER BY id");

			if (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setDescription(rs.getString("description"));
				person.setFirstName(rs.getString("firstName"));
				result.add(person);
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
        return result;
    }
  
    
    public Person findByEmail(String email)throws ClassNotFoundException {
  		Connection con = DBConnection.connection();

  		ArrayList<Person> result = new ArrayList<Person>();
  		try {
  			Statement stmt = con.createStatement();
  			ResultSet rs = stmt.executeQuery("SELECT id, name, description, firstName, email FROM person WHERE email LIKE '" + email + "' ORDER BY id");

  			if (rs.next()) {
  				Person person = new Person();
  				person.setId(rs.getInt("id"));
  				person.setName(rs.getString("name"));
  				person.setDescription(rs.getString("description"));
  				person.setFirstName(rs.getString("firstName"));
  				person.setEmail(rs.getString("email"));
  				result.add(person);
  			}

  		} catch (SQLException e2) {
  			e2.printStackTrace();
  		}
          return result;
          //TODO: Fehler verbessern
      }
    
    

}