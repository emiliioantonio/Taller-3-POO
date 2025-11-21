package ucnmusic.models;

public enum Genero {
    ROCK,
    POP,
    RAP,
    REGGAETON,
    ELECTRONICA,
    METAL,
    TRAP,
    OTRO;

    public static Genero fromString(String texto){
        try{
            return Genero.valueOf(texto.toUpperCase());
        }
        catch(Exception e){
            return OTRO;
        }
    }
}
