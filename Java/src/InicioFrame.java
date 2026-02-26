import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

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
	private int psw = 1234; // elemento placeholder hasta que se implemente otra cosa
	
	InicioFrame(){
		
		
		
		this.setTitle("Inicio Sesión Demo"); // establece el titulo del frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sale de la aplicacion
		this.setResizable(false); // No deja redimensionar el frame
		this.setSize(640, 360); // Establece las dimensiones en x e y
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		
		
		menu = new JMenu("Menu");
		menu.setFont(new Font("Consolas", Font.BOLD, 20));
		menu.setSize(300, 300);
		
		
		salirItem = new JMenuItem("Salir");
		salirItem.setFont(new Font("Consolas", Font.BOLD, 17));
		salirItem.setPreferredSize(new Dimension(200, salirItem.getPreferredSize().height));
		salirItem.addActionListener(e -> System.exit(0)); // Lambda function, cierra la aplicación
		
		menuBar.add(menu);
		menu.add(salirItem);
		this.setJMenuBar(menuBar);
		
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.activeCaption);
		this.add(panel);
		
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
		inicioButton = new JButton();
		inicioButton.setContentAreaFilled(false);
		inicioButton.setIcon(new ImageIcon("inicio_boton.png"));
		inicioButton.setBounds(860, 480, 30, 30);
		inicioButton.setFocusPainted(false);
		inicioButton.setBorderPainted(false);
		inicioButton.addActionListener(e -> verificar());
		panel.add(inicioButton);
		
		this.setUndecorated(true);
		this.setVisible(true);
	}
	public void verificar() {
			String usr = userField.getText();
			int pass = Integer.parseInt(new String(pswField.getPassword()));
			if (!usr.equals(usuario) || pass != psw) {
				JOptionPane.showMessageDialog(panel, "Usuario o contraseña introducidos son incorrectos", "error", JOptionPane.ERROR_MESSAGE);
				userField.setText("");
				pswField.setText("");
			} else if (usr.equals(usuario) && pass == psw) {
				new MainFrame();
				this.dispose();
			}
	}
}
