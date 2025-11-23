package ucnmusic.services;

import ucnmusic.models.Cancion;

import java.util.List;
/*
*Interfaz que define las operaciones
* principales del sistema musical
 */
public interface Sistema {
    void menu();

    void cargarCanciones();

    void guardarCanciones();

    void mostrarPorGenero();

    void registrarCancion();

    void recomendarCancion();

    void darlike();

    void rankingCanciones();

    void rankingGeneros();

    void rankingArtistas();
}
