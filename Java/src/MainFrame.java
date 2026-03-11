import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.BorderFactory;import java.awt.event.*;
import java.awt.*;

public class MainFrame extends JFrame implements ActionListener {
public static String idioma = InicioFrame.getIdioma();
private JDesktopPane desktop;
JButton deleteButton;      // BOTÓN DEL RECUADRO
JPanel deletePanel;        // RECUADRO

    public MainFrame() {
 
    	
    	
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
     
        // Recuadro con botón de exportar
        createDeletePanel();
        
        //Make dragging a little faster but perhaps uglier.
        desktop.setBackground(SystemColor.activeCaption);
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        desktop.setLayout(null); // para colocar el recuadro por coordenadas
        setContentPane(desktop);

        // Barra de menú
        setJMenuBar(createMenuBar());

        // Recuadro con botón de exportar
        createExportPanel();
    }

    private JMenuBar createMenuBar() {
        
    	JMenuBar menuBar = new JMenuBar();
    	menuBar.setBorderPainted(false);
        
    	//Set up barra menu.
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);
        
 
        //Set up primer menu item.
        if (MainFrame.idioma.equals ("Español")) {
	        //Set up segundo menu item.
	        JMenuItem menuItem = new JMenuItem("Cerrar");
	        menuItem.setActionCommand("quit");
	        menuItem.addActionListener(this);
	        menu.add(menuItem);
	        menu.addSeparator();
	        
	        //Set up tercer menu item
	        JMenu menuAgregar = new JMenu("Agregar Datos");
	        menu.add(menuAgregar);
	        
	        JMenuItem menuItemSedes  = new JMenuItem("Tabla Sedes");
	        menuItemSedes.setActionCommand("sedes");
	        menuItemSedes.addActionListener(this);
	        menuAgregar.add(menuItemSedes);
	        
	        // ITEM: Borrar datos de sql
	        JMenuItem deleteItem = new JMenuItem("Borrar datos");
	        deleteItem.setActionCommand("delete");
	        deleteItem.addActionListener(this);
	        menu.add(deleteItem);
          
          // ITEM: EXPORTAR BD A CSV
        JMenuItem exportItem = new JMenuItem("Exportar BD a CSV");
        exportItem.setActionCommand("export");
        exportItem.addActionListener(this);
        menu.add(exportItem);
	        
        }
        else if (MainFrame.idioma.equals ("English")) {
                //Set up the second menu item.
                JMenuItem menuItem = new JMenuItem("Exit");
                menuItem.setActionCommand("quit");
                menuItem.addActionListener(this);
                menu.add(menuItem);	
                menu.addSeparator();
    	        
    	        //Set up tercer menu item
    	        JMenu menuAgregar = new JMenu("Add Data");
    	        menu.add(menuAgregar);
    	        
    	        JMenuItem menuItemSedes  = new JMenuItem("Headquarters Table");
    	        menuItemSedes.setActionCommand("sedes");
    	        menuItemSedes.addActionListener(this);
    	        menuAgregar.add(menuItemSedes);
    	        
    	        // ITEM: Borrar datos de sql
    	        JMenuItem deleteItem = new JMenuItem("Erase data");
    	        deleteItem.setActionCommand("delete");
    	        deleteItem.addActionListener(this);
    	        menu.add(deleteItem);
          
              // ITEM: EXPORTAR BD A CSV
              JMenuItem exportItem = new JMenuItem("Export DB to CSV");
              exportItem.setActionCommand("export");
              exportItem.addActionListener(this);
              menu.add(exportItem);
        }
        else if (MainFrame.idioma.equals ("Euskera")) {
            //Set up the second menu item.
            JMenuItem menuItem = new JMenuItem("Irten");
            menuItem.setActionCommand("quit");
            menuItem.addActionListener(this);
            menu.add(menuItem);
            menu.addSeparator();
	        
	        //Set up tercer menu item
	        JMenu menuAgregar = new JMenu("Datuak Sartu");
	        menu.add(menuAgregar);
	        
	        JMenuItem menuItemSedes  = new JMenuItem("Egoitzen Taula");
	        menuItemSedes.setActionCommand("sedes");
	        menuItemSedes.addActionListener(this);
	        menuAgregar.add(menuItemSedes);
	        
	        // ITEM: Borrar datos de sql
	        JMenuItem deleteItem = new JMenuItem("Datuak ezabatu");
	        deleteItem.setActionCommand("delete");
	        deleteItem.addActionListener(this);
	        menu.add(deleteItem);
          
          // ITEM: EXPORTAR BD A CSV
          JMenuItem exportItem = new JMenuItem("Esportatu DB CSVra");
          exportItem.setActionCommand("export");
          exportItem.addActionListener(this);
          menu.add(exportItem);
        }
        return menuBar;
    }
    
    
 // Recuadro con botón en la pantalla principal
    private void createDeletePanel() {
        deletePanel = new JPanel();
        deletePanel.setLayout(null);
        deletePanel.setBounds(500, 30, 260, 120); // posición y tamaño del recuadro
        deletePanel.setBackground(Color.WHITE);
        if (idioma.equals("Español")) {
        	deletePanel.setBorder(BorderFactory.createTitledBorder("Eliminar datos BD"));
        } else if (idioma.equals("English")) {
        	deletePanel.setBorder(BorderFactory.createTitledBorder("Erase data DB"));
        } else if (idioma.equals("Euskera")) {
        	deletePanel.setBorder(BorderFactory.createTitledBorder("Datuak ezabatu DB"));
        }
        
        JMenuItem menuItemAgregar  = new JMenuItem("Tabla Sedes");
        menuItemAgregar.setActionCommand("sedes");
        menuItemAgregar.addActionListener(this);
        menuAgregar.add(menuItemAgregar);
        
        menuItemAgregar  = new JMenuItem("Tabla Usuarios");
        menuItemAgregar.setActionCommand("usuarios");
        menuItemAgregar.addActionListener(this);
        menuAgregar.add(menuItemAgregar);
        
        menuItemAgregar  = new JMenuItem("Tabla Residuos");
        menuItemAgregar.setActionCommand("residuos");
        menuItemAgregar.addActionListener(this);
        menuAgregar.add(menuItemAgregar);
        
        menuItemAgregar  = new JMenuItem("Tabla Empleados");
        menuItemAgregar.setActionCommand("empleados");
        menuItemAgregar.addActionListener(this);
        menuAgregar.add(menuItemAgregar);
        
        menuItemAgregar  = new JMenuItem("Tabla Consumo Energia");
        menuItemAgregar.setActionCommand("energia");
        menuItemAgregar.addActionListener(this);
        menuAgregar.add(menuItemAgregar);
        
        menuItemAgregar  = new JMenuItem("Tabla Emisiones");
        menuItemAgregar.setActionCommand("emisiones");
        menuItemAgregar.addActionListener(this);
        menuAgregar.add(menuItemAgregar);
        
        menuItemAgregar  = new JMenuItem("Tabla Servidores");
        menuItemAgregar.setActionCommand("servidores");
        menuItemAgregar.addActionListener(this);
        menuAgregar.add(menuItemAgregar);
        
        menuItemAgregar  = new JMenuItem("Tabla Sistema Refrigeracio");
        menuItemAgregar.setActionCommand("refrigeracion");
        menuItemAgregar.addActionListener(this);
        menuAgregar.add(menuItemAgregar);
        
        deleteButton = new JButton("Eliminar datos BD");
        if (idioma.equals("Español")) {
        	deleteButton.setText("Eliminar datos BD");
        } else if (idioma.equals("English")) {
        	deleteButton.setText("Erase data DB");
        } else if (idioma.equals("Euskera")) {
        	deleteButton.setText("Datuak ezabatu DB");
        }
        deleteButton.setBounds(20, 40, 220, 40);
        deleteButton.setFont(new Font("Consolas", Font.BOLD, 14));
        deleteButton.setActionCommand("delete"); // reutilizar el mismo comando
        deleteButton.addActionListener(this);

        deletePanel.add(deleteButton);
        desktop.add(deletePanel);
    }
 
    // Recuadro con botón en la pantalla principal
    private void createExportPanel() {
        exportPanel = new JPanel();
        exportPanel.setLayout(null);
        exportPanel.setBounds(30, 30, 260, 120); // posición y tamaño del recuadro
        exportPanel.setBackground(Color.WHITE);
        if (InicioFrame.getIdioma().equals("Español")) {
          exportPanel.setBorder(BorderFactory.createTitledBorder("Herramientas BD"));
        } else if (InicioFrame.getIdioma().equals("English")) {
          exportPanel.setBorder(BorderFactory.createTitledBorder("DB Tools"));
        } else if (InicioFrame.getIdioma().equals("Euskera")) {
          exportPanel.setBorder(BorderFactory.createTitledBorder("DB Tresnak"));
        }

        exportButton = new JButton();  
        if (InicioFrame.getIdioma().equals("Español")) {
          exportButton.setText("Exportar BD a CSV");
        } else if (InicioFrame.getIdioma.equals("English")){
          exportButton.setText("Export DB to CSV")
        } else if (InicioFrame.getIdioma().equals("Euskera")) {
          exportButton.setText("Esportatu DB CSVra")
        }
        
        exportButton.setBounds(20, 40, 220, 40);
        exportButton.setFont(new Font("Consolas", Font.BOLD, 14));
        exportButton.setActionCommand("export"); // reutilizamos el mismo comando
        exportButton.addActionListener(this);

        exportPanel.add(exportButton);
        desktop.add(exportPanel);
    }
    
    //React to menu selections.
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("delete")) {
            Database.eliminarDatos();
        } else if (e.getActionCommand().equals("sedes")) {
        	createSedesFrame();
        } else if (e.getActionCommand().equals("usuarios")) {
            createUsuariosFrame();
        } else if (e.getActionCommand().equals("residuos")) {
            createResiduosFrame();
        } else if (e.getActionCommand().equals("empleados")) {
            createEmpleadosFrame();
        } else if (e.getActionCommand().equals("energia")) {
            createConsumoEnergiaFrame();
        } else if (e.getActionCommand().equals("emisiones")) {
            createEmisionesFrame();
        } else if (e.getActionCommand().equals("servidores")) {
            createServidoresFrame();
        } else if (e.getActionCommand().equals("refrigeracion")) {
            createRefrigeracionFrame();
        } else if (e.getActionCommand().equals("export")) {
            exportarBD();
        } else { //quit
            quit();
        } 
    }  
    
    private void createSedesFrame() {
    	InternalFrameSedes frame = new InternalFrameSedes();
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
    
    private void createUsuariosFrame() {
    	InternalFrameUsuarios frame = new InternalFrameUsuarios();
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
    
    private void createResiduosFrame() {
        InternalFrameResiduos frame = new InternalFrameResiduos();
        frame.setVisible(true);
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
    
    private void createEmpleadosFrame() {
        InternalFrameEmpleados frame = new InternalFrameEmpleados();
        frame.setVisible(true);
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
    
    private void createConsumoEnergiaFrame() {
        InternalFrameConsumoEnergia frame = new InternalFrameConsumoEnergia();
        frame.setVisible(true);
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
    
    private void createEmisionesFrame() {
        InternalFrameEmisiones frame = new InternalFrameEmisiones();
        frame.setVisible(true);
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
    
    private void createServidoresFrame() {
        InternalFrameServidores frame = new InternalFrameServidores();
        frame.setVisible(true);
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
    
    private void createRefrigeracionFrame() {
        InternalFrameRefrigeracion frame = new InternalFrameRefrigeracion();
        frame.setVisible(true);
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
    
    //Quit the application.
    private void quit() {
        System.exit(0);
    }

    // Exportar BD a CSV (lo llaman el menú y el botón)
    private void exportarBD() {
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        chooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File dir = chooser.getSelectedFile();
            String ruta = dir.getAbsolutePath();
            try {
                Database.exportarBDaCSVs(ruta);
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Base de datos exportada a CSV en:\n" + ruta,
                        "Exportación completada",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Error al exportar la base de datos:\n" + ex.getMessage(),
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Create the GUI and show it.
     */
    public static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setUndecorated(true);
        frame.setVisible(true);
    }
}
	