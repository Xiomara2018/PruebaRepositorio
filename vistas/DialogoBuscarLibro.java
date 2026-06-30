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
}
