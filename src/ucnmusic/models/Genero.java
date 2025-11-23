package ucnmusic.models;

/*
*Enum que representa los generos musicales disponibles
 */

public enum Genero {
    ROCK,
    POP,
    RAP,
    REGGAETON,
    ELECTRONICA,
    METAL,
    TRAP,
    OTRO;

    /*
    * Convierte texto en un valor del enum.
     */

    public static Genero fromString(String texto){
        try{
            return Genero.valueOf(texto.toUpperCase());
        }
        catch(Exception e){
            return OTRO;
        }
    }
}
