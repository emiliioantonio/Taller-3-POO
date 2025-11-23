package ucnmusic;

import ucnmusic.services.SistemaImpl;
/*
*Clase principal del programa.
* Inicia el menu del sistema musical
 */
public class Main {
    public static void main(String[] args) {
        SistemaImpl sistema = new SistemaImpl();
        sistema.menu();

    }
}