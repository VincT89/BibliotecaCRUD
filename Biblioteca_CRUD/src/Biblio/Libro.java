package Biblio;

public class Libro {
    private int id;
    private String titolo;
    private int idAutore;
    private int anno;

    public Libro(int id, String titolo, int idAutore, int anno) {
        this.id = id;
        this.titolo = titolo;
        this.idAutore = idAutore;
        this.anno = anno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getIdAutore() {
        return idAutore;
    }

    public void setIdAutore(int idAutore) {
        this.idAutore = idAutore;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }
}
