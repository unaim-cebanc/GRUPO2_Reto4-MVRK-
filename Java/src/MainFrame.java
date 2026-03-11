
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.BorderFactory;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.*;
 
public class MainFrame extends JFrame implements ActionListener {
    public static String idioma = "Español";
	JDesktopPane desktop;
	JButton deleteButton;      // BOTÓN DEL RECUADRO
	JPanel deletePanel;        // RECUADRO
 
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
        
    	//Set up the lone menu.
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);
 
        //Set up the first menu item.
        if (MainFrame.idioma.equals ("Español")) {
	        JMenuItem menuItem = new JMenuItem("Nueva Ventana");
	        menuItem.setActionCommand("new");
	        menuItem.addActionListener(this);
	        menu.add(menuItem);
	 
	        //Set up the second menu item.
	        menuItem = new JMenuItem("Cerrar");
	        menuItem.setActionCommand("quit");
	        menuItem.addActionListener(this);
	        menu.add(menuItem);
        }
        else if (MainFrame.idioma.equals ("English")) {
        		JMenuItem menuItem = new JMenuItem("New window");
                menuItem.setActionCommand("new");
                menuItem.addActionListener(this);
                menu.add(menuItem);
         
                //Set up the second menu item.
                menuItem = new JMenuItem("Exit");
                menuItem.setActionCommand("quit");
                menuItem.addActionListener(this);
                menu.add(menuItem);	
        }
        else if (MainFrame.idioma.equals ("Euskera")) {
    		JMenuItem menuItem = new JMenuItem("Lehio berria");
            menuItem.setActionCommand("new");
            menuItem.addActionListener(this);
            menu.add(menuItem);
     
            //Set up the second menu item.
            menuItem = new JMenuItem("Irten");
            menuItem.setActionCommand("quit");
            menuItem.addActionListener(this);
            menu.add(menuItem);	
        }
        
        
        
     // ITEM: Borrar datos de sql
        JMenuItem deleteItem = new JMenuItem("Borrar datos de la BD");
        deleteItem.setActionCommand("delete");
        deleteItem.addActionListener(this);
        menu.add(deleteItem);

        
        return menuBar;
    }
    
    
 // Recuadro con botón en la pantalla principal
    private void createDeletePanel() {
        deletePanel = new JPanel();
        deletePanel.setLayout(null);
        deletePanel.setBounds(500, 30, 260, 120); // posición y tamaño del recuadro
        deletePanel.setBackground(Color.WHITE);
        deletePanel.setBorder(BorderFactory.createTitledBorder("Eliminar datos BD"));

        deleteButton = new JButton("Eliminar datos BD");
        deleteButton.setBounds(20, 40, 220, 40);
        deleteButton.setFont(new Font("Consolas", Font.BOLD, 14));
        deleteButton.setActionCommand("delete"); // reutilizar el mismo comando
        deleteButton.addActionListener(this);

        deletePanel.add(deleteButton);
        desktop.add(deletePanel);
    }
 
    //React to menu selections.
    public void actionPerformed(ActionEvent e) {
        if ("new".equals(e.getActionCommand())) { //new
            createFrame();
            
        } else if (e.getActionCommand().equals("delete")) {
            Database.eliminarDatos();
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