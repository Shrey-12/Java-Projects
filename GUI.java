import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class GUI {
    public static void main(String[] args) {
        new Application();
    }
}

class Application extends JFrame implements ActionListener {
    JButton button;
    JLabel label;
    JTextField inputTextField;
    JTextField outputTextField;

    Application() {
        super("Application");

        label = new JLabel("IP Address Finder");

        button = new JButton("Find IP");

        inputTextField = new JTextField();
        outputTextField = new JTextField();

        label.setBounds(140, 80, 110, 20);
        button.setBounds(140, 200, 100, 40);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.white);
        inputTextField.setBounds(140, 100, 100, 20);
        outputTextField.setBounds(140, 140, 100, 20);
        outputTextField.setEditable(false);


        (new JPanel()).setLayout(new GridLayout());

        add(button);
        add(label);
        add(inputTextField);
        add(outputTextField);

        button.addActionListener(this);

        setSize(400, 400);
        setLayout(null);
        setVisible(true);
        setBackground(Color.BLACK);
        setLocation(200, 200);
        setDefaultCloseOperation(Application.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent E) {
        inputTextField.setText("");

        String url = inputTextField.getText();

        try {
            InetAddress ia = InetAddress.getByName(url);
            String ip = ia.getHostAddress();

            outputTextField.setText(ip);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }
}