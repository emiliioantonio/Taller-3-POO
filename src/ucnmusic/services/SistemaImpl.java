package ucnmusic.services;

import ucnmusic.models.Cancion;
import ucnmusic.models.Genero;

import java.util.*;
import java.util.stream.Collectors;
/*
*Implementacion del sistema musical.
* Gestiona canciones, rankings, recomendaciones y archivo CSV.
 */
public class SistemaImpl implements Sistema {
    private static final String rutaCsv = "C:\\Users\\g4m3r\\IdeaProjects\\Taller 3\\src\\canciones.csv";

    private final List<Cancion> canciones;
    private final Scanner sc;
    private final LectorArchivos lector;
    /*
    *Constructor que inicializaba las listas y utilidades
     */
    public SistemaImpl() {
        this.canciones = new ArrayList<>();
        this.sc = new Scanner(System.in);
        this.lector = new LectorArchivos();
    }

    @Override
    public void menu() {
        cargarCanciones();

        boolean continuar = true;

        while (continuar) {
            System.out.println("---- UCN MUSIC 2.0 ----");
            System.out.println("1. Registrar cancion");
            System.out.println("2. Mostrar canciones por genero");
            System.out.println("3. Recomendar cancion de forma aleatoria");
            System.out.println("4. Dar like a una cancion");
            System.out.println("5. Canciones mas populares.");
            System.out.println("6. Generos mas populares");
            System.out.println("7. Artistas mas populares");
            System.out.println("8. Guardar y salir.");

            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    registrarCancion();
                    break;
                case "2":
                    mostrarPorGenero();
                    break;
                case "3":
                    recomendarCancion();
                    break;
                case "4":
                    darlike();
                    break;
                case "5":
                    rankingCanciones();
                    break;
                case "6":
                    rankingGeneros();
                    break;
                case "7":
                    rankingArtistas();
                    break;
                case "8":{
                    guardarCanciones();
                    continuar = false;
                    break;

                }
                default:
                    System.out.println("Opcion invalida");

            }
            System.out.println();
        }
    }

    @Override
    public void cargarCanciones() {
        try{
            List<Cancion> cargadas = lector.cargarCanciones(rutaCsv);
            canciones.addAll(cargadas);

        }
        catch(Exception e){
            System.out.println("No se pudieron cargar las canciones");
        }

    }

    @Override
    public void guardarCanciones() {
        try{
            lector.guardarCanciones(rutaCsv, canciones);
            System.out.println("Las canciones fueron guardadas correctamente");

        }
        catch(Exception e){
            System.out.println("Error al guardar");
        }

    }

    @Override
    public void mostrarPorGenero() {
        System.out.println("Ingrese genero: ");
        String generoStr = sc.nextLine().trim();

        Genero genero = Genero.fromString(generoStr);

        canciones.stream().filter(c -> c.getGenero() == genero).forEach(c -> System.out.println(c));

    }

    @Override
    public void registrarCancion() {
        System.out.println("Nombre: ");
        String nombre = sc.nextLine().trim();

        System.out.println("Artista: ");
        String artista = sc.nextLine().trim();

        System.out.println("Artista colaborador (opcional, presiona ENTER para omitir): ");
        String artista2 = sc.nextLine().trim();
        if(artista2.isEmpty()){
            artista2 = "";
        }

        System.out.println("Genero (ROCK, POP, RAP, METAL, REGGAETON, TRAP, ELECTRONICA, OTRO): ");
        String generoStr = sc.nextLine().trim();

        Genero genero = Genero.fromString(generoStr);

        Cancion c = new Cancion(nombre, artista, artista2, genero, 0);
        canciones.add(c);

        System.out.println("Cancion registrada correctamente");


    }

    @Override
    public void recomendarCancion() {
        if(canciones.isEmpty()){
            System.out.println("No hay canciones");
            return;
        }
        Random r = new Random();
        Cancion c = canciones.get(r.nextInt(canciones.size()));

        System.out.println("Recomendacion: " + c);


    }

    @Override
    public void darlike() {
        System.out.println("Ingrese nombre o artista: ");
        String busca = sc.nextLine().toLowerCase();

        List<Cancion> resultados = canciones.stream().filter(c -> c.getNombre().toLowerCase().contains(busca) || c.getArtista().toLowerCase().contains(busca) || c.getArtista2().toLowerCase().contains(busca)).toList();
        if(resultados.isEmpty()){
            System.out.println("No se encontraron canciones con ese criterio.");
            return;
        }
        System.out.println("Seleccione una cancion para dar like: ");
        for(int i = 0; i < resultados.size(); i++){
            System.out.println((i + 1) + ". " + resultados.get(i));
        }
        System.out.println("Opcion (0 para cancelar)");
        String opcionStr = sc.nextLine();

        int opcion;
        try {
            opcion = Integer.parseInt(opcionStr);
        }
        catch(NumberFormatException e) {
            System.out.println("Opcion invalida");
            return;
        }
        if (opcion == 0){
            System.out.println("Operacion cancelada");
            return;
        }
        if (opcion < 1 || opcion > resultados.size()){
            System.out.println("Opcion fuera de rango");
            return;
        }
        Cancion elegida = resultados.get(opcion - 1);

        elegida.setLikes(elegida.getLikes() + 1);
        System.out.println("Se ha dado like a: ");
        System.out.println(elegida);
    }

    @Override
    public void rankingCanciones() {
        canciones.stream().sorted((a,b) -> b.getLikes() - a.getLikes()).limit(5).forEach(c -> System.out.println(c));

    }

    @Override
    public void rankingGeneros() {
        Map<Genero, Integer> mapa = new HashMap<>();

        for(Cancion c : canciones){
            mapa.put(c.getGenero(), mapa.getOrDefault(c.getGenero(), 0) + c.getLikes());
        }

        mapa.entrySet().stream().sorted((a,b) -> b.getValue() - a.getValue()).forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue() + " likes"));


    }

    @Override
    public void rankingArtistas() {
        Map<String, Integer> mapa = new HashMap<>();
        for(Cancion c : canciones){
            mapa.put(c.getArtista(), mapa.getOrDefault(c.getArtista(), 0) + c.getLikes());
        }
        mapa.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));

    }
}
