import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class Frame extends JFrame{

	JMenuBar menuBar;
	JMenu menu;
	JMenuItem salirItem;
	
	Frame() {
		
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		salirItem = new JMenuItem("Salir");
		
		menu.setFont(new Font("Consolas", Font.BOLD, 17));
		salirItem.setFont(new Font("Consolas", Font.BOLD, 12));
		
		menuBar.add(menu);
		menu.add(salirItem);
		

		this.setTitle("GUI Demo"); // establece el titulo del frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sale de la aplicacion
		this.setResizable(false); // No deja redimensionar el frame
		this.setSize(640, 360); // Establece las dimensiones en x e y
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fuerza la ventana a extar maximizado
		this.setLayout(new BorderLayout()); // No establezco ningún layout
		this.getContentPane().setBackground(Color.decode("#76A6B9"));
		
		this.setJMenuBar(menuBar);
		
		this.setUndecorated(true); //Elimina la barra de encima de la ventana
		this.setVisible(true); // Hace le GUI visible
		
		
		
		salirItem.addActionListener(e -> System.exit(0)); // Lambda function, cierra la aplicación
	}
}