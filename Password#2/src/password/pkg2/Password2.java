package password.pkg2;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;


public class Password2 extends JFrame {
    // Componentes de la Interfaz
    private JTextField txtLongitud;
    private JCheckBox chkNumeros, chkLetras, chkSimbolos;
    private JTextField txtContrasena;
    private JButton btnGenerar;

    public Password2() {
        // Inicializa la interfaz
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Generador de Contraseña");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel lblLongitud = new JLabel("Longitud (entre 8 y 12 caracteres):");
        lblLongitud.setBounds(20, 20, 250, 30);
        add(lblLongitud);

        txtLongitud = new JTextField();
        txtLongitud.setBounds(250, 20, 50, 30);
        add(txtLongitud);

        chkNumeros = new JCheckBox("Incluye Números?");
        chkNumeros.setBounds(20, 60, 200, 30);
        add(chkNumeros);

        chkLetras = new JCheckBox("Incluye Letras?");
        chkLetras.setBounds(20, 90, 200, 30);
        add(chkLetras);

        chkSimbolos = new JCheckBox("Incluye Símbolos?");
        chkSimbolos.setBounds(20, 120, 200, 30);
        add(chkSimbolos);

        JLabel lblContrasena = new JLabel("Contraseña Generada:");
        lblContrasena.setBounds(20, 160, 200, 30);
        add(lblContrasena);

        txtContrasena = new JTextField();
        txtContrasena.setBounds(20, 190, 300, 30);
        txtContrasena.setEditable(false);
        add(txtContrasena);

        btnGenerar = new JButton("Generar Contraseña");
        btnGenerar.setBounds(20, 230, 200, 30);
        add(btnGenerar);

        // Evento del botón
        btnGenerar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generarContrasena();
            }
        });
    }

    private void generarContrasena() {
        int longitud;
        try {
            longitud = Integer.parseInt(txtLongitud.getText());
            if (longitud < 8 || longitud > 12) {
                JOptionPane.showMessageDialog(this, "La longitud debe estar entre 8 y 12 caracteres.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido.");
            return;
        }

        String caracteres = "";
        if (chkLetras.isSelected()) 
            caracteres += "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (chkNumeros.isSelected()) 
            caracteres += "0123456789";
        if (chkSimbolos.isSelected()) 
            caracteres += "!@#$%^&*()_+";

        if (caracteres.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos un tipo de carácter.");
            return;
        }

        StringBuilder contrasena = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(caracteres.length());
            contrasena.append(caracteres.charAt(indice));
        }

        txtContrasena.setText(contrasena.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Password2().setVisible(true);
        });
    }
}