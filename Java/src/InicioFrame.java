import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
/*
 * Notas importantes:
 * Eliminar "extends JFrame" en ambos frames
 * Cambiar getter y setter para que devuelvan true o false
 * Crear, eliminar, y cambiar idioma desde Main llamando funciones de inicioFrame y MainFrame
 */


/**
 * JFrame de Inicio de Sesión
 * @author Unai
 * @version 2.1
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
	private String usuario = "placeholder", psw = "1234"; // elemento placeholder hasta que se implemente otra cosa
	private String errorMsg = "Usuario o Contraseña incorrectos";
	public int pred;
	
	/**
	 * Constructor del JFrame InicioFrame
	 */
	InicioFrame(){
		
		// --- Configuración aspecto JFrame --- //
		this.setTitle("Inicio Sesión Demo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// -----------------------------------//
		
		// --- Barra de menú superior --- //
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		
		menu = new JMenu("Menu");
		
		salirItem = new JMenuItem("Salir");
		salirItem.addActionListener(e -> System.exit(0)); // Lambda function, cierra la aplicación
		
		menuBar.add(menu);
		menu.add(salirItem);
		this.setJMenuBar(menuBar);
		// -------------------------------//
		
		// --- JPanel principal --- //
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.activeCaption);
		this.add(panel);
		// ------------------------//
		
		// --- Label + TextField Usuario --- //
		userLabel = new JLabel("Usuario:");
		userLabel.setBounds(550, 400, 130, 30);
		userLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(userLabel);
		
		userField = new JTextField();
		userField.setBounds(700, 400, 160, 30);
		userField.setFont(new Font("Consolas", Font.BOLD, 20));
		panel.add(userField);
		// ---------------------------------//
		
		// --- Label + Field Contraseña --- //
		pswLabel = new JLabel("Contraseña:");
		pswLabel.setBounds(550, 480, 130, 30);
		pswLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		pswLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(pswLabel);
		
		pswField = new JPasswordField();
		pswField.setBounds(700, 480, 160, 30);
		pswField.setFont(new Font("Consolas", Font.BOLD, 20));
		panel.add(pswField);
		// ---------------------------------//
		
		// --- Botón inicio sesión --- //
		inicioButton = new JButton(">>");
		inicioButton.setFont(new Font("Consolas", Font.BOLD, 10));
		inicioButton.setBounds(860, 480, 45, 30);
		
		panel.add(inicioButton);
		inicioButton.addActionListener(e -> verificar());
		// ---------------------------------//
		
		// --- Combo Box Idiomas --- //
		String[] idiomas = {"Español", "English", "Euskera"};
		idiomasBox = new JComboBox<String>(idiomas);
		idiomasBox.setBounds(1400, 50, 100, 25);
		idiomasBox.setFont(new Font("Consolas", Font.BOLD, 12));
		idiomasBox.setSelectedIndex(pred);
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
	 * Verifica si el usuario y la contraseña introducidos son correctos.
	 */
	public void verificar() {
			String usr = userField.getText();
			String pass = new String(pswField.getPassword());
			if (usr.equals(usuario) && pass.equals(psw)) {
				MainFrame.idioma = getIdioma();
				MainFrame.createAndShowGUI();
			} else if (!usr.equals(usuario) || !pass.equals(psw)) {this.errorInicioSesion();}
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
			pred = 0;
			errorMsg = "Usuario o Contraseña incorrectos";
			salirItem.setText("Salir");
			userLabel.setText("Usuario:");
			pswLabel.setText("Contraseña:");
			panel.repaint();
		} else if (idioma.equals("English")) {
			pred = 1;
			errorMsg = "User or Password incorrect";
			salirItem.setText("Exit");
			userLabel.setText("User:");
			pswLabel.setText("Password:");
			panel.repaint();
		} else if (idioma.equals("Euskera")) {
			pred = 2;
			errorMsg = "Erabiltzaie izena edo pasahitza okerra";
			salirItem.setText("Irten");
			userLabel.setText("Erabiltzaiea:");
			pswLabel.setText("Pasahitza:");
			panel.repaint();
		}
	}
	public void errorInicioSesion() {
		JOptionPane.showMessageDialog(panel, errorMsg, "error", JOptionPane.ERROR_MESSAGE);
		userField.setText("");
		pswField.setText("");
	}
}
