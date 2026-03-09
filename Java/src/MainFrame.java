
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
    public static String idioma = "";
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
        if (this.idioma == "Español") {
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
        else if (this.idioma == "English") {
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
        else if (this.idioma == "Euskera") {
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
        		
        return menuBar;
    }
 
    //React to menu selections.
    public void actionPerformed(ActionEvent e) {
        if ("new".equals(e.getActionCommand())) { //new
            createFrame();
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