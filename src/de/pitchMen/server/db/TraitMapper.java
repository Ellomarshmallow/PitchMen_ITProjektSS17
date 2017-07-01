package de.pitchMen.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.pitchMen.shared.bo.Trait;

/**
 * Die Klasse TraitMapper bildet Trait-Objekte auf einer relationalen Datenbank
 * ab. Ebenfalls ist es moeglich aus den Datenbank-Tupel Java-Objekte zur
 * erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden (insert, search, delete, update).
 * 
 * @author Lars
 */
public class TraitMapper {

	/**
	 * Die Klasse TraitMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft gegeben ist, wird eine
	 * Variable mit dem Schluesselwort static und dem Standardwert null erzeugt.
	 * Sie speichert die Instanz dieser Klasse.
	 */
	private static TraitMapper traitMapper = null;

	/**
	 * Ein geschuetzter Konstruktor verhindert das erneute erzeugen von weiteren
	 * Instanzen dieser Klasse.
	 */
	protected TraitMapper() {
	}

	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafuer,
	 * dass nur eine einzige Instanz der TraitMapper-Klasse existiert.
	 * Aufgerufen wird die Klasse somit ueber TraitMapper.traitMapper() und nicht
	 * ueber den New-Operator.
	 * 
	 * @return traitMapper
	 */
	public static TraitMapper traitMapper() {
		if (traitMapper == null) {
			traitMapper = new TraitMapper();
		}
		return traitMapper;
	}

