import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InternalFrameEmpleados extends InternalFrame {
    public InternalFrameEmpleados() {

        JPanel contenido = new JPanel();
        contenido.setLayout(null);
        add(contenido);

        JLabel LabelIdGerente = new JLabel("id_empleado_gerente");
        LabelIdGerente.setBounds(50, 150, 180, 30);
        LabelIdGerente.setFont(new Font("Consolas", Font.BOLD, 20));
        LabelIdGerente.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelIdGerente);

        JLabel LabelIdSede = new JLabel("id_sede");
        LabelIdSede.setBounds(50, 200, 180, 30);
        LabelIdSede.setFont(new Font("Consolas", Font.BOLD, 20));
        LabelIdSede.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelIdSede);

        JLabel LabelNombre = new JLabel("nombre");
        LabelNombre.setBounds(50, 250, 180, 30);
        LabelNombre.setFont(new Font("Consolas", Font.BOLD, 20));
        LabelNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelNombre);

        JLabel LabelApellido = new JLabel("apellido");
        LabelApellido.setBounds(50, 300, 180, 30);
        LabelApellido.setFont(new Font("Consolas", Font.BOLD, 20));
        LabelApellido.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelApellido);

        JLabel LabelFecha = new JLabel("fecha_contrato");
        LabelFecha.setBounds(50, 350, 180, 30);
        LabelFecha.setFont(new Font("Consolas", Font.BOLD, 20));
        LabelFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelFecha);

        JTextField campoIdGerente = new JTextField();
        campoIdGerente.setBounds(250, 150, 150, 25);
        campoIdGerente.setFont(campoIdGerente.getFont().deriveFont(15f));
        contenido.add(campoIdGerente);

        JTextField campoIdSede = new JTextField();
        campoIdSede.setBounds(250, 200, 150, 25);
        campoIdSede.setFont(campoIdSede.getFont().deriveFont(15f));
        contenido.add(campoIdSede);

        JTextField campoNombre = new JTextField();
        campoNombre.setBounds(250, 250, 150, 25);
        campoNombre.setFont(campoNombre.getFont().deriveFont(15f));
        contenido.add(campoNombre);

        JTextField campoApellido = new JTextField();
        campoApellido.setBounds(250, 300, 150, 25);
        campoApellido.setFont(campoApellido.getFont().deriveFont(15f));
        contenido.add(campoApellido);

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
                campoIdGerente.setText("");
                campoIdSede.setText("");
                campoNombre.setText("");
                campoApellido.setText("");
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
                String textoIdGerente  = campoIdGerente.getText();
                String textoIdSede     = campoIdSede.getText();
                String nombre          = campoNombre.getText();
                String apellido        = campoApellido.getText();
                String textoFecha      = campoFecha.getText();

                if (textoIdGerente.isBlank() || textoIdSede.isBlank() ||
                    nombre.isBlank() || apellido.isBlank() || textoFecha.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Introduce datos en todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id_empleado_gerente = Integer.parseInt(textoIdGerente);
                int id_sede             = Integer.parseInt(textoIdSede);

                Database.insertarDatosEmpleados(id_empleado_gerente, id_sede, nombre, apellido, textoFecha);
            }
        });

        setSize(550, 520);
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
    }
}