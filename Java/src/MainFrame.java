
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import java.awt.event.*;
import java.awt.*;
 
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.util.List;
import java.util.ArrayList;
public class MainFrame extends JFrame implements ActionListener {
    
	JDesktopPane desktop;
 
    public MainFrame() {
        super("InternalFrameDemo");
 
        //Make the big window be indented 50 pixels from each edge
        //of the screen.
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                  screenSize.width  - inset*2,
                  screenSize.height - inset*2);
 
        //Set up the GUI.
        desktop = new JDesktopPane(); //a specialized layered pane
        setContentPane(desktop);
        setJMenuBar(createMenuBar());
        
        //Make dragging a little faster but perhaps uglier.
        desktop.setBackground(SystemColor.activeCaption);
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }
 
    private JMenuBar createMenuBar() {
        
    	JMenuBar menuBar = new JMenuBar();
    	menuBar.setBorderPainted(false);
        
    	//Set up the lone menu.
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);
 
        //Set up the first menu item.
        JMenuItem menuItem = new JMenuItem("Nueva Ventana");
        menuItem.setActionCommand("new");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Set up the second menu item 
        JMenuItem menuItemAgregar  = new JMenuItem("Agregar datos");
        menuItemAgregar.setActionCommand("agregar");
        menuItemAgregar.addActionListener(this);
        menu.add(menuItemAgregar);
        
        //Set up the third menu item.
        menuItem = new JMenuItem("Cerrar");
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(this);
        menu.add(menuItem);
 
        return menuBar;
    }
 
    //React to menu selections.
    public void actionPerformed(ActionEvent e) {
        if ("new".equals(e.getActionCommand())) {
            createFrame();
        } else if ("agregar".equals(e.getActionCommand())) { // add data
            abrirFormularioAgregar();                        
        } else { //quit
            quit();
        }
    }
    
    //Create a new internal frame.
    private void createFrame() {
        InternalFrame frame = new InternalFrame();
        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
        frame.setBackground(Color.white);
        frame.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                InternalFrame.openFrameCount--;
            }
        });
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
    
    
    private void abrirFormularioAgregar() {
        InternalFrame frame = new InternalFrame();
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(SystemColor.activeCaption);
        frame.setContentPane(panel);

        JLabel tablaLabel = new JLabel("Tabla:");
        tablaLabel.setBounds(50, 20, 100, 30);
        tablaLabel.setFont(new Font("Consolas", Font.BOLD, 15));
        panel.add(tablaLabel);

        JTextField tablaField = new JTextField();
        tablaField.setBounds(160, 20, 200, 30);
        panel.add(tablaField);

        JButton cargarButton = new JButton("Cargar");
        cargarButton.setBounds(375, 20, 100, 30);
        panel.add(cargarButton);

        JPanel camposPanel = new JPanel();
        camposPanel.setLayout(null);
        camposPanel.setBackground(SystemColor.activeCaption);
        camposPanel.setBounds(0, 70, 550, 280);
        panel.add(camposPanel);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.setBounds(120, 380, 120, 35);
        agregarButton.setEnabled(false);
        panel.add(agregarButton);

        JButton limpiarButton = new JButton("Limpiar");
        limpiarButton.setBounds(290, 380, 120, 35);
        limpiarButton.setEnabled(false);
        panel.add(limpiarButton);

        List<String> columnas = new ArrayList<>();
        List<JTextField> camposTexto = new ArrayList<>();

        cargarButton.addActionListener(e -> {
            String tabla = tablaField.getText().trim();
            if (tabla.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Escribe el nombre de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            List<String> cols = Database.obtenerColumnas(tabla);
            if (cols == null || cols.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "La tabla no existe o no hay conexión.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            camposPanel.removeAll();
            columnas.clear();
            camposTexto.clear();
            columnas.addAll(cols);
            int y = 10;
            for (String col : cols) {
                JLabel lbl = new JLabel(col + ":");
                lbl.setBounds(50, y, 150, 25);
                lbl.setFont(new Font("Consolas", Font.BOLD, 14));
                camposPanel.add(lbl);
                JTextField campo = new JTextField();
                campo.setBounds(210, y, 250, 25);
                camposPanel.add(campo);
                camposTexto.add(campo);
                y += 40;
            }
            agregarButton.setEnabled(true);
            limpiarButton.setEnabled(true);
            camposPanel.revalidate();
            camposPanel.repaint();
        });

        agregarButton.addActionListener(e -> {
            List<String> valores = new ArrayList<>();
            for (JTextField campo : camposTexto) valores.add(campo.getText().trim());
            for (String valor : valores) {
                if (valor.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Todos los campos deben estar rellenos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if (Database.insertarDatos(tablaField.getText().trim(), columnas, valores)) {
                JOptionPane.showMessageDialog(panel, "Datos agregados correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                camposTexto.forEach(c -> c.setText(""));
            } else {
                JOptionPane.showMessageDialog(panel, "Error al insertar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        limpiarButton.addActionListener(e -> camposTexto.forEach(c -> c.setText("")));

        frame.setSize(550, 470);
        frame.setVisible(true);
        desktop.add(frame);
        frame.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                InternalFrame.openFrameCount--;
            }
        });
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
    	   
    
    
    
    //Quit the application.
    private void quit() {
        System.exit(0);
    }
 
    /**
     * Create the GUI and show it.
     */
    
    public static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);
 
        //Create and set up the window.
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
 
        //Display the window.
        frame.setUndecorated(true);
        frame.setVisible(true);
    }
}