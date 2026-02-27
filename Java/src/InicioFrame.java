import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

/**
 * JFrame de Inicio de Sesión
 * @author Unai
 * @version 2.0
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
	private String usuario = "placeholder"; // elemento placeholder hasta que se implemente otra cosa
	private String psw = "1234"; // elemento placeholder hasta que se implemente otra cosa
	
	/**
	 * Constructor del JFrame InicioFrame
	 */
	InicioFrame(){
		
		// --- Configuración aspecto JFrame --- //
		this.setTitle("Inicio Sesión Demo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		// -----------------------------------//
		
		// --- Barra de menú superior --- //
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		
		
		menu = new JMenu("Menu");
		menu.setFont(new Font("Consolas", Font.BOLD, 20));
		menu.setSize(300, 300);
		
		
		salirItem = new JMenuItem("Salir");
		salirItem.setFont(new Font("Consolas", Font.BOLD, 20));
		salirItem.setPreferredSize(new Dimension(200, salirItem.getPreferredSize().height));
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
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
			if (!usr.equals(usuario) || !pass.equals(pass)) {
				JOptionPane.showMessageDialog(panel, "Usuario o contraseña introducidos son incorrectos", "error", JOptionPane.ERROR_MESSAGE);
				userField.setText("");
				pswField.setText("");
			} else if (usr.equals(usuario) && pass.equals(psw)) {
				new MainFrame();
				this.dispose();
			}
	}
}
