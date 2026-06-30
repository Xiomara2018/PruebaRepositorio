package vistas;

import controladores.GestorBiblioteca;
import modelos.Libro;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DialogoMostrarLibros extends JDialog {
    public DialogoMostrarLibros(JFrame ventanaPadre, GestorBiblioteca gestor) {

        super(ventanaPadre, "Catálogo de Libros", true);
        setSize(700, 400); 
        setLocationRelativeTo(ventanaPadre);
        setLayout(new BorderLayout(10, 10));

        String[] nombresColumnas = {"Código", "Título", "Autor", "Categoría", "Año", "Estado"};
        
        DefaultTableModel modeloTabla = new DefaultTableModel(nombresColumnas, 0);

        JTable tablaLibros = new JTable(modeloTabla);
        

        List<Libro> lista = gestor.mostrarTodosLosLibros();
        
        if (lista != null && !lista.isEmpty()) {

            for (Libro libro : lista) {

                Object[] fila = {
                    libro.getCodigo(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getCategoria(),
                    libro.getAnio(),
                    libro.getEstado().toString() 
                };

                modeloTabla.addRow(fila);
            }
        } else {

            modeloTabla.addRow(new Object[]{"-", "No hay libros registrados", "-", "-", "-", "-"});
        }

        JScrollPane scrollPane = new JScrollPane(tablaLibros);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        JButton btnCerrar = new JButton("Cerrar Catálogo");
        btnCerrar.addActionListener(e -> dispose()); 
        panelInferior.add(btnCerrar);
        
        add(panelInferior, BorderLayout.SOUTH);
    }
}

