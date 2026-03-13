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


/**
 * Internal Frame que contiene los elementos para insertar datos a la tabla de sistemas de refrigeracion
 * @author Unax
 */
public class InternalFrameRefrigeracion extends InternalFrame {
    
	/**
	 * Constructor del internal frame para insertar datos en la tabla de sistemas de refrigeracion
	 */
	public InternalFrameRefrigeracion() {

        JPanel contenido = new JPanel();
        contenido.setLayout(null);
        add(contenido);

        JLabel LabelIdSala = new JLabel("id_sala_servidores");
        LabelIdSala.setBounds(50, 150, 200, 30);
        LabelIdSala.setFont(new Font("Consolas", Font.BOLD, 15));
        LabelIdSala.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelIdSala);

        JLabel LabelTipoSistema = new JLabel("tipo_sistema");
        LabelTipoSistema.setBounds(50, 200, 200, 30);
        LabelTipoSistema.setFont(new Font("Consolas", Font.BOLD, 15));
        LabelTipoSistema.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelTipoSistema);

        JLabel LabelEficiencia = new JLabel("eficiencia_energetica");
        LabelEficiencia.setBounds(50, 250, 200, 30);
        LabelEficiencia.setFont(new Font("Consolas", Font.BOLD, 15));
        LabelEficiencia.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelEficiencia);

        JLabel LabelRefrigerante = new JLabel("refrigerante");
        LabelRefrigerante.setBounds(50, 300, 200, 30);
        LabelRefrigerante.setFont(new Font("Consolas", Font.BOLD, 15));
        LabelRefrigerante.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelRefrigerante);

        JTextField campoIdSala = new JTextField();
        campoIdSala.setBounds(270, 150, 150, 25);
        campoIdSala.setFont(campoIdSala.getFont().deriveFont(15f));
        contenido.add(campoIdSala);

        JTextField campoTipoSistema = new JTextField();
        campoTipoSistema.setBounds(270, 200, 150, 25);
        campoTipoSistema.setFont(campoTipoSistema.getFont().deriveFont(15f));
        contenido.add(campoTipoSistema);

        JTextField campoEficiencia = new JTextField();
        campoEficiencia.setBounds(270, 250, 150, 25);
        campoEficiencia.setFont(campoEficiencia.getFont().deriveFont(15f));
        contenido.add(campoEficiencia);

        JTextField campoRefrigerante = new JTextField();
        campoRefrigerante.setBounds(270, 300, 150, 25);
        campoRefrigerante.setFont(campoRefrigerante.getFont().deriveFont(15f));
        contenido.add(campoRefrigerante);

        JButton limpiar = new JButton("Limpiar");
        limpiar.setBounds(60, 370, 100, 30);
        limpiar.setFont(limpiar.getFont().deriveFont(15f));
        contenido.add(limpiar);
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoIdSala.setText("");
                campoTipoSistema.setText("");
                campoEficiencia.setText("");
                campoRefrigerante.setText("");
            }
        });

        JButton agregar = new JButton("Agregar");
        agregar.setBounds(300, 370, 100, 30);
        agregar.setFont(agregar.getFont().deriveFont(15f));
        contenido.add(agregar);
        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoIdSala     = campoIdSala.getText();
                String tipo_sistema    = campoTipoSistema.getText();
                String eficiencia      = campoEficiencia.getText();
                String refrigerante    = campoRefrigerante.getText();

                if (textoIdSala.isBlank() || tipo_sistema.isBlank() ||
                    eficiencia.isBlank() || refrigerante.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Introduce datos en todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id_sala_servidores = Integer.parseInt(textoIdSala);

                Database.insertarDatosRefrigeracion(id_sala_servidores, tipo_sistema, eficiencia, refrigerante);
            }
        });

        setSize(550, 460);
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
    }
}