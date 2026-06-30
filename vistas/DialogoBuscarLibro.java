package vistas;

import controladores.GestorBiblioteca;
import estructuras.ExceptionIsEmpty;
import estructuras.ItemNotfound;
import modelos.Libro;
import javax.swing.*;
import java.awt.*;
public class DialogoBuscarLibro extends JDialog{
    private JTextField txtCodigoBuscar;
    private JTextArea txtResultado;
public DialogoBuscarLibro(JFrame ventanaPadre, GestorBiblioteca gestor) {
        super(ventanaPadre, "Buscar Libro por Código", true);
        setSize(400, 300);
        setLocationRelativeTo(ventanaPadre);
        setLayout(new BorderLayout(10, 10));
    
}
