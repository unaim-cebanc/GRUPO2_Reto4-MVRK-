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

public class InternalFrameEmisiones extends InternalFrame {
   
	/**
	 * Constructor del internal frame para insertar datos en la tabla de emisiones
	 */
	public InternalFrameEmisiones() {

        JPanel contenido = new JPanel();
        contenido.setLayout(null);
        add(contenido);

        JLabel LabelIdSede = new JLabel("id_sede");
        LabelIdSede.setBounds(50, 150, 180, 30);
        LabelIdSede.setFont(new Font("Consolas", Font.BOLD, 15));
        LabelIdSede.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelIdSede);

        JLabel LabelTipoEmision = new JLabel("tipo_emision");
        LabelTipoEmision.setBounds(50, 200, 180, 30);
        LabelTipoEmision.setFont(new Font("Consolas", Font.BOLD, 15));
        LabelTipoEmision.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelTipoEmision);

        JLabel LabelCantidad = new JLabel("cantidad_toneladas");
        LabelCantidad.setBounds(50, 250, 180, 30);
        LabelCantidad.setFont(new Font("Consolas", Font.BOLD, 15));
        LabelCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelCantidad);

        JLabel LabelFuente = new JLabel("fuente");
        LabelFuente.setBounds(50, 300, 180, 30);
        LabelFuente.setFont(new Font("Consolas", Font.BOLD, 15));
        LabelFuente.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelFuente);

        JLabel LabelFecha = new JLabel("fecha");
        LabelFecha.setBounds(50, 350, 180, 30);
        LabelFecha.setFont(new Font("Consolas", Font.BOLD, 15));
        LabelFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelFecha);

        JTextField campoIdSede = new JTextField();
        campoIdSede.setBounds(250, 150, 150, 25);
        campoIdSede.setFont(campoIdSede.getFont().deriveFont(15f));
        contenido.add(campoIdSede);

        JTextField campoTipoEmision = new JTextField();
        campoTipoEmision.setBounds(250, 200, 150, 25);
        campoTipoEmision.setFont(campoTipoEmision.getFont().deriveFont(15f));
        contenido.add(campoTipoEmision);

        JTextField campoCantidad = new JTextField();
        campoCantidad.setBounds(250, 250, 150, 25);
        campoCantidad.setFont(campoCantidad.getFont().deriveFont(15f));
        contenido.add(campoCantidad);

        JTextField campoFuente = new JTextField();
        campoFuente.setBounds(250, 300, 150, 25);
        campoFuente.setFont(campoFuente.getFont().deriveFont(15f));
        contenido.add(campoFuente);

        JTextField campoFecha = new JTextField();
        campoFecha.setBounds(250, 350, 150, 25);
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
                campoTipoEmision.setText("");
                campoCantidad.setText("");
                campoFuente.setText("");
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
                String textoIdSede      = campoIdSede.getText();
                String tipo_emision     = campoTipoEmision.getText();
                String textoCantidad    = campoCantidad.getText();
                String fuente           = campoFuente.getText();
                String textoFecha       = campoFecha.getText();

                if (textoIdSede.isBlank() || tipo_emision.isBlank() ||
                    textoCantidad.isBlank() || fuente.isBlank() || textoFecha.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Introduce datos en todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                int id_sede            = Integer.parseInt(textoIdSede);
                int cantidad_toneladas = Integer.parseInt(textoCantidad);

                Database.insertarDatosEmisiones(id_sede, tipo_emision, cantidad_toneladas, fuente, textoFecha);
            }
        });

        setSize(550, 520);
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
    }
}