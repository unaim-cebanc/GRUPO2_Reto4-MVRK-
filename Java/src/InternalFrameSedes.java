import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InternalFrameSedes extends InternalFrame {
	public InternalFrameSedes() {
		
		JPanel contenido = new JPanel();
		contenido.setLayout(null);
		add(contenido);
		
		JLabel LabelNombre = new JLabel();
		LabelNombre.setText("nombre_sede");
		LabelNombre.setBounds(50, 100, 150, 30);
		LabelNombre.setFont(new Font("Consolas", Font.BOLD, 15));
		LabelNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		LabelNombre.setVerticalAlignment(SwingConstants.CENTER);
		contenido.add(LabelNombre);
		
		JLabel LabelCiudad = new JLabel();
		LabelCiudad.setText("ciudad");
		LabelCiudad.setBounds(50, 150, 150, 30);
		LabelCiudad.setFont(new Font("Consolas", Font.BOLD, 15));
		LabelCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		contenido.add(LabelCiudad);
		
		JLabel LabelSuperficie = new JLabel();
		LabelSuperficie.setText("superficie");
		LabelSuperficie.setBounds(50, 200, 150, 30);
		LabelSuperficie.setFont(new Font("Consolas", Font.BOLD, 15));
		LabelSuperficie.setHorizontalAlignment(SwingConstants.RIGHT);
		contenido.add(LabelSuperficie);
		
		JTextField FieldNombre = new JTextField();
		FieldNombre.setBounds(220, 100, 150, 25);
		FieldNombre.setFont(FieldNombre.getFont().deriveFont(15f));
		contenido.add(FieldNombre);
		
		JTextField FieldCiudad = new JTextField();
		FieldCiudad.setBounds(220, 150, 150, 25);
		FieldCiudad.setFont(FieldCiudad.getFont().deriveFont(15f));
		contenido.add(FieldCiudad);
		
		JTextField FieldSuperficie = new JTextField();
		FieldSuperficie.setBounds(220, 200, 150, 25);
		FieldSuperficie.setFont(FieldSuperficie.getFont().deriveFont(15f));
		contenido.add(FieldSuperficie);
		
		JButton limpiar = new JButton("Limpiar");
		limpiar.setBounds(60, 300, 100, 30);
		limpiar.setFont(limpiar.getFont().deriveFont(15f));
		contenido.add(limpiar);
		limpiar.addActionListener(new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) {
			FieldNombre.setText("");
			FieldCiudad.setText("");
			FieldSuperficie.setText("");
			}
		});
		
		JButton agregar = new JButton("Agregar");
		agregar.setBounds(300, 300, 100, 30);
		agregar.setFont(agregar.getFont().deriveFont(15f));
		contenido.add(agregar);
		agregar.addActionListener(new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) {
			String nombre = FieldNombre.getText();
			String ciudad = FieldCiudad.getText();
			String superficie = FieldSuperficie.getText();
			if (nombre.isBlank() || ciudad.isBlank() || superficie.isBlank()) {
				JOptionPane.showMessageDialog(null, "Introduce datos en todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Database.insertarDatosSedes(nombre, ciudad, superficie);
			}
		});
		
		setSize(500,450);
	    setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
	}


}
