import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import java.awt.SystemColor;

public class MainFrame extends JFrame{

	JMenuBar menuBar;
	JMenu menu;
	JMenuItem salirItem;
	JPanel panel;
	JLabel placeholderLabel;
	
	MainFrame() {
		
		this.getContentPane().setBackground(SystemColor.activeCaption);
		
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menu = new JMenu("Menu");
		salirItem = new JMenuItem("Salir");
		
		menu.setFont(new Font("Consolas", Font.BOLD, 20));
		salirItem.setFont(new Font("Consolas", Font.BOLD, 17));
		
		menuBar.add(menu);
		menu.setSize(300, 300);
		menu.add(salirItem);
		salirItem.setPreferredSize(new Dimension(200, salirItem.getPreferredSize().height));

		this.setTitle("GUI Demo"); // establece el titulo del frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sale de la aplicacion
		this.setResizable(false); // No deja redimensionar el frame
		this.setSize(640, 360); // Establece las dimensiones en x e y
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		this.setJMenuBar(menuBar);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		placeholderLabel = new JLabel("PLACEHOLDER");
		placeholderLabel.setFont(new Font("Consolas", Font.BOLD, 80));
		placeholderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(placeholderLabel);
		this.add(panel);
		
		this.setUndecorated(true); //Elimina la barra de encima de la ventana
		this.setVisible(true); // Hace le GUI visible
		
		
		
		salirItem.addActionListener(e -> System.exit(0)); // Lambda function, cierra la aplicación
	}
}