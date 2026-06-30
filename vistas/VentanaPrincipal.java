package vistas;

import controladores.GestorBiblioteca;
import javax.swing.*;
import java.awt.*;
public class VentanaPrincipal extends JFrame
 {private GestorBiblioteca gestor;

public VentanaPrincipal() {
    gestor = new GestorBiblioteca();
    setTitle("QuickLibrary - Sistema de Gestión");
    setSize(400, 350);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new GridLayout(5, 1, 10, 10));

    JButton btnRegistrarLibro = new JButton("1. Registrar Libro");
        JButton btnMostrarLibros = new JButton("2. Mostrar Todos los Libros");
        JButton btnRegistrarSolicitud = new JButton("7. Registrar Solicitud de Préstamo");

}
