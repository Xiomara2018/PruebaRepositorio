package vistas;

import controladores.GestorBiblioteca;
import java.awt.*;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    
    private GestorBiblioteca gestor;

    public VentanaPrincipal() {
        gestor = new GestorBiblioteca();
        setTitle("QuickLibrary - Sistema de Gestión");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN QUICKLIBRARY", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0)); // Márgenes
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(6, 2, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JButton btn1 = new JButton("1. Registrar libro");
        JButton btn2 = new JButton("2. Mostrar libros");
        JButton btn3 = new JButton("3. Buscar libro por código");
        JButton btn4 = new JButton("4. Buscar libros por categoría");
        JButton btn5 = new JButton("5. Modificar libro");
        JButton btn6 = new JButton("6. Eliminar libro");
        JButton btn7 = new JButton("7. Registrar solicitud de préstamo");
        JButton btn8 = new JButton("8. Mostrar cola de solicitudes");
        JButton btn9 = new JButton("9. Atender siguiente solicitud");
        JButton btn10 = new JButton("10. Registrar devolución");
        JButton btn11 = new JButton("11. Mostrar reporte");
        JButton btn12 = new JButton("12. Salir");

        panelBotones.add(btn1);
        panelBotones.add(btn7);
        panelBotones.add(btn2);
        panelBotones.add(btn8);
        panelBotones.add(btn3);
        panelBotones.add(btn9);
        panelBotones.add(btn4);
        panelBotones.add(btn10);
        panelBotones.add(btn5);
        panelBotones.add(btn11);
        panelBotones.add(btn6);
        panelBotones.add(btn12);

        add(panelBotones, BorderLayout.CENTER);


        btn12.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });


        btn1.addActionListener(e -> {
            DialogoRegistrarLibro dialogo = new DialogoRegistrarLibro(this, gestor);
            dialogo.setVisible(true);
        });
        
        btn2.addActionListener(e -> {
            DialogoMostrarLibros dialogo = new DialogoMostrarLibros(this, gestor);
            dialogo.setVisible(true);
        });

        btn3.addActionListener(e -> {
            DialogoBuscarLibro dialogo = new DialogoBuscarLibro(this, gestor);
            dialogo.setVisible(true);
        });


        btn5.addActionListener(e -> {
            DialogoModificarLibro dialogo = new DialogoModificarLibro(this, gestor);
            dialogo.setVisible(true);
        });

        btn6.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, 
                "Ingrese el código del libro a eliminar:", 
                "Eliminar Libro", 
                JOptionPane.WARNING_MESSAGE);
                        
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int codigo = Integer.parseInt(input.trim());
                    gestor.eliminarLibro(codigo);
                    JOptionPane.showMessageDialog(this, "Libro eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El código debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (estructuras.ItemNotfound ex) {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún libro con ese código.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (estructuras.ExceptionIsEmpty ex) {
                    JOptionPane.showMessageDialog(this, "Error interno con los datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btn7.addActionListener(e -> {
            DialogoRegistrarSolicitud dialogo = new DialogoRegistrarSolicitud(this, gestor);
            dialogo.setVisible(true);
        });
        
        btn8.addActionListener(e -> {
            String textoCola = gestor.getColaSolicitudes().mostrar();
            JOptionPane.showMessageDialog(this, textoCola, "Cola de Espera", JOptionPane.INFORMATION_MESSAGE);
        });
        
        btn9.addActionListener(e -> {
            String resultado = gestor.procesarPrestamo();
            JOptionPane.showMessageDialog(this, resultado, "Atención de Solicitud", JOptionPane.INFORMATION_MESSAGE);
        });

        btn10.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, 
                "Ingrese el código del libro que desea devolver:", 
                "Registrar Devolución", 
                JOptionPane.QUESTION_MESSAGE);
                        
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int codigo = Integer.parseInt(input.trim());
                    gestor.procesarDevolucion(codigo);
                            
                    JOptionPane.showMessageDialog(this, 
                        "Se intentó procesar la devolución.\n(Revisa la consola de VS Code para ver si fue exitosa o si hubo error)", 
                        "Proceso Terminado", 
                        JOptionPane.INFORMATION_MESSAGE);
                                            
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El código debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btn11.addActionListener(e -> {
            java.util.List<modelos.Libro> todosLosLibros = gestor.mostrarTodosLosLibros();
            int totales = 0, disponibles = 0, prestados = 0;
            if (todosLosLibros != null) {
                totales = todosLosLibros.size();
                for (modelos.Libro libro : todosLosLibros) {
                    if (libro.getEstado().toString().equals("Disponible")) {
                        disponibles++;
                    } else {
                        prestados++;
                    }
                }
            }
                        
            int solicitudes = gestor.getColaSolicitudes().size();

            String textoReporte = """
                                  === REPORTE B\u00c1SICO DE BIBLIOTECA ===
                                  
                                  Total de libros registrados: """ + totales + "\n"
                + "Libros disponibles: " + disponibles + "\n"
                + "Libros prestados: " + prestados + "\n"
                + "Solicitudes pendientes en cola: " + solicitudes + "\n\n"
                + "====================================";
                                                
            JOptionPane.showMessageDialog(this, textoReporte, "Reporte General", JOptionPane.INFORMATION_MESSAGE);
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
} 