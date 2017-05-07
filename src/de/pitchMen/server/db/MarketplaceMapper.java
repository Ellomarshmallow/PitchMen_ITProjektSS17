package de.pitchMen.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.pitchMen.shared.bo.Company;
import de.pitchMen.shared.bo.Marketplace;

/**
* Die Klasse MarketplaceMapper bildet Marketplace-Objekte auf einer relationale Datenbank ab. 
* Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
* 
* Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende 
* Methoden (Speichern, Suchen, Löschen, Bearbeiten).
* 
* @author Heike
*
*/
public class MarketplaceMapper {

	/**
	 * Die Klasse MarketplaceMapper wird nur einmal instantiiert (Singelton-Eigenschaft). Die Variable ist
	 * mit static gekennzeichnet, da sie die einzige Instanz dieser Klasse speichert.
	 */

	private static MarketplaceMapper marketplaceMapper = null;

	/**
	 * Ein geschützter Konstrukter verhindert eine neue Instanz dieser Klasse zu erzeugen.
	 */

	protected MarketplaceMapper() {
	}

	/**
	 * Methode zum sicherstellen der Singleton-Eigenschaft. Es wird somit sichergestellt, 
	 * dass nur eine einzige Instanz der CompanyMapper existiert.
	 * 
	 * @return marketplaceMapper
	 */

	public static MarketplaceMapper marketplaceMapper() {
		if (marketplaceMapper == null){
			marketplaceMapper = new MarketplaceMapper();
		}
		return marketplaceMapper;
	}

	/**
	* Fügt ein Marketplace-Objekt der Datenbank hinzu.
	* Und gibt das korrigierte Marketplace-Objekt zurück.
	* 
	* @param marketplace 
	* @return marketplace
	* @throws ClassNotFoundException 
	*/
	public Marketplace insert(Marketplace marketplace) throws ClassNotFoundException  {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/** Abfrage des als letztes hinzugefügten Primärschlüssels des Datensatzes.
			 * Der aktuelle Primärschlüssel wird um eins erhöht.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM marketplace");
			if (rs.next()) {
				marketplace.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();

				/**
				 * Ausführen der Einfügeoperation
				 */
				stmt.executeUpdate("INSERT INTO marketplace (id, description, title)"
				 + "VALUES ( " + marketplace.getId() + ", '" + marketplace.getDescription() + "' ,'" + marketplace.getTitle() + "')");
			}
		} 
		catch (SQLException e2) {
			e2.printStackTrace();
		}
	
		return marketplace;
	}
	
    /**
     * Aktuallisiert ein Marketplace-Objekt in der Datenbank.
     * 
     * @param marketplace 
     * @return marketplace
     * @throws ClassNotFoundException
     */
	public Marketplace update(Marketplace marketplace) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE marketplace SET description= '" + marketplace.getDescription() +"', "
					+ "title= '" + marketplace.getTitle() +"' "
					+ "WHERE id=" + marketplace.getId());				
		
			} 
		catch (SQLException e2) {
			e2.printStackTrace();
		}
			
		return marketplace;
	}

    /**
     * Löscht ein Marketplace-Objekt aus der Datenbank.
     * 
     * @param marketplace 
     * @throws ClassNotFoundException
     */
    public void delete(Marketplace marketplace) throws ClassNotFoundException{
        Connection con = DBConnection.connection();
			
        try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM marketplace "
				+ "WHERE id=" + marketplace.getId());				
			} 
		
        catch (SQLException e2) {
			e2.printStackTrace();
		}
    }

    /**
     * Findet ein Marketplace-Objekt anhand der übergebenen Id in der Datenbank.
     * 
     * @param id 
     * @throws ClassNotFoundException
     */
	public Marketplace findById(int id) throws ClassNotFoundException {
		 Connection con = DBConnection.connection();
			
	        try {
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT id, description, title FROM marketplace "
					+ "WHERE id=" + id);				
						
	        /**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben.
			 * Es wird geprüft ob ein Ergebnis vorliegt
			 * Das Ergebnis-Tupel wird in ein Objekt umgewandelt.
			 * 
			 */			
			if (rs.next()){
				Marketplace marketplace = new Marketplace();
				marketplace.setId(rs.getInt("id"));
				marketplace.setDescription(rs.getString("description"));
				marketplace.setTitle(rs.getString("title"));
				
				return marketplace;
			}
		}
			
	    catch (SQLException e2){
	    	e2.printStackTrace();
	    }
		
		return null;
    }

    /**
     * Findet alle Marketplace-Objekte in der Datenbank.
     * 
     * @return ArrayList<marketplace>
     * @throws ClassNotFoundException
     */
    public ArrayList<Marketplace> findAll() throws ClassNotFoundException {
    	 Connection con = DBConnection.connection();
			
    	 ArrayList<Marketplace> result = new ArrayList<Marketplace>();
    	 
	     try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id, description, title FROM marketplace "
					+ "ORDER BY id");				
			
			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben.
			 * Es wird geprüft ob ein Ergebnis vorliegt
			 * Das Ergebnis-Tupel wird in ein Objekt umgewandelt.
			 * 
			 */	
			while (rs.next()){
				Marketplace marketplace = new Marketplace();
				marketplace.setId(rs.getInt("id"));
				marketplace.setDescription(rs.getString("description"));
				marketplace.setTitle(rs.getString("title"));
				
				result.add(marketplace);
			}
		}
			
	    catch (SQLException e2){
	    	e2.printStackTrace();
	    }
	     
        return result;
    }

	    /**
	     * Findet ein Marketplace-Objekt anhand des übergebenen Titels in der Datenbank.
	     * 
	     * @param title 
	     * @return ArrayList<Marketplace>
	     * @throws ClassNotFoundException
	     */
    public ArrayList<Marketplace> findByTitle(String title) throws ClassNotFoundException {
    	 Connection con = DBConnection.connection();
			
    	 ArrayList<Marketplace> result = new ArrayList<Marketplace>();
    	 
	     try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id, description, title FROM marketplace "
					+ "WHERE title LIKE " + title	+ "ORDER BY id");				
			
			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben.
			 * Das Ergebnis-Tupel wird in ein Objekt umgewandelt.
			 * 
			 */	
			while (rs.next()){
				Marketplace marketplace = new Marketplace();
				marketplace.setId(rs.getInt("id"));
				marketplace.setDescription(rs.getString("description"));
				marketplace.setTitle(rs.getString("title"));
				
				result.add(marketplace);
			}
		}
			
	    catch (SQLException e2){
	    	e2.printStackTrace();
	    }
	     
        return result;
    }

	
}
