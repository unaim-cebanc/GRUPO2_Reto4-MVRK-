package main;
import javax.swing.*;

import java.awt.Font;
import java.awt.SystemColor;
/*
 * Notas importantes:
 * Eliminar "extends JFrame" en ambos frames
 * Cambiar getter y setter para que devuelvan true o false
 * Crear, eliminar, y cambiar idioma desde Main llamando funciones de inicioFrame y MainFrame
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * JFrame de Inicio de Sesión
 * @author Unai, Aroa
 * @version 3
 */
public class InicioFrame extends JFrame {

	private JTextField userField;
	private JLabel userLabel, pswLabel;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem salirItem;
	private JPanel panel;
	private JPasswordField pswField;
	private JButton inicioButton;
	private static JComboBox<String> idiomasBox;
	private String errorMsg = "Usuario o contraseña incorrectos";
	private String loginMsg = "Sesión iniciada correctamente!";
	
	/**
	 * Autor: Unai Manterola
	 * Constructor del JFrame InicioFrame
	 */
	InicioFrame(){
		
		// --- Configuración aspecto JFrame --- //
		this.setTitle("Inicio Sesión"); // Titulo ventana
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra el programa al cerrar la ventana
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza el aspecto del programa
		// -----------------------------------//
		
		// --- Barra de menú superior --- //
		// Crea la barra del menú
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		
		//Crea un item que va en la barra del menu
		menu = new JMenu("Menu");
		
		// Crea un item que va dentro de "Menu"
		salirItem = new JMenuItem("Salir");
		salirItem.addActionListener(e -> System.exit(0)); // Lambda function, cierra la aplicación
		
		// Añade los elementos la barra
		menuBar.add(menu);
		menu.add(salirItem);
		this.setJMenuBar(menuBar);
		// -------------------------------//
		
		// --- JPanel principal --- //
		panel = new JPanel(); // Crea el panel
		panel.setLayout(null); // Elimina el layout 
		panel.setBackground(SystemColor.activeCaption); // Establece el color de fondo
		this.add(panel); // Añade el panel al frame
		// ------------------------//
		
		// --- Label + TextField Usuario --- //
		// Crea el label y establece su posicion
		userLabel = new JLabel("Usuario:");
		userLabel.setBounds(530, 400, 160, 30);
		
		// Establece la fuente del label y lo posiciona la derecha
		userLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(userLabel);
		
		// Crea la caja de texto y establece su posicion y tamaño
		userField = new JTextField();
		userField.setBounds(710, 400, 200, 30);
		
		// Establece la fuente del texto
		userField.setFont(new Font("Consolas", Font.BOLD, 20));
		panel.add(userField);
		// ---------------------------------//
		
		// --- Label + Field Contraseña --- //
		// Crea el label y establece su posicion
		pswLabel = new JLabel("Contraseña:");
		pswLabel.setBounds(530, 480, 160, 30);
		
		// Establece la fuente del texto y lo posiciona a la derecha
		pswLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		pswLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(pswLabel);
		
		// Crea la caja de contraseña y establece su posicion en el frame
		pswField = new JPasswordField();
		pswField.setBounds(710, 480, 200, 30);
		
		// Establece su fuente
		pswField.setFont(new Font("Consolas", Font.BOLD, 20));
		panel.add(pswField);
		// ---------------------------------//
		
		// --- Botón inicio sesión --- //
		// Crea el boton y lo pone en su lugar
		inicioButton = new JButton(">>");
		inicioButton.setFont(new Font("Consolas", Font.BOLD, 10));
		inicioButton.setBounds(910, 480, 45, 30);
		
		panel.add(inicioButton);
		
		// Action Listener que se ejecuta al hacer clic en el boton
		inicioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Recoge los valores de usuario y contraseña
				String usuario = userField.getText();
				String contraseña = new String(pswField.getPassword());
				
				// Valida que la longitud no sea 0 (que no este vacío)
				if (validarLength(usuario, contraseña)) {
					
					// Valida que el usuario existe en la base de datos y que los datos introducidos son correctos
					if (Database.validarLogin(usuario, contraseña)) {
						
						JOptionPane.showMessageDialog(panel, loginMsg, "Login Status", JOptionPane.INFORMATION_MESSAGE);
						MainFrame.createAndShowGUI();
						userField.setText("");
						userField.setEditable(false);
						pswField.setText("");
						pswField.setEditable(false);
						idiomasBox.setEnabled(false);
					} else {
						
						JOptionPane.showMessageDialog(panel, errorMsg, "Login Status", JOptionPane.ERROR_MESSAGE);
						
						userField.setText("");
						pswField.setText("");
						
						userLabel.requestFocus();
					}
				}
			}
		});
		// ---------------------------------//
		
		// --- Combo Box Idiomas --- //
		String[] idiomas = {"Español", "English", "Euskera"};
		idiomasBox = new JComboBox<String>(idiomas);
		idiomasBox.setBounds(1400, 50, 100, 25);
		idiomasBox.setFont(new Font("Consolas", Font.BOLD, 12));
		idiomasBox.setSelectedIndex(0);
		idiomasBox.setFocusable(false);
		idiomasBox.addActionListener(e -> setIdioma());
		panel.add(idiomasBox);
		// -------------------------//
		
		// --- Controla la visibiliadad del frame --- //
		this.setUndecorated(true); // elimina la barra superior de la ventana (minimizar, pantalla completa, exit)
		this.setVisible(true);
		// ------------------------------------------//
	}
	
	/**
	 * Cuando el usuario elige un idioma usando el Combo Box localizado en la esquina superior derecha,
	 * mira cual es el idioma seleccionado. 
	 * @return Devuelve el valor del item seleccionado en el Combo Box.
	 */
	public static String getIdioma() {
		return (String)idiomasBox.getSelectedItem();
	}
	
	/**
	 * Establece el idioma a partir del valor leído en el método getIdioma();
	 */
	public void setIdioma() {
		String idioma = getIdioma();
		if (idioma.equals("Español")) {
			errorMsg = "Usuario o contraseña incorrectos";
			loginMsg = "Sesión iniciada correctamente!";
			salirItem.setText("Salir");
			userLabel.setText("Usuario:");
			pswLabel.setText("Contraseña:");
			panel.repaint();
		} else if (idioma.equals("English")) {
			errorMsg = "User or password incorrect";
			loginMsg = "Log-in correct!";
			salirItem.setText("Exit");
			userLabel.setText("User:");
			pswLabel.setText("Password:");
			panel.repaint();
		} else if (idioma.equals("Euskera")) {
			errorMsg = "Erabiltzaie izena edo pasahitza okerra";
			loginMsg = "Saioa behar bezala hasi da!";
			salirItem.setText("Irten");
			userLabel.setText("Erabiltzaiea:");
			pswLabel.setText("Pasahitza:");
			panel.repaint();
		}
	}
	
	/**
	 * Revisa que el usuario no haya dejado los campos de usuario y contraseña vacios
	 * @param usuario Valor introducido en el JTextField userField
	 * @param contraseña Valor introducido en el JTextField pswField
	 * @return Devuelve false si la longitud de los campos es = 0, si no, devuelve true.
	 */
	private boolean validarLength(String usuario, String contraseña) {
		if (usuario.length() == 0 || contraseña.length() == 0 ) return false;
		return true;
	}
	
}
