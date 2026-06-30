package vistas;

import controladores.GestorBiblioteca;
import estructuras.ExceptionIsEmpty;
import javax.swing.*;
import java.awt.*;

public class DialogoRegistrarSolicitud extends JDialog {

    private JTextField txtCodigoEstudiante;
    private JTextField txtNombreEstudiante;
    private JTextField txtCodigoLibro;
    private JTextField txtFecha;

    public DialogoRegistrarSolicitud(JFrame ventanaPadre, GestorBiblioteca gestor) {
        super(ventanaPadre, "Registrar Solicitud de Préstamo", true);
        setSize(400, 300);
        setLocationRelativeTo(ventanaPadre);
        setLayout(new BorderLayout(10, 10));
