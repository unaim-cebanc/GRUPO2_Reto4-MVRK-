import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InternalFrameUsuarios extends InternalFrame {
	public InternalFrameUsuarios() {
		
		JPanel contenido = new JPanel();
		contenido.setLayout(null);
		add(contenido);
		
		
		JLabel Labeladmin = new JLabel();
		Labeladmin.setText("id_usuario_admin");
		Labeladmin.setBounds(50, 100, 150, 30);
		Labeladmin.setFont(new Font("Consolas", Font.BOLD, 20));
		Labeladmin.setHorizontalAlignment(SwingConstants.RIGHT);
		Labeladmin.setVerticalAlignment(SwingConstants.CENTER);
		contenido.add(Labeladmin);
		
		JLabel Labelnomusuario = new JLabel();
		Labelnomusuario.setText("nombre_usuario");
		Labelnomusuario.setBounds(50, 150, 150, 30);
		Labelnomusuario.setFont(new Font("Consolas", Font.BOLD, 20));
		Labelnomusuario.setHorizontalAlignment(SwingConstants.RIGHT);
		contenido.add(Labelnomusuario);
		
		JLabel Labelcontraseña = new JLabel();
		Labelcontraseña.setText("contraseña");
		Labelcontraseña.setBounds(50, 200, 150, 30);
		Labelcontraseña.setFont(new Font("Consolas", Font.BOLD, 20));
		Labelcontraseña.setHorizontalAlignment(SwingConstants.RIGHT);
		contenido.add(Labelcontraseña);
		
		JTextField campoAdmin = new JTextField();
		campoAdmin.setBounds(220, 150, 150, 25);
		campoAdmin.setFont(campoAdmin.getFont().deriveFont(15f));
		contenido.add(campoAdmin);
		
		JTextField camponomusuario = new JTextField();
		camponomusuario.setBounds(220, 200, 150, 25);
		camponomusuario.setFont(camponomusuario.getFont().deriveFont(15f));
		contenido.add(camponomusuario);
		
		JTextField campoContraseña = new JTextField();
		campoContraseña.setBounds(220, 250, 150, 25);
		campoContraseña.setFont(Labelnomusuario.getFont().deriveFont(15f));
		contenido.add(campoContraseña);
		
		JButton limpiar = new JButton("Limpiar");
		limpiar.setBounds(60, 300, 100, 30);
		limpiar.setFont(limpiar.getFont().deriveFont(15f));
		contenido.add(limpiar);
		limpiar.addActionListener(new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) {
			campoAdmin.setText("");
			camponomusuario.setText("");
			campoContraseña.setText("");
			}
		});
		
		JButton agregar = new JButton("Agregar");
		agregar.setBounds(300, 300, 100, 30);
		agregar.setFont(agregar.getFont().deriveFont(15f));
		contenido.add(agregar);
		agregar.addActionListener(new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) {
			String textoAdmin = campoAdmin.getText();
			String nombre_usuario = camponomusuario.getText();
			String contraseña = campoContraseña.getText();
			if (textoAdmin.isBlank() || nombre_usuario.isBlank() || contraseña.isBlank()) {
				JOptionPane.showMessageDialog(null, "Introduce datos en todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// Convertir a int después de validar que no están vacíos
            int admin      = Integer.parseInt(textoAdmin);
            
            Database.insertarDatosUsuarios(admin, nombre_usuario, contraseña);
			}
		});
		
		setSize(500,450);
	    setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
	}


}
