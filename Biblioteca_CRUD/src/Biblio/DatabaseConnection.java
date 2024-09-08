package Biblio;

import java.sql.*;

public class DatabaseConnection {

	// Costanti di connessione
	public static final String DB_URL = "jdbc:mysql://localhost:3306/biblioteca2";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "root";

	// Metodo per creare le tabelle nel database
	public static void creaTabelle() {

		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			Statement stmt = conn.createStatement();

			String sqlAutori = "CREATE TABLE IF NOT EXISTS autori (" + "id INT AUTO_INCREMENT PRIMARY KEY, "
					+ "nome VARCHAR(100), " + "cognome VARCHAR(100));";

			String sqlLibri = "CREATE TABLE IF NOT EXISTS libri (" + "id INT AUTO_INCREMENT PRIMARY KEY, "
					+ "titolo VARCHAR(255), " + "id_autore INT, " + "anno INT, "
					+ "FOREIGN KEY (id_autore) REFERENCES autori(id) ON DELETE CASCADE);";

			stmt.execute(sqlAutori);
			stmt.execute(sqlLibri);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore nella creazione delle tabelle");
		}
	}

	// Metodi per la gestione degli autori
	// INSERT
	public static void aggiungiAutore(String nome, String cognome) {

		String query = "INSERT INTO autori (nome, cognome) VALUES (?, ?)";

		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nome);
			pstmt.setString(2, cognome);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore durante l'aggiunta dell'autore");
		}
	}

	// UPDATE
	public static void modificaAutore(int id, String nome, String cognome) {

		String query = "UPDATE autori SET nome = ?, cognome = ? WHERE id = ?";

		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nome);
			pstmt.setString(2, cognome);
			pstmt.setInt(3, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore durante la modifica dell'autore");
		}
	}

	// DELETE
	public static void cancellaAutore(int id) {

		String queryCancellaAutore = "DELETE FROM autori WHERE id = ?";

		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(queryCancellaAutore);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore durante la cancellazione dell'autore");
		}
	}

	// READ
	public static boolean visualizzaAutori() {

		String queryPrintAutori = "SELECT id, nome, cognome FROM autori";

		boolean trovato = false;

		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(queryPrintAutori);
			ResultSet rs = pstmt.executeQuery();
			
			// se presenti cicla i record degli autori, altrimenti mostra il messaggio
			while (rs.next()) {
				trovato = true;
				System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome") + ", Cognome: "
						+ rs.getString("cognome"));
			}
			if (!trovato) {
				System.out.println("Non ci sono autori da visualizzare.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore durante la visualizzazione degli autori");
		}
		return trovato;
	}

	// Gestione Libri
	// INSERT
	public static void aggiungiLibro(String titolo, int idAutore, int anno) {

		String queryInsertLibro = "INSERT INTO libri (titolo, id_autore, anno) VALUES (?, ?, ?)";

		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(queryInsertLibro);
			pstmt.setString(1, titolo);
			pstmt.setInt(2, idAutore);
			pstmt.setInt(3, anno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore durante l'aggiunta del libro");
		}
	}

	// UPDATE
	public static void modificaLibro(int id, String titolo, int idAutore, int anno) {

		String queryUpdateLibro = "UPDATE libri SET titolo = ?, id_autore = ?, anno = ? WHERE id = ?";

		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(queryUpdateLibro);
			pstmt.setString(1, titolo);
			pstmt.setInt(2, idAutore);
			pstmt.setInt(3, anno);
			pstmt.setInt(4, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore durante l'aggiornamento del libro");
		}
	}

	// DELETE
	public static void cancellaLibro(int id) {

		String queryDeleteLibro = "DELETE FROM libri WHERE id = ?";

		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(queryDeleteLibro);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore durante la cancellazione del libro");
		}
	}

	// READ
	public static boolean visualizzaLibri() {

		String queryPrintLibri = "SELECT id, titolo, id_autore, anno FROM libri";

		boolean trovato = false;

		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(queryPrintLibri);
			ResultSet rs = pstmt.executeQuery();
			
			// se presenti cicla i record dei libri, altrimenti mostra il messaggio
			while (rs.next()) {
				trovato = true;
				System.out.println("ID: " + rs.getInt("id") + ", Titolo: " + rs.getString("titolo") + ", ID Autore: "
						+ rs.getInt("id_autore") + ", Anno: " + rs.getInt("anno"));
			}
			if (!trovato) {
				System.out.println("Non ci sono libri da visualizzare.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore durante la visualizzazione dei libri");
		}
		return trovato;
	}

	// RICERCA LIBRI
	// ricerca per titolo
	public static void cercaLibroPerTitolo(String titolo) {

		String queryCercaTitolo = "SELECT * FROM libri WHERE titolo LIKE ?";

		boolean trovato = false;

		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(queryCercaTitolo);
			pstmt.setString(1, "%" + titolo + "%");
			ResultSet rs = pstmt.executeQuery();
			
			// se presenti cicla i record dei libri, altrimenti mostra il messaggio
			while (rs.next()) {
				trovato = true;
				System.out.println("ID: " + rs.getInt("id") + ", Titolo: " + rs.getString("titolo") + ", ID Autore: "
						+ rs.getInt("id_autore") + ", Anno: " + rs.getInt("anno"));
			}
			if (!trovato) {
				System.out.println("Non ci sono libri da visualizzare.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore durante la visualizzazione dei libri");
		}

	}

	// ricerca per anno
	public static void cercaLibroPerAnno(int annoInizio, int annoFine) {

		String queryCercaAnno = "SELECT * FROM libri WHERE anno BETWEEN ? AND ?";

		boolean trovato = false;

		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(queryCercaAnno);
			pstmt.setInt(1, annoInizio);
			pstmt.setInt(2, annoFine);

			ResultSet rs = pstmt.executeQuery();
			
			// se presenti cicla i record dei libri, altrimenti mostra il messaggio
			while (rs.next()) {
				trovato = true;
				System.out.println("ID: " + rs.getInt("id") + ", Titolo: " + rs.getString("titolo") + ", ID Autore: "
						+ rs.getInt("id_autore") + ", Anno: " + rs.getInt("anno"));
			}
			if (!trovato) {
				System.out.println("Non ci sono libri da visualizzare.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore durante la visualizzazione dei libri");
		}

	}

	// ricerca per autore
	public static void cercaLibroPerAutore(String nome, String cognome) {

		String queryCercaAutore = "SELECT * FROM libri JOIN autori ON libri.id_autore = autori.id WHERE autori.nome LIKE ? OR autori.cognome LIKE ?";

		boolean trovato = false;

		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(queryCercaAutore);
			pstmt.setString(1, "%" + nome + "%");
			pstmt.setString(2, "%" + cognome + "%");

			ResultSet rs = pstmt.executeQuery();
			
			// se presenti cicla i record dei libri, altrimenti mostra il messaggio
			while (rs.next()) {
				trovato = true;
				System.out.println("ID: " + rs.getInt("id") + ", Titolo: " + rs.getString("titolo") + ", ID Autore: "
						+ rs.getInt("id_autore") + ", Anno: " + rs.getInt("anno"));
			}
			if (!trovato) {
				System.out.println("Non ci sono libri da visualizzare.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore durante la visualizzazione dei libri");
		}

	}

}
