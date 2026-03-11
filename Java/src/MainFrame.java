
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import java.awt.event.*;
import java.awt.*;
public class MainFrame extends JFrame implements ActionListener {
    
	JDesktopPane desktop;
 
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
        
        //Make dragging a little faster but perhaps uglier.
        desktop.setBackground(SystemColor.activeCaption);
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }
 
    private JMenuBar createMenuBar() {
        
    	JMenuBar menuBar = new JMenuBar();
    	menuBar.setBorderPainted(false);
        
    	//Set up barra menu.
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);
        
 
        //Set up primer menu item.
        JMenuItem menuItem = new JMenuItem("Nueva Ventana");
        menuItem.setActionCommand("new");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        //Set up segundo menu item.
        menuItem = new JMenuItem("Cerrar");
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menu.addSeparator();
        
        //Set up tercer menu item
        JMenu menuAgregar = new JMenu("Agregar Datos");
        menu.add(menuAgregar);
        
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
        
        return menuBar;
    }
 
    //React to menu selections.
    public void actionPerformed(ActionEvent e) {
        if ("new".equals(e.getActionCommand())) {
            createFrame();
        } else if ("sedes".equals(e.getActionCommand())) {
            createSedesFrame();
        } else if ("usuarios".equals(e.getActionCommand())) {
            createUsuariosFrame();
        } else if ("residuos".equals(e.getActionCommand())) {
            createResiduosFrame();
        } else if ("empleados".equals(e.getActionCommand())) {
            createEmpleadosFrame();
        } else if ("energia".equals(e.getActionCommand())) {
            createConsumoEnergiaFrame();
        } else if ("emisiones".equals(e.getActionCommand())) {
            createEmisionesFrame();
        } else if ("servidores".equals(e.getActionCommand())) {
            createServidoresFrame();
        } else if ("refrigeracion".equals(e.getActionCommand())) {
            createRefrigeracionFrame();
        } else {
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