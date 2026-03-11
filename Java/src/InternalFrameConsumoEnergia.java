import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InternalFrameConsumoEnergia extends InternalFrame {
    public InternalFrameConsumoEnergia() {

        JPanel contenido = new JPanel();
        contenido.setLayout(null);
        add(contenido);

        JLabel LabelIdSede = new JLabel("id_sede");
        LabelIdSede.setBounds(50, 150, 180, 30);
        LabelIdSede.setFont(new Font("Consolas", Font.BOLD, 20));
        LabelIdSede.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelIdSede);

        JLabel LabelKwhServidores = new JLabel("kwh_servidores");
        LabelKwhServidores.setBounds(50, 200, 180, 30);
        LabelKwhServidores.setFont(new Font("Consolas", Font.BOLD, 20));
        LabelKwhServidores.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelKwhServidores);

        JLabel LabelKwhRefrigeracion = new JLabel("kwh_refrigeracion");
        LabelKwhRefrigeracion.setBounds(50, 250, 180, 30);
        LabelKwhRefrigeracion.setFont(new Font("Consolas", Font.BOLD, 20));
        LabelKwhRefrigeracion.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelKwhRefrigeracion);

        JLabel LabelKwhTotales = new JLabel("kwh_totales");
        LabelKwhTotales.setBounds(50, 300, 180, 30);
        LabelKwhTotales.setFont(new Font("Consolas", Font.BOLD, 20));
        LabelKwhTotales.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelKwhTotales);

        JLabel LabelFecha = new JLabel("fecha");
        LabelFecha.setBounds(50, 350, 180, 30);
        LabelFecha.setFont(new Font("Consolas", Font.BOLD, 20));
        LabelFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelFecha);

        JTextField campoIdSede = new JTextField();
        campoIdSede.setBounds(250, 150, 150, 25);
        campoIdSede.setFont(campoIdSede.getFont().deriveFont(15f));
        contenido.add(campoIdSede);

        JTextField campoKwhServidores = new JTextField();
        campoKwhServidores.setBounds(250, 200, 150, 25);
        campoKwhServidores.setFont(campoKwhServidores.getFont().deriveFont(15f));
        contenido.add(campoKwhServidores);

        JTextField campoKwhRefrigeracion = new JTextField();
        campoKwhRefrigeracion.setBounds(250, 250, 150, 25);
        campoKwhRefrigeracion.setFont(campoKwhRefrigeracion.getFont().deriveFont(15f));
        contenido.add(campoKwhRefrigeracion);

        JTextField campoKwhTotales = new JTextField();
        campoKwhTotales.setBounds(250, 300, 150, 25);
        campoKwhTotales.setFont(campoKwhTotales.getFont().deriveFont(15f));
        contenido.add(campoKwhTotales);

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
                campoKwhServidores.setText("");
                campoKwhRefrigeracion.setText("");
                campoKwhTotales.setText("");
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
                String textoIdSede          = campoIdSede.getText();
                String textoKwhServidores   = campoKwhServidores.getText();
                String textoKwhRefrigeracion = campoKwhRefrigeracion.getText();
                String textoKwhTotales      = campoKwhTotales.getText();
                String textoFecha           = campoFecha.getText();

                if (textoIdSede.isBlank() || textoKwhServidores.isBlank() ||
                    textoKwhRefrigeracion.isBlank() || textoKwhTotales.isBlank() || textoFecha.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Introduce datos en todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id_sede           = Integer.parseInt(textoIdSede);
                int kwh_servidores    = Integer.parseInt(textoKwhServidores);
                int kwh_refrigeracion = Integer.parseInt(textoKwhRefrigeracion);
                int kwh_totales       = Integer.parseInt(textoKwhTotales);

                Database.insertarDatosConsumoEnergia(id_sede, kwh_servidores, kwh_refrigeracion, kwh_totales, textoFecha);
            }
        });

        setSize(550, 520);
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
    }
}