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
						
						JOptionPane.showMessageDialog(panel, "Sesión iniciada correctamente!", "Login Status", JOptionPane.INFORMATION_MESSAGE);
					} else {
						
						JOptionPane.showMessageDialog(panel, "Error al iniciar sesión, pruebe a introducir sus credenciales otra vez",
								"Login Status", JOptionPane.ERROR_MESSAGE);
						
						userField.setText("");
						pswField.setText("");
						
						userLabel.requestFocus();
					}
				} else {
					
					JOptionPane.showMessageDialog(panel, "Los campos no pueden estar vacios", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
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
			salirItem.setText("Salir");
			userLabel.setText("Usuario:");
			pswLabel.setText("Contraseña:");
			panel.repaint();
		} else if (idioma.equals("English")) {
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
