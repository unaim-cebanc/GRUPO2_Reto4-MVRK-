import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InternalFrameServidores extends InternalFrame {
    public InternalFrameServidores() {

        JPanel contenido = new JPanel();
        contenido.setLayout(null);
        add(contenido);


        JLabel LabelIdSede = new JLabel("id_sede");
        LabelIdSede.setBounds(50, 150, 180, 30);
        LabelIdSede.setFont(new Font("Consolas", Font.BOLD, 20));
        LabelIdSede.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelIdSede);

        JLabel LabelNombreSala = new JLabel("nombre_sala");
        LabelNombreSala.setBounds(50, 200, 180, 30);
        LabelNombreSala.setFont(new Font("Consolas", Font.BOLD, 20));
        LabelNombreSala.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelNombreSala);

        JLabel LabelNumRacks = new JLabel("num_racks");
        LabelNumRacks.setBounds(50, 250, 180, 30);
        LabelNumRacks.setFont(new Font("Consolas", Font.BOLD, 20));
        LabelNumRacks.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelNumRacks);

        JLabel LabelSuperficie = new JLabel("superficie_m2");
        LabelSuperficie.setBounds(50, 300, 180, 30);
        LabelSuperficie.setFont(new Font("Consolas", Font.BOLD, 20));
        LabelSuperficie.setHorizontalAlignment(SwingConstants.RIGHT);
        contenido.add(LabelSuperficie);

        
        JTextField campoIdSede = new JTextField();
        campoIdSede.setBounds(250, 150, 150, 25);
        campoIdSede.setFont(campoIdSede.getFont().deriveFont(15f));
        contenido.add(campoIdSede);

        JTextField campoNombreSala = new JTextField();
        campoNombreSala.setBounds(250, 200, 150, 25);
        campoNombreSala.setFont(campoNombreSala.getFont().deriveFont(15f));
        contenido.add(campoNombreSala);

        JTextField campoNumRacks = new JTextField();
        campoNumRacks.setBounds(250, 250, 150, 25);
        campoNumRacks.setFont(campoNumRacks.getFont().deriveFont(15f));
        contenido.add(campoNumRacks);

        JTextField campoSuperficie = new JTextField();
        campoSuperficie.setBounds(250, 300, 150, 25);
        campoSuperficie.setFont(campoSuperficie.getFont().deriveFont(15f));
        contenido.add(campoSuperficie);

        JButton limpiar = new JButton("Limpiar");
        limpiar.setBounds(60, 370, 100, 30);
        limpiar.setFont(limpiar.getFont().deriveFont(15f));
        contenido.add(limpiar);
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoIdSede.setText("");
                campoNombreSala.setText("");
                campoNumRacks.setText("");
                campoSuperficie.setText("");
            }
        });

        JButton agregar = new JButton("Agregar");
        agregar.setBounds(300, 370, 100, 30);
        agregar.setFont(agregar.getFont().deriveFont(15f));
        contenido.add(agregar);
        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoIdSede    = campoIdSede.getText();
                String nombre_sala    = campoNombreSala.getText();
                String textoNumRacks  = campoNumRacks.getText();
                String textoSuperficie = campoSuperficie.getText();

                if (textoIdSede.isBlank() || nombre_sala.isBlank() || textoNumRacks.isBlank() || textoSuperficie.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Introduce datos en todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id_sede            = Integer.parseInt(textoIdSede);
                short num_racks        = Short.parseShort(textoNumRacks);  // SMALLINT → short
                int superficie_m2      = Integer.parseInt(textoSuperficie);

                Database.insertarDatosServidores(id_sede, nombre_sala, num_racks, superficie_m2);
            }
        });

        setSize(550, 470);
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
    }
}