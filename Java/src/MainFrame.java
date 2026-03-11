import javax.swing.JButton;
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
 
    //React to menu selections.
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("delete")) {
            Database.eliminarDatos();
        } else if (e.getActionCommand().equals("sedes")) {
        	createSedesFrame();
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