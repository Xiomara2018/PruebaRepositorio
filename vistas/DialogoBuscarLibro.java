package vistas;

import controladores.GestorBiblioteca;
import estructuras.ExceptionIsEmpty;
import estructuras.ItemNotfound;
import java.awt.*;
import javax.swing.*;
import modelos.Libro;
public class DialogoBuscarLibro extends JDialog{
    private JTextField txtCodigoBuscar;
    private JTextArea txtResultado;
public DialogoBuscarLibro(JFrame ventanaPadre, GestorBiblioteca gestor) {
        super(ventanaPadre, "Buscar Libro por Código", true);
        setSize(400, 300);
        setLocationRelativeTo(ventanaPadre);
        setLayout(new BorderLayout(10, 10));
    JPanel panelBusqueda = new JPanel(new FlowLayout());
    panelBusqueda.add(new JLabel("Ingrese el Código del Libro:"));
    txtCodigoBuscar = new JTextField(10); 
    panelBusqueda.add(txtCodigoBuscar);   
    JButton btnBuscar = new JButton("Buscar");
    panelBusqueda.add(btnBuscar);
    add(panelBusqueda, BorderLayout.NORTH);
    txtResultado = new JTextArea();
    txtResultado.setEditable(false); 
    txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));
    txtResultado.setBorder(BorderFactory.createTitledBorder("Resultado de la Búsqueda"));
    add(new JScrollPane(txtResultado), BorderLayout.CENTER);
    JPanel panelInferior = new JPanel();
    JButton btnCerrar = new JButton("Cerrar");
    btnCerrar.addActionListener(e -> dispose());
    panelInferior.add(btnCerrar);
    add(panelInferior, BorderLayout.SOUTH);
    btnBuscar.addActionListener(e -> {
        try {
                int codigo = Integer.parseInt(txtCodigoBuscar.getText().trim());
                
                Libro libroEncontrado = gestor.buscarPorCodigo(codigo);
                
                String info = """
                              \u00a1Libro Encontrado!
                              
                            C\u00f3digo:    """ + libroEncontrado.getCodigo() + "\n"
                            + "Título:    " + libroEncontrado.getTitulo() + "\n"
                            + "Autor:     " + libroEncontrado.getAutor() + "\n"
                            + "Categoría: " + libroEncontrado.getCategoria() + "\n"
                            + "Año:       " + libroEncontrado.getAnio() + "\n"
                            + "Estado:    " + libroEncontrado.getEstado().toString();
                            
                txtResultado.setText(info); 

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                txtResultado.setText(""); 
            } catch (ItemNotfound ex) {
                JOptionPane.showMessageDialog(this, "No existe ningún libro con ese código.", "No Encontrado", JOptionPane.WARNING_MESSAGE);
                txtResultado.setText(""); 
            } catch (ExceptionIsEmpty ex) {
                JOptionPane.showMessageDialog(this, "Error interno con los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
