package ucnmusic.models;

public class Cancion {
    private String nombre;
    private String artista;
    private String artista2;
    private Genero genero;
    private int likes;

    public Cancion(String nombre, String artista, String artista2, Genero genero, int likes) {
        this.nombre = nombre;
        this.artista = artista;
        this.artista2 = artista2;
        this.genero = genero;
        this.likes = likes;
    }
    public String getNombre() {
        return nombre;
    }

    public String getArtista() {
        return artista;
    }

    public String getArtista2() {
        return artista2;
    }
    public Genero getGenero() {
        return genero;
    }
    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }
    public String toString(){
        return nombre + " - " + artista + " (" + genero + ") [" + likes + "likes]";
    }

    public String toCSVLine(){
        return nombre + "," + artista + "," + artista2 + "," + genero + "," + likes;
    }

}
