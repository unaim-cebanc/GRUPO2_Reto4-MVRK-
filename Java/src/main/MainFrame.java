package main;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;

/**
 * Frame principal en el que el usuario interactua la mayoria del tiempo, el usuario
 * puede tomar tres acciones, añadir elementos a la base de datos, eliminar elementos de la base de datos, o exportar las tablas a .csv
 * @author Unax
 */
public class MainFrame extends JFrame implements ActionListener {
	public static String idioma = InicioFrame.getIdioma();
	private JDesktopPane desktop;
	JButton deleteButton;      // BOTÓN DEL RECUADRO
	JPanel deletePanel;        // RECUADRO

   /**
    * Constructor del frame principal que contiene la funcionalidad principal del programa
    */
	public MainFrame() {
 
    	
    	
    	//Crea los InternalFrame a un offset de 50 pixeles veritcales y horizontales.
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                  screenSize.width  - inset*2,
                  screenSize.height - inset*2);
 
        //Prepara la GUI.
        desktop = new JDesktopPane(); //a specialized layered pane
        setContentPane(desktop);
        setJMenuBar(createMenuBar());
     
        // Recuadros con botones
        createDeletePanel();
        createAddPanel();
        createExportPanel();
        
        //Establece el efecto de arrastre de las ventanas.
        desktop.setBackground(SystemColor.activeCaption);
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        
        desktop.setLayout(null); // para colocar el recuadro por coordenadas
        setContentPane(desktop);

