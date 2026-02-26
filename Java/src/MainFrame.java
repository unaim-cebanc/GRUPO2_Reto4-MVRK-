import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import java.awt.SystemColor;

/**
 * Frame principal donde se realizarán todas las operaciones
 * @author Unai
 * @version 2.0
 */
public class MainFrame extends JFrame{

	JMenuBar menuBar;
	JMenu menu;
	JMenuItem salirItem, clsSesItem;
	JPanel panel;
	JLabel placeholderLabel;
	
	MainFrame() {
		
		// --- Configuración aspecto JFrame --- //
		this.getContentPane().setBackground(SystemColor.activeCaption);
		this.setTitle("GUI Demo"); // establece el titulo del frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sale de la aplicacion
		this.setResizable(false); // No deja redimensionar el frame
		this.setSize(640, 360); // Establece las dimensiones en x e y
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// -----------------------------------//
		
		// --- Barra de Menú Superior --- //
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		
		
		menu = new JMenu("Menu");
		menu.setSize(300, 300);
		menu.setFont(new Font("Consolas", Font.BOLD, 20));
		
		salirItem = new JMenuItem("Salir");
		salirItem.setFont(new Font("Consolas", Font.BOLD, 17));
		salirItem.setPreferredSize(new Dimension(200, salirItem.getPreferredSize().height));
		salirItem.addActionListener(e -> System.exit(0)); // Lambda function, cierra la aplicación
		
		clsSesItem = new JMenuItem("Cerrar Sesión");
		clsSesItem.setFont(new Font("Consolas", Font.BOLD, 17));
		clsSesItem.setPreferredSize(new Dimension(200, salirItem.getPreferredSize().height));
		clsSesItem.addActionListener(e -> {
			new InicioFrame();
			this.dispose();
		});

		menuBar.add(menu);
		menu.add(salirItem);
		menu.add(clsSesItem);
		this.setJMenuBar(menuBar);
		// -------------------------------//
		
		// --- JPanel principal --- //
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		this.add(panel);
		// ------------------------//
		
		// --- Elementos Placeholder ---//
		placeholderLabel = new JLabel("PLACEHOLDER");
		placeholderLabel.setFont(new Font("Consolas", Font.BOLD, 80));
		placeholderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(placeholderLabel);
		// ----------------------------//
		
		// --- Controla la visibiliadad del frame --- //
		this.setUndecorated(true); //Elimina la barra de encima de la ventana
		this.setVisible(true); // Hace le GUI visible
		// ------------------------------------------//
	}
}