package estructuras; 

import estructuras.Cola;
import estructuras.LinkedBST;
import modelos.Libro;
import modelos.Solicitud;

public class GestorBiblioteca {

    private Cola<Solicitud> colaSolicitudes;
    private LinkedBST<Libro> arbolLibros;

    public GestorBiblioteca() {
        this.colaSolicitudes = new Cola<>();
        this.arbolLibros = new LinkedBST<>();
    }

    public Cola<Solicitud> getColaSolicitudes() {
        return colaSolicitudes;
    }

    public LinkedBST<Libro> getArbolLibros() {
        return arbolLibros;
    }
}