        // Barra de menú
        setJMenuBar(createMenuBar());
    }

    /**
     * Crea la barra de menu
     * @return Devuelve la barra del menú
     */
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
	        
	        if (Database.getAdmin()) {
	        	JMenuItem menuItemAgregar  = new JMenuItem("Tabla Sedes");
		        menuItemAgregar.setActionCommand("sedes");
		        menuItemAgregar.addActionListener(this);
		        menuAgregar.add(menuItemAgregar);
		        
		        menuItemAgregar  = new JMenuItem("Tabla Usuarios");
		        menuItemAgregar.setActionCommand("usuarios");
		        menuItemAgregar.addActionListener(this);
		        menuAgregar.add(menuItemAgregar);
		        
		        menuItemAgregar  = new JMenuItem("Tabla Empleados");
		        menuItemAgregar.setActionCommand("empleados");
		        menuItemAgregar.addActionListener(this);
		        menuAgregar.add(menuItemAgregar);
	        }
	        
	        JMenuItem menuItemAgregar  = new JMenuItem("Tabla Residuos");
	        menuItemAgregar.setActionCommand("residuos");
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
	        
	        menuItemAgregar  = new JMenuItem("Tabla Sistemas Refrigeracion");
	        menuItemAgregar.setActionCommand("refrigeracion");
	        menuItemAgregar.addActionListener(this);
	        menuAgregar.add(menuItemAgregar);
	        
	        // ITEM: Borrar datos de sql
	        if (Database.getAdmin()) {
	        	JMenuItem deleteItem = new JMenuItem("Borrar datos");
		        deleteItem.setActionCommand("delete");
		        deleteItem.addActionListener(this);
		        menu.add(deleteItem);
	        }
          
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
    	        
            if (Database.getAdmin()) {
            	JMenuItem menuItemAgregar  = new JMenuItem("Headquarters Table");
                menuItemAgregar.setActionCommand("sedes");
                menuItemAgregar.addActionListener(this);
                menuAgregar.add(menuItemAgregar);
                
                menuItemAgregar  = new JMenuItem("Users Table");
                menuItemAgregar.setActionCommand("usuarios");
                menuItemAgregar.addActionListener(this);
                menuAgregar.add(menuItemAgregar);
                
                menuItemAgregar  = new JMenuItem("Employee Table");
                menuItemAgregar.setActionCommand("empleados");
                menuItemAgregar.addActionListener(this);
                menuAgregar.add(menuItemAgregar);
            }
            
            JMenuItem menuItemAgregar  = new JMenuItem("Residues Table");
            menuItemAgregar.setActionCommand("residuos");
            menuItemAgregar.addActionListener(this);
            menuAgregar.add(menuItemAgregar);
    	         
            menuItemAgregar  = new JMenuItem("Energy Consumption Table");
            menuItemAgregar.setActionCommand("energia");
            menuItemAgregar.addActionListener(this);
            menuAgregar.add(menuItemAgregar);
    	        
            menuItemAgregar  = new JMenuItem("Emissions Table");
            menuItemAgregar.setActionCommand("emisiones");
            menuItemAgregar.addActionListener(this);
            menuAgregar.add(menuItemAgregar);
    	        
            menuItemAgregar  = new JMenuItem("Servers Table");
            menuItemAgregar.setActionCommand("servidores");
            menuItemAgregar.addActionListener(this);
            menuAgregar.add(menuItemAgregar);
    	        
            menuItemAgregar  = new JMenuItem("Cooling Systems Table");
            menuItemAgregar.setActionCommand("refrigeracion");
            menuItemAgregar.addActionListener(this);
            menuAgregar.add(menuItemAgregar);
    	        
            if (Database.getAdmin()) {
            	// Borrar datos de sql
                JMenuItem deleteItem = new JMenuItem("Erase data");
                deleteItem.setActionCommand("delete");
                deleteItem.addActionListener(this);
                menu.add(deleteItem);
            }
            
            // EXPORTAR BD A CSV
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
	        
	        if (Database.getAdmin()) {
	        	JMenuItem menuItemAgregar  = new JMenuItem("Egoitzen Taula");
		        menuItemAgregar.setActionCommand("sedes");
		        menuItemAgregar.addActionListener(this);
		        menuAgregar.add(menuItemAgregar);
		        
		        menuItemAgregar  = new JMenuItem("Erabiltzaileen Taula");
		        menuItemAgregar.setActionCommand("usuarios");
		        menuItemAgregar.addActionListener(this);
		        menuAgregar.add(menuItemAgregar);
		        
		        menuItemAgregar  = new JMenuItem("Langile Taula");
		        menuItemAgregar.setActionCommand("empleados");
		        menuItemAgregar.addActionListener(this);
		        menuAgregar.add(menuItemAgregar);
	        }
	        
	        JMenuItem menuItemAgregar  = new JMenuItem("Hondakin Taula");
	        menuItemAgregar.setActionCommand("residuos");
	        menuItemAgregar.addActionListener(this);
	        menuAgregar.add(menuItemAgregar);
	        
	        menuItemAgregar  = new JMenuItem("Energia-kontsumo Taula");
	        menuItemAgregar.setActionCommand("energia");
	        menuItemAgregar.addActionListener(this);
	        menuAgregar.add(menuItemAgregar);
	        
	        menuItemAgregar  = new JMenuItem("Isurtzeen Taula");
	        menuItemAgregar.setActionCommand("emisiones");
	        menuItemAgregar.addActionListener(this);
	        menuAgregar.add(menuItemAgregar);
	        
	        menuItemAgregar  = new JMenuItem("Zerbitzari Taula");
	        menuItemAgregar.setActionCommand("servidores");
	        menuItemAgregar.addActionListener(this);
	        menuAgregar.add(menuItemAgregar);
	        
	        menuItemAgregar  = new JMenuItem("Hozte-sistema Taula");
	        menuItemAgregar.setActionCommand("refrigeracion");
	        menuItemAgregar.addActionListener(this);
	        menuAgregar.add(menuItemAgregar);
	        
	        if (Database.getAdmin()) {
	        	// ITEM: Borrar datos de sql
		        JMenuItem deleteItem = new JMenuItem("Datuak ezabatu");
		        deleteItem.setActionCommand("delete");
		        deleteItem.addActionListener(this);
		        menu.add(deleteItem);
	        }

	        // ITEM: EXPORTAR BD A CSV
	        JMenuItem exportItem = new JMenuItem("Esportatu DB CSVra");
	        exportItem.setActionCommand("export");
	        exportItem.addActionListener(this);
	        menu.add(exportItem);
        }
        return menuBar;
    }
    
    
    /**
     * Crea el panel con el boton para eliminar datos de la base de datos
     */
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
        
        if (!Database.getAdmin()) {
        	deleteButton.setEnabled(false);
        }

        deletePanel.add(deleteButton);
        desktop.add(deletePanel);
    }
    
	/**
	 * Crea el panel con el boton en la pantalla principal para añadir datos en la base de datos
	 */
	private void createAddPanel() {
        JPanel addPanel = new JPanel();
        addPanel.setLayout(null);
        addPanel.setBounds(970, 30, 260, 120); // posición y tamaño del recuadro
        addPanel.setBackground(Color.WHITE);
        if (idioma.equals("Español")) {
        	addPanel.setBorder(BorderFactory.createTitledBorder("Añadir datos BD"));
        } else if (idioma.equals("English")) {
        	addPanel.setBorder(BorderFactory.createTitledBorder("Add data DB"));
        } else if (idioma.equals("Euskera")) {
        	addPanel.setBorder(BorderFactory.createTitledBorder("Datuak sartu DB"));
        }
        
        JButton addButton = new JButton("Eliminar datos BD");
        if (idioma.equals("Español")) {
        	addButton.setText("Añadir datos BD");
        } else if (idioma.equals("English")) {
        	addButton.setText("Add data DB");
        } else if (idioma.equals("Euskera")) {
        	addButton.setText("Datuak sartu DB");
        }
        addButton.setBounds(20, 40, 220, 40);
        addButton.setFont(new Font("Consolas", Font.BOLD, 14));
        addButton.setActionCommand("añadir"); // reutilizar el mismo comando
        addButton.addActionListener(this);

        addPanel.add(addButton);
        desktop.add(addPanel);
    }
    // Recuadro con botón en la pantalla principal
    /**
     * Crea el panel con el boton que permite exportar los datos de la base a .csv
     */
	private void createExportPanel() {
        JPanel exportPanel = new JPanel();
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

        JButton exportButton = new JButton();  
        if (InicioFrame.getIdioma().equals("Español")) {
          exportButton.setText("Exportar BD a CSV");
        } else if (InicioFrame.getIdioma().equals("English")){
          exportButton.setText("Export DB to CSV");
        } else if (InicioFrame.getIdioma().equals("Euskera")) {
          exportButton.setText("Esportatu DB CSVra");
        }
        
        exportButton.setBounds(20, 40, 220, 40);
        exportButton.setFont(new Font("Consolas", Font.BOLD, 14));
        exportButton.setActionCommand("export"); // reutilizamos el mismo comando
        exportButton.addActionListener(this);

        exportPanel.add(exportButton);
        desktop.add(exportPanel);
    }
    
    //React to menu selections.
    
	/**
	 * Detecta las acciones que hace el usuario y dependiendo de la accion realizada se ejecuta un metodo u otro
	 */
	public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("delete")) {
            Database.eliminarDatos();
        } else if (e.getActionCommand().equals("añadir")) {
        	elegirTabla();
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
    
    /**
     * Crea el internal frame para añadir para añadir datos a la tabla sedes
     */
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
    
	/**
     * Crea el internal frame para añadir para añadir datos a la tabla usuarios
     */
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
    
	/**
     * Crea el internal frame para añadir para añadir datos a la tabla residuos
     */
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
    
	/**
     * Crea el internal frame para añadir para añadir datos a la tabla empleados
     */
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
    
    
	/**
     * Crea el internal frame para añadir para añadir datos a la tabla consumo de energia
     */
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
    
	/**
     * Crea el internal frame para añadir para añadir datos a la tabla emisiones
     */
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
    
	/**
     * Crea el internal frame para añadir para añadir datos a la tabla de salas de servidores
     */
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
    
	/**
     * Crea el internal frame para añadir para añadir datos a la tabla sistemas de refrigeracion
     */
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
    
    /**
     * Permite cerrar la aplicacion
     */
    private void quit() {
        System.exit(0);
    }

    // Exportar BD a CSV (lo llaman el menú y el botón)
    /**
     * Exporta las tablas de la base de datos a .csv
     */
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
     * Permite elegir una tabla especifica para exportar
     */
    private void elegirTabla() {
    	Object[] opciones = {"Sedes",
    			"Consumo de Energia",
    			"Emisiones",
    			"Empleados",
    			"Sistemas de Refrigeracion",
    			"Residuos",
    			"Salas Servidores",
    			"Usuarios"};
    	
    	if (!Database.getAdmin()) {
    		ArrayList<Object> listaOpciones = new ArrayList<>(Arrays.asList(opciones));
    		listaOpciones.remove("Sedes");
    		listaOpciones.remove("Usuarios");
    		listaOpciones.remove("Empleados");
    		opciones = listaOpciones.toArray();
    	}
    	
    	Object elegirOpcion = JOptionPane.showInputDialog(desktop, "Elige una tabla", "Atencion", JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
    	
    	if (String.valueOf(elegirOpcion).equals("Sedes")) {
    		createSedesFrame();
    	} else if (String.valueOf(elegirOpcion).equals("Consumo de Energia")) {
    		createConsumoEnergiaFrame();
    	} else if (String.valueOf(elegirOpcion).equals("Emisiones")) {
    		createEmisionesFrame();
    	} else if (String.valueOf(elegirOpcion).equals("Empleados")) {
    		createEmpleadosFrame();
    	} else if (String.valueOf(elegirOpcion).equals("Sistemas de Refrigeracion")) {
    		createRefrigeracionFrame();
    	} else if (String.valueOf(elegirOpcion).equals("Residuos")) {
    		createResiduosFrame();
    	} else if (String.valueOf(elegirOpcion).equals("Salas Servidores")) {
    		createServidoresFrame();
    	} else if (String.valueOf(elegirOpcion).equals("Usuarios")) {
    		createUsuariosFrame();
    	}
    }
    
    
    /**
     * Crea la GUI y la muestra.
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
	