package ucnmusic.services;

import ucnmusic.models.Cancion;
import ucnmusic.models.Genero;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/*
 *Clase encargada de leer y escribir el archivo con las cancniones
 */
public class LectorArchivos {
   /*
   *Carga las canciones desde un archivo CSV
   * @param rutaCsv ruta del archivo CSV
    */
    public List<Cancion> cargarCanciones(String rutaCsv) throws Exception{
        List<String> lineas = Files.readAllLines(Paths.get(rutaCsv));
        List<Cancion> listaCanciones = new ArrayList<>();

        for (int i = 1; i < lineas.size(); i++) {
            String[] p = lineas.get(i).split(",");
            Genero genero = Genero.fromString(p[3]);

            listaCanciones.add(new Cancion(p[0], p[1], p[2], genero, Integer.parseInt(p[4])));
        }
        return listaCanciones;
    }
    /*
    *Guarda las canciones en el archivo CSV
     */
    public void guardarCanciones(String rutaCsv, List<Cancion> canciones) throws Exception{
        List<String> out = new ArrayList<>();

        out.add("nombre,artista,artista2,genero,likes");

        for(Cancion c: canciones){
            out.add(c.toCSVLine());
        }
        Files.write(Paths.get(rutaCsv), out);
    }
}
