package vistas;

import controladores.GestorBiblioteca;
import estructuras.ExceptionIsEmpty;
import estructuras.ItemNotfound;
import javax.swing.*;
import java.awt.*;

public class DialogoModificarLibro extends JDialog {

    public DialogoModificarLibro(JFrame ventanaPadre, GestorBiblioteca gestor) {
        super(ventanaPadre, "Modificar Libro", true);
        setSize(350, 300);
        setLocationRelativeTo(ventanaPadre);
        setLayout(new BorderLayout(10, 10));

        JPanel panelNorte = new JPanel(new FlowLayout());
        panelNorte.add(new JLabel("Código del libro a modificar:"));
        JTextField txtCodigo = new JTextField(8);
        panelNorte.add(txtCodigo);
        add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        JTextField txtNuevoTitulo = new JTextField();
        JTextField txtNuevoAutor = new JTextField();
        JTextField txtNuevaCategoria = new JTextField();
        JTextField txtNuevoAnio = new JTextField();

        panelCentro.add(new JLabel("Nuevo Título:"));
        panelCentro.add(txtNuevoTitulo);
        panelCentro.add(new JLabel("Nuevo Autor:"));
        panelCentro.add(txtNuevoAutor);
        panelCentro.add(new JLabel("Nueva Categoría:"));
        panelCentro.add(txtNuevaCategoria);
        panelCentro.add(new JLabel("Nuevo Año (0 = no cambiar):"));
        panelCentro.add(txtNuevoAnio);
        txtNuevoAnio.setText("0"); // Por defecto en 0 para que no obligue a cambiarlo

        add(panelCentro, BorderLayout.CENTER);

        JPanel panelSur = new JPanel();
        JButton btnGuardar = new JButton("Guardar Cambios");
        JButton btnCancelar = new JButton("Cancelar");
        panelSur.add(btnGuardar);
        panelSur.add(btnCancelar);
        add(panelSur, BorderLayout.SOUTH);

        btnCancelar.addActionListener(e -> dispose());

        btnGuardar.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText().trim());
                String titulo = txtNuevoTitulo.getText().trim();
                String autor = txtNuevoAutor.getText().trim();
                String categoria = txtNuevaCategoria.getText().trim();
                int anio = Integer.parseInt(txtNuevoAnio.getText().trim());

                gestor.modificarLibro(codigo, titulo, autor, categoria, anio);
                
                JOptionPane.showMessageDialog(this, "Libro modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El código y el año deben ser números.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (ItemNotfound ex) {
                JOptionPane.showMessageDialog(this, "No se encontró ningún libro con ese código.", "No Encontrado", JOptionPane.WARNING_MESSAGE);
            } catch (ExceptionIsEmpty ex) {
                JOptionPane.showMessageDialog(this, "Error de validación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}