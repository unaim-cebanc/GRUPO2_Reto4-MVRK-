package main;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InternalFrameResiduos extends InternalFrame {
    
	/**
	 * Constructor del internal frame para insertar datos en la tabla de residuos
	 */
	public InternalFrameResiduos() {

        JPanel contenido = new JPanel();
        contenido.setLayout(null);
        add(contenido);


        JLabel LabelIdSede = new JLabel("id_sede");
        LabelIdSede.setBounds(50, 150, 150, 30);
        LabelIdSede.setFont(new Font("Consolas", Font.BOLD, 15));
        LabelIdSede.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelIdSede);

        JLabel LabelTipoResiduo = new JLabel("tipo_residuos");
        LabelTipoResiduo.setBounds(50, 200, 150, 30);
        LabelTipoResiduo.setFont(new Font("Consolas", Font.BOLD, 15));
        LabelTipoResiduo.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelTipoResiduo);

        JLabel LabelCantidad = new JLabel("cantidad_kg");
        LabelCantidad.setBounds(50, 250, 150, 30);
        LabelCantidad.setFont(new Font("Consolas", Font.BOLD, 15));
        LabelCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelCantidad);

        JLabel LabelMetodo = new JLabel("metodo_gestión");
        LabelMetodo.setBounds(50, 300, 150, 30);
        LabelMetodo.setFont(new Font("Consolas", Font.BOLD, 15));
        LabelMetodo.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelMetodo);

        JLabel LabelFecha = new JLabel("fecha");
        LabelFecha.setBounds(50, 350, 150, 30);
        LabelFecha.setFont(new Font("Consolas", Font.BOLD, 15));
        LabelFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelFecha);

        JTextField campoIdSede = new JTextField();
        campoIdSede.setBounds(220, 150, 150, 25);
        campoIdSede.setFont(campoIdSede.getFont().deriveFont(15f));
        contenido.add(campoIdSede);

        JTextField campoTipoResiduo = new JTextField();
        campoTipoResiduo.setBounds(220, 200, 150, 25);
        campoTipoResiduo.setFont(campoTipoResiduo.getFont().deriveFont(15f));
        contenido.add(campoTipoResiduo);

        JTextField campoCantidad = new JTextField();
        campoCantidad.setBounds(220, 250, 150, 25);
        campoCantidad.setFont(campoCantidad.getFont().deriveFont(15f));
        contenido.add(campoCantidad);

        JTextField campoMetodo = new JTextField();
        campoMetodo.setBounds(220, 300, 150, 25);
        campoMetodo.setFont(campoMetodo.getFont().deriveFont(15f));
        contenido.add(campoMetodo);

        JTextField campoFecha = new JTextField();
        campoFecha.setBounds(220, 350, 150, 25);
        campoFecha.setFont(campoFecha.getFont().deriveFont(15f));
        contenido.add(campoFecha);

        JButton limpiar = new JButton("Limpiar");
        limpiar.setBounds(60, 420, 100, 30);
        limpiar.setFont(limpiar.getFont().deriveFont(15f));
        contenido.add(limpiar);
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoIdSede.setText("");
                campoTipoResiduo.setText("");
                campoCantidad.setText("");
                campoMetodo.setText("");
                campoFecha.setText("");
            }
        });

        JButton agregar = new JButton("Agregar");
        agregar.setBounds(300, 420, 100, 30);
        agregar.setFont(agregar.getFont().deriveFont(15f));
        contenido.add(agregar);
        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoIdSede    = campoIdSede.getText();
                String tipo_residuos  = campoTipoResiduo.getText();
                String textoCantidad  = campoCantidad.getText();
                String metodo_gestion = campoMetodo.getText();
                String fecha          = campoFecha.getText();

                if (textoIdSede.isBlank() || tipo_residuos.isBlank() ||
                    textoCantidad.isBlank() || metodo_gestion.isBlank() || fecha.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Introduce datos en todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Convertir a int los campos numéricos
                int id_sede      = Integer.parseInt(textoIdSede);
                int cantidad_kg  = Integer.parseInt(textoCantidad);

                Database.insertarDatosResiduos(id_sede, tipo_residuos, cantidad_kg, metodo_gestion, fecha);
            }
        });

        setSize(550, 520);
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
    }
}