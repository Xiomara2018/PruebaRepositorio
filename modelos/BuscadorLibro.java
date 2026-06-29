package ....;

import java.util.ArrayList;
import java.util.List;
import modelos.Libro;

public class BuscadorLibros {

    public List<Libro> buscarPorTitulo(List<Libro> listaLibros, String tituloBuscado) {
        List<Libro> resultado = new ArrayList<>();

        if (listaLibros == null) {
            return resultado;
        }

        if (tituloBuscado == null) {
            return resultado;
        }

        if (tituloBuscado.trim().isEmpty()) {
            return resultado;
        }

        tituloBuscado = tituloBuscado.trim().toLowerCase();

        for (Libro libro : listaLibros) {
            if (libro.getTitulo().toLowerCase().contains(tituloBuscado)) {
                resultado.add(libro);
            }
        }

        return resultado;
    }

    public List<Libro> buscarPorCategoria(List<Libro> listaLibros, String categoriaBuscada) {
        List<Libro> resultado = new ArrayList<>();

        if (listaLibros == null) {
            return resultado;
        }

        if (categoriaBuscada == null) {
            return resultado;
        }

        if (categoriaBuscada.trim().isEmpty()) {
            return resultado;
        }

        String textoBuscado = categoriaBuscada.trim().toLowerCase();

        for (int i = 0; i < listaLibros.size(); i++) {
            Libro libroActual = listaLibros.get(i);
            String categoriaActual = libroActual.getCategoria().toLowerCase();

            if (categoriaActual.equals(textoBuscado)) {
                resultado.add(libroActual);
            }
        }

        return resultado;
    }
}
