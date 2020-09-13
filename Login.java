import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
  private JPanel panel;
  private JLabel user_label, password_label, message;
  private JTextField userName_text;
  private JPasswordField password_text;
  private JButton submit, cancel;

  private RSAUtils RSACipher;
      

  public Login() throws NoSuchAlgorithmException {
    RSAKeyPairGenerator kpgen = new RSAKeyPairGenerator();
    String privateKey = kpgen.getPrivateKey();
    String publicKey = kpgen.getPublicKey();
    this.RSACipher = new RSAUtils(publicKey, privateKey);;

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

  public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException,
      BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException {
      new Login();

   }

  @Override
  public void actionPerformed(ActionEvent ae) {
    String userName = userName_text.getText();
    String password = password_text.getText();
    String userNameEncrypted = encryptRSA(userName);
    String passwordEncrypted = encryptRSA(password);
    // message.setText(" Hello " + userName + "");
    StringBuilder answer = new StringBuilder();
    answer.append("---------------------------------------------------------\n");
    answer.append("RSA\n");
    answer.append("---------------------------------------------------------\n");
    answer.append("TEXTO CLARO: \n");
    answer.append("usuario:" + userName + "\n");
    answer.append("contrasena:" + password + "\n");
    answer.append("TEXTO CIFRADO: " + "\n");
    answer.append("usuario:" + userNameEncrypted + "\n");
    answer.append("contrasena:" + passwordEncrypted + "\n");
    answer.append("llave pública: " + RSACipher.getPublicKey() + "\n");
    JOptionPane.showMessageDialog(null, answer.toString());

    /**
     * FOR TEST PURPOSES
     */
    System.out.println(decryptRSA(userNameEncrypted));
  }

  private String encryptRSA(String plainText) {
    try {
      String cipherText = RSACipher.encrypt(plainText);
      return cipherText;
    } catch (Exception e) {
      return "ERROR!!!!!!";
    }
  }

  private String decryptRSA(String cipherText) {
    try {
      String plainText = RSACipher.decrypt(cipherText);
      return plainText;
    } catch (Exception e) {
      return "ERROR!!!!!!!!!!";
    }
  }

  private String encryptAES(String plainTest) {
    return null;
  }
}