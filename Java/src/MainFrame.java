import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.event.*;
import java.awt.*;

public class MainFrame extends JFrame implements ActionListener {

    JDesktopPane desktop;
    JButton exportButton;      // BOTÓN DEL RECUADRO
    JPanel exportPanel;        // RECUADRO

    public MainFrame() {
        super("InternalFrameDemo");

        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width - inset*2,
                screenSize.height - inset*2);

        // DesktopPane
        desktop = new JDesktopPane();
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

        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Nueva Ventana");
        menuItem.setActionCommand("new");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Cerrar");
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        // ITEM: EXPORTAR BD A CSV
        JMenuItem exportItem = new JMenuItem("Exportar BD a CSV");
        exportItem.setActionCommand("export");
        exportItem.addActionListener(this);
        menu.add(exportItem);

        return menuBar;
    }

    // Recuadro con botón en la pantalla principal
    private void createExportPanel() {
        exportPanel = new JPanel();
        exportPanel.setLayout(null);
        exportPanel.setBounds(30, 30, 260, 120); // posición y tamaño del recuadro
        exportPanel.setBackground(Color.WHITE);
        exportPanel.setBorder(BorderFactory.createTitledBorder("Herramientas BD"));

        exportButton = new JButton("Exportar BD a CSV");
        exportButton.setBounds(20, 40, 220, 40);
        exportButton.setFont(new Font("Consolas", Font.BOLD, 14));
        exportButton.setActionCommand("export"); // reutilizamos el mismo comando
        exportButton.addActionListener(this);

        exportPanel.add(exportButton);
        desktop.add(exportPanel);
    }

    //React to menu selections and button
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if ("new".equals(cmd)) {
            createFrame();
        } else if ("quit".equals(cmd)) {
            quit();
        } else if ("export".equals(cmd)) {
            exportarBD();
        }
    }

    //Create a new internal frame.
    private void createFrame() {
        InternalFrame frame = new InternalFrame();
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
	