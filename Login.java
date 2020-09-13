import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.*;

public class Login extends JFrame implements ActionListener {
  private JPanel panel;
  private JLabel user_label, password_label, message;
  private JTextField userName_text;
  private JPasswordField password_text;
  private JButton submit, cancel;

  public Login() {
    // Username Label
    user_label = new JLabel();
    user_label.setText("Usuario :");
    userName_text = new JTextField();
    // Password Label
    password_label = new JLabel();
    password_label.setText("Contrsena :");
    password_text = new JPasswordField();
    // Submit
    submit = new JButton("Cifrar...");
    panel = new JPanel(new GridLayout(3, 1));
    panel.add(user_label);
    panel.add(userName_text);
    panel.add(password_label);
    panel.add(password_text);
    message = new JLabel();
    panel.add(message);
    panel.add(submit);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Adding the listeners to components..
    submit.addActionListener(this);
    add(panel, BorderLayout.CENTER);
    setTitle("Please Login Here !");
    setSize(450, 350);
    setVisible(true);
  }

  public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
      new Login();
      new RSAKeyPairGenerator().printKeysInTerminal();
   }
   @Override
   public void actionPerformed(ActionEvent ae) {
      String userName = userName_text.getText();
      String password = password_text.getText();
      if (userName.trim().equals("admin") && password.trim().equals("admin")) {
         message.setText(" Hello " + userName + "");
      } else {
         message.setText(" Invalid user.. ");
      }
   }
}