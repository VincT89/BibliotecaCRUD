package Biblio;

import java.util.Scanner;

public class Main {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		// richiama il metodo per creare le tabelle se non esistono gia
		DatabaseConnection.creaTabelle();
		
		// menu principale
		while (true) {
			System.out.println("\n=== Menu Principale ===");
			System.out.println("1. Gestione Libri");
			System.out.println("2. Gestione Autori");
			System.out.println("3. Ricerca Avanzata Libri");
			System.out.println("4. Esci");
			System.out.println("Seleziona un'opzione: ");
			int scelta = scanner.nextInt();
			scanner.nextLine();

			switch (scelta) {
			case 1:
				gestioneLibri();
				break;
			case 2:
				gestioneAutori();
				break;
			case 3:
				ricercaAvanzataLibri();
				break;
			case 4:
				System.out.println("Uscita dal programma.");
				return;
			default:
				System.out.println("Opzione non valida. Riprova.");
			}
		}
	}

	// gestione Libri
	private static void gestioneLibri() {
		
		while (true) {
			System.out.println("\n=== Gestione Libri ===");
			System.out.println("1. Aggiungi un nuovo libro");
			System.out.println("2. Visualizza tutti i libri");
			System.out.println("3. Modifica un libro");
			System.out.println("4. Cancella un libro");
			System.out.println("5. Torna al menu principale");
			System.out.println("Seleziona un'opzione: ");
			
			int scelta = scanner.nextInt();
			scanner.nextLine();

			switch (scelta) {
			case 1:
				aggiungiLibro();
				break;
			case 2:
				DatabaseConnection.visualizzaLibri();
				break;
			case 3:
				modificaLibro();
				break;
			case 4:
				cancellaLibro();
				break;
			case 5:
				return;
			default:
				System.out.println("Opzione non valida. Riprova.");
			}
		}
	}

	private static void aggiungiLibro() {
		
		// fa visualizzare la lista degli autori (se presenti) per prendere l'id,altrimenti torna al sottomenu
		System.out.println("Conosci l'ID dell'autore? (s/n): ");
		String rispostaIdAutore = scanner.nextLine();

		if (rispostaIdAutore.equalsIgnoreCase("n")) {
			boolean autoriPresenti = DatabaseConnection.visualizzaAutori();
			if (!autoriPresenti) {
				return; // Ritorna al menu se non ci sono autori
			}
		}

		System.out.println("Inserisci l'ID dell'autore: ");
		int idAutore = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Inserisci il titolo del libro: ");
		String titolo = scanner.nextLine();

		System.out.println("Inserisci l'anno di pubblicazione: ");
		int anno = scanner.nextInt();
		scanner.nextLine();

		DatabaseConnection.aggiungiLibro(titolo, idAutore, anno);
		
		System.out.println("Libro aggiunto con successo.");
	}

	private static void modificaLibro() {
		
		// fa visualizzare la lista dei libri (se presenti) per prendere l'id, altrimenti torna al sottomenu 
		System.out.println("Conosci l'ID del libro? (s/n): ");
		String rispostaIdLibro = scanner.nextLine();

		if (rispostaIdLibro.equalsIgnoreCase("n")) {
			boolean libriPresenti = DatabaseConnection.visualizzaLibri();
			if (!libriPresenti) {
				return; // Ritorna al menu se non ci sono autori
			}
		}

		System.out.println("Inserisci l'ID del libro da modificare: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Inserisci il nuovo titolo del libro: ");
		String titolo = scanner.nextLine();

		System.out.println("Conosci l'ID dell'autore? (s/n): ");
		String rispostaIdAutore = scanner.nextLine();

		// fa visualizzare la lista degli autori (se presenti) per prendere l'id, altrimenti torna al sottomenu
		if (rispostaIdAutore.equalsIgnoreCase("n")) {
			boolean autoriPresenti = DatabaseConnection.visualizzaAutori();
			if (!autoriPresenti) {
				return; // Ritorna al menu se non ci sono autori
			}
		}

		System.out.println("Inserisci il nuovo ID dell'autore: ");
		int idAutore = scanner.nextInt();

		System.out.println("Inserisci il nuovo anno di pubblicazione: ");
		int anno = scanner.nextInt();
		scanner.nextLine();

		DatabaseConnection.modificaLibro(id, titolo, idAutore, anno);
		
		System.out.println("Libro modificato con successo.");
	}

	private static void cancellaLibro() {

		// fa visualizzare la lista dei libri (se presenti) per prendere l'id, altrimenti torna al sottomenu
		System.out.println("Conosci l'ID del libro? (s/n): ");
		String rispostaIdLibro = scanner.nextLine();

		if (rispostaIdLibro.equalsIgnoreCase("n")) {
			boolean libriPresenti = DatabaseConnection.visualizzaLibri();
			if (!libriPresenti) {
				return; // Ritorna al menu se non ci sono autori
			}
		}

		System.out.println("Inserisci l'ID del libro da cancellare: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		DatabaseConnection.cancellaLibro(id);
		
		System.out.println("Libro cancellato con successo.");
	}

	// gestione Autori
	private static void gestioneAutori() {
		
		while (true) {
			System.out.println("\n=== Gestione Autori ===");
			System.out.println("1. Aggiungi un nuovo autore");
			System.out.println("2. Visualizza tutti gli autori");
			System.out.println("3. Modifica un autore");
			System.out.println("4. Cancella un autore");
			System.out.println("5. Torna al menu principale");
			System.out.println("Seleziona un'opzione: ");
			
			int scelta = scanner.nextInt();
			scanner.nextLine();

			switch (scelta) {
			case 1:
				aggiungiAutore();
				break;
			case 2:
				DatabaseConnection.visualizzaAutori();
				break;
			case 3:
				modificaAutore();
				break;
			case 4:
				cancellaAutore();
				break;
			case 5:
				return;
			default:
				System.out.println("Opzione non valida. Riprova.");
			}
		}
	}

	private static void aggiungiAutore() {
		
		System.out.println("Inserisci il nome dell'autore: ");
		String nome = scanner.nextLine();

		System.out.println("Inserisci il cognome dell'autore: ");
		String cognome = scanner.nextLine();

		DatabaseConnection.aggiungiAutore(nome, cognome);
		
		System.out.println("Autore aggiunto con successo.");
	}

	private static void modificaAutore() {

		// fa visualizzare la lista degli autori (se presenti) per prendere l'id, altrimenti torna al sottomenu
		System.out.println("Conosci l'ID dell'autore? (s/n): ");
		String rispostaIdAutore = scanner.nextLine();

		if (rispostaIdAutore.equalsIgnoreCase("n")) {
			boolean autoriPresenti = DatabaseConnection.visualizzaAutori();
			if (!autoriPresenti) {
				return; // Ritorna al menu se non ci sono autori
			}
		}

		System.out.println("Inserisci l'ID dell'autore da modificare: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Inserisci il nuovo nome dell'autore: ");
		String nome = scanner.nextLine();

		System.out.println("Inserisci il nuovo cognome dell'autore: ");
		String cognome = scanner.nextLine();

		DatabaseConnection.modificaAutore(id, nome, cognome);
		
		System.out.println("Autore modificato con successo.");
	}

	private static void cancellaAutore() {

		// fa visualizzare la lista degli autori (se presenti) per prendere l'id, altrimenti torna al sottomenu
		System.out.println("Conosci l'ID dell'autore? (s/n): ");
		String rispostaIdAutore = scanner.nextLine();

		if (rispostaIdAutore.equalsIgnoreCase("n")) {
			boolean autoriPresenti = DatabaseConnection.visualizzaAutori();
			if (!autoriPresenti) {
				return; // Ritorna al menu se non ci sono autori
			}
		}

		System.out.println("Inserisci l'ID dell'autore da cancellare: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		DatabaseConnection.cancellaAutore(id);
		
		System.out.println("Autore cancellato con successo.");
	}

	// ricerca avanzata Libri
	private static void ricercaAvanzataLibri() {
		
		System.out.println("\n=== Ricerca Avanzata Libri ===");
		System.out.println("1. Cerca per Titolo");
		System.out.println("2. Cerca per Autore");
		System.out.println("3. Cerca per Anno di Pubblicazione");
		System.out.println("4. Torna al menu principale");
		System.out.println("Selezione un'opzione: ");

		int scelta = scanner.nextInt();
		scanner.nextLine();

		switch (scelta) {
		case 1:
			cercaPerTitolo();
			break;
		case 2:
			cercaPerAutore();
			break;
		case 3:
			cercaPerAnno();
			break;
		case 4:
			return;
		default:
			System.out.println("Opzione non valida. Riprova.");
		}
	}

	private static void cercaPerTitolo() {
		
		System.out.println("Inserisci il titolo del libro da cercare: ");
		String titoloCercato = scanner.nextLine();
		
		DatabaseConnection.cercaLibroPerTitolo(titoloCercato);
	}

	private static void cercaPerAnno() {
		
		System.out.println("Inserisci un range di  due anni per la ricerca , uno di inizio e uno di fine.");
		System.out.println("Inserisci l'anno di inizio: ");
		int annoInizio = scanner.nextInt();
		System.out.println("Inserisci l'anno di fine: ");
		int annoFine = scanner.nextInt();
		scanner.nextLine();

		DatabaseConnection.cercaLibroPerAnno(annoInizio, annoFine);
	}
	
	private static void cercaPerAutore() {
		
		System.out.println("Inserisci il nome o il cognome dell'autore del libro da cercare:");
		String nomeCognomeAutore = scanner.nextLine();
		
		DatabaseConnection.cercaLibroPerAutore(nomeCognomeAutore, nomeCognomeAutore);
	}

}