	/**
	 * Fuegt ein Trait-Objekt der Datenbank hinzu.
	 * 
	 * @param trait
	 * @return trait
	 */
	public Trait insert(Trait trait) {
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
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM trait");
			if(rs.next()) {
				trait.setId(rs.getInt("maxid") + 1);
			}
			stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Einfuegen des neuen Datensatzes in die Datenbank.
			 */
			stmt.executeUpdate("INSERT INTO trait (id, name, value, partnerProfile_id)" 
					+ "VALUES (" + trait.getId()
					+ ", '" + trait.getName() + "', '" + trait.getValue() + "', " + trait.getPartnerProfileId()
					+ ")");
		/**
		 * Das Aufrufen des printStackTrace bietet die Mï¿½glichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return trait;
	}

	/**
	 * Aktualisiert ein Trait-Objekt in der Datenbank.
	 * 
	 * @param trait
	 * @return trait
	 */
	public Trait update(Trait trait) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zur Aktualisierung des uebergebenen Datensatzes in der Datenbank.
			 */
			stmt.executeUpdate("UPDATE trait SET name='" + trait.getName() + "', value= '" + trait.getValue()
					+ "' WHERE id= " + trait.getId());
		}
		/**
		 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return trait;
	}

	/**
	 * Loescht ein Trait-Objekt aus der Datenbank.
	 * 
	 * @param trait
	 */
	public void delete(Trait trait) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Loeschen des ï¿½bergebenen Datensatzes in der Datenbank.
			 */
			stmt.executeUpdate("DELETE FROM trait WHERE id=" + trait.getId());
		/**
		 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Findet ein Trait-Objekt anhand der uebergebenen Id in der Datenbank.
	 * 
	 * @param id
	 * @return trait
	 */
	public Trait findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, anhand der uebergebenen Id, in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery("SELECT id, name, value, partnerProfil_id FROM trait WHERE id=" + id);
			/**
			 * Zu einem Primaerschluessel existiert nur max ein Datenbank-Tupel,
			 * somit kann auch nur einer zurueckgegeben werden. Es wird mit einer
			 * IF-Abfragen geprueft, ob es fuer den angefragten Primaerschluessel
			 * ein DB-Tupel gibt.
			 */
			if (rs.next()) {
				Trait trait = new Trait();
				trait.setId(rs.getInt("id"));
				trait.setName(rs.getString("name"));
				trait.setValue(rs.getString("value"));
				trait.setPartnerProfileId(rs.getInt("partnerProfil_id"));
				return trait;
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
	 * Findet alle Trait-Objekte in der Datenbank.
	 * 
	 * @return ArrayList<Trait>
	 */
	public ArrayList<Trait> findAll() {
		Connection con = DBConnection.connection();
		/**
		 * Erzeugen einer ArrayList.
		 */
		ArrayList<Trait> result = new ArrayList<Trait>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden aller Datensatzes in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM trait ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle trait vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschliessend der ArrayList hinzugefuegt.
			 */
			while (rs.next()) {
				Trait trait = new Trait();
				trait.setId(rs.getInt("id"));
				trait.setName(rs.getString("name"));
				trait.setValue(rs.getString("value"));
				//trait.setPartnerProfileId(rs.getInt("partnerProfileId"));
				result.add(trait);
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
	 * Findet Trait-Objekte anhand des uebergebenen Namens in der Datenbank.
	 * 
	 * @param name
	 * @return ArrayList<Trait>
	 */
	public ArrayList<Trait> findByName(String name) {
		Connection con = DBConnection.connection();
		/**
		 * Erzeugen einer ArrayList
		 */
		ArrayList<Trait> result = new ArrayList<Trait>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach dem gesuchten Namen, in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt.executeQuery(
					"SELECT id, name, value, partnerProfil_id FROM trait WHERE name LIKE '" + name + "' ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle trait mit dem uebergebenen Namen vorhanden ist, muss das
			 * Abfragen des ResultSet so oft erfolgen (while-Schleife), bis alle
			 * Tupel durchlaufen wurden. Die DB-Tupel werden in Java-Objekte
			 * transformiert und anschliessend der ArrayList hinzugefuegt.
			 */
			while (rs.next()) {
				Trait trait = new Trait();
				trait.setId(rs.getInt("id"));
				trait.setName(rs.getString("name"));
				trait.setValue(rs.getString("value"));
				trait.setPartnerProfileId(rs.getInt("partnerProfil_id"));
				result.add(trait);
			}
		/**
		* Das Aufrufen des printStackTrace bietet die Moelichkeit, die
		* Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		* ausgegeben, was passiert ist und wo im Code es passiert ist.
		*/	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein Trait-Objekt anhand des uebergebenen Wertes(value) in der Datenbank.
	 * 
	 * @param value
	 * @return ArrayList<Trait>
	 */
	public ArrayList<Trait> findByValue(String value) {
		Connection con = DBConnection.connection();
		/**
		* Erzeugen einer ArrayList.
		*/	
		ArrayList<Trait> result = new ArrayList<Trait>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach dem gesuchten Wert, in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt.executeQuery(
					"SELECT id, name, value, partnerProfil_id FROM trait WHERE value LIKE '" + value + "' ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle trait mit dem uebergebenen Wert vorhanden ist,
			 * muss das Abfragen des ResultSet so oft erfolgen (while-Schleife),
			 * bis alle Tupel durchlaufen wurden. Die DB-Tupel werden in
			 * Java-Objekte transformiert und anschliessend der ArrayList
			 * hinzugefuegt.
			 */
			while (rs.next()) {
				Trait trait = new Trait();
				trait.setId(rs.getInt("id"));
				trait.setName(rs.getString("name"));
				trait.setValue(rs.getString("value"));
				trait.setPartnerProfileId(rs.getInt("partnerProfil_id"));
				result.add(trait);
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
	 * Findet die Eigenschaften passend zur Person-Id, innerhalb der ParterProfile Tabelle. 
	 * uebergibt ein Trait-Objekt zum Vergleich der Traits mit der ArrayList aus der 
	 * Methode @seeFindByJobPosting.
	 * 
	 * Mit der Inner-Join-Klausel wird erreicht, dass nur die Datensaetze zusammengefuegt
	 * werden, zu den es jeweils auch ein Gegenstueck in der verknuepften 
	 * Tabelle gibt. Da es moeglich ist, dass ein Partnerprofil mehrere Eigenschaften hat,
	 * muessen die PartnerProfile-Objekte in einer ArrayList gespeichert werden.
	 * 
	 * @param id
	 * @return trait
	 */
	public Trait findPartnerProfielByPersonId(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach der gesuchten PersonenId, in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery("SELECT * FROM partnerProfile "
					+ "INNER JOIN trait ON partnerProfile.id = trait.partnerProfile_id "
					+ "WHERE person_id =" + id);
			/**
			 * Zu einem Primï¿½rschlï¿½ssel exisitiert nur max ein Datenbank-Tupel,
			 * somit kann auch nur einer zurï¿½ckgegeben werden. Es wird mit einer
			 * IF-Abfrage geprueft, ob es fuer den angefragten Primaerschluessel ein
			 * DB-Tupel gibt.
			 */
			if (rs.next()) {
				Trait trait = new Trait();
				trait.setId(rs.getInt("id"));
				trait.setName(rs.getString("name"));
				trait.setValue(rs.getString("value"));
				trait.setPartnerProfileId(rs.getInt("partnerProfil-id"));
				return trait;
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
	 * Findet alle Eigenschaften (Traits) passend zum ParnterProfile, innerhalb der ParterProfile Tabelle. 
	 * Ausgegeben wird eine ArrayListe, welche die Trait-Objekte beinhaltet
	 *
	 * Mit der Inner-Join-Klausel wird erreicht, dass nur die Datensaetze zusammengefuegt
	 * werden, zu den es jeweils auch ein Gegenstueck in der verknuepften 
	 * Tabelle gibt. Da es moeglich ist, dass ein Partnerprofil mehrere Eigenschaften hat,
	 * muessen die PartnerProfile-Objekte in einer ArrayList gespeichert werden.
	 * 
	 * @param partnerProfileId
	 * @return ArrayList<Traits>
	 * 
	 */
	public ArrayList<Trait> findTraitByPartnerProfileId(int partnerProfileId) {
		Connection con = DBConnection.connection();

		ArrayList<Trait> result = new ArrayList<Trait>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach der gesuchten PartnerProfilId, in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery("SELECT * FROM trait WHERE partnerProfile_id = " + partnerProfileId);
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle trait mit dem uebergebenen Wert vorhanden ist,
			 * muss das Abfragen des ResultSet so oft erfolgen (while-Schleife),
			 * bis alle Tupel durchlaufen wurden. Die DB-Tupel werden in
			 * Java-Objekte transformiert und anschliessend der ArrayList
			 * hinzugefuegt.
			 */
			while (rs.next()) {
				Trait trait = new Trait();
				trait.setId(rs.getInt("id"));
				trait.setName(rs.getString("name"));
				trait.setValue(rs.getString("value"));
				trait.setPartnerProfileId(rs.getInt("partnerProfile_Id"));
				result.add(trait);
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
	 * Mapper zum Abfragen von Traits aus der Trait Tabelle, welcher aber nur einem jobPosting angehören. 
	 * Benötigt wird die Methode für den Report "Alle Ausschreibungen passend zum Partnerprofil"
	 * @param partnerProfileId
	 * @return trait
	 */
	
	public ArrayList<Trait> findTraitsFromJobPostings() {
		Connection con = DBConnection.connection();

		ArrayList<Trait> result = new ArrayList<Trait>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum finden von Trait-Datensätzen, welche einem JobPosting und keiner Person angehören
			 */
			ResultSet rs = stmt.executeQuery("SELECT * FROM trait INNER JOIN partnerProfile ON trait.partnerProfile_id = partnerProfile.id WHERE partnerProfile.jobPosting_id != 0");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle trait mit dem uebergebenen Wert vorhanden ist,
			 * muss das Abfragen des ResultSet so oft erfolgen (while-Schleife),
			 * bis alle Tupel durchlaufen wurden. Die DB-Tupel werden in
			 * Java-Objekte transformiert und anschliessend der ArrayList
			 * hinzugefuegt.
			 */
			while (rs.next()) {
				Trait trait = new Trait();
				trait.setId(rs.getInt("id"));
				trait.setName(rs.getString("name"));
				trait.setValue(rs.getString("value"));
				trait.setPartnerProfileId(rs.getInt("partnerProfile_Id"));
				result.add(trait);
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
	
}