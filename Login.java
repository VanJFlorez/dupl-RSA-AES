import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
  private static final long serialVersionUID = -2304015521704196526L;
  private JPanel panel;
  private JLabel user_label, password_label, message;
  private JTextField userName_text;
  private JPasswordField password_text;
  private JButton submit;

  private RSAUtils RSACipher;
  private AESUtils AESCipher;
      

  public Login() throws NoSuchAlgorithmException {
    RSAKeyPairGenerator kpgen = new RSAKeyPairGenerator();
    String privateKey = kpgen.getPrivateKey();
    String publicKey = kpgen.getPublicKey();
    
    this.RSACipher = new RSAUtils(publicKey, privateKey);;
    this.AESCipher = new AESUtils();

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
    
    String userNameRSAEncrypted = encryptRSA(userName);
    String passwordRSAEncrypted = encryptRSA(password);
    
    String userNameAESEncrypted = encryptAES(userName);
    String passwordAESEncrypted = encryptAES(password);
    // message.setText(" Hello " + userName + "");
    StringBuilder answer = new StringBuilder();
    answer.append("---------------------------------------------------------\n");
    answer.append("RSA\n");
    answer.append("---------------------------------------------------------\n");
    answer.append("TEXTO CLARO: \n");
    answer.append("usuario:" + userName + "\n");
    answer.append("contrasena:" + password + "\n");
    answer.append("TEXTO CIFRADO: " + "\n");
    answer.append("usuario:" + userNameRSAEncrypted + "\n");
    answer.append("contrasena:" + passwordRSAEncrypted + "\n");
    answer.append("llave pública: " + RSACipher.getPublicKey() + "\n");
    answer.append("llave privada: " + RSACipher.getPrivateKey() + "\n");
    answer.append("---------------------------------------------------------\n");
    answer.append("AES\n");
    answer.append("---------------------------------------------------------\n");
    answer.append("TEXTO CLARO: \n");
    answer.append("usuario:" + userName + "\n");
    answer.append("contrasena:" + password + "\n");
    answer.append("TEXTO CIFRADO: " + "\n");
    answer.append("usuario:" + userNameAESEncrypted + "\n");
    answer.append("contrasena:" + passwordAESEncrypted + "\n");
    answer.append("llave: " + AESCipher.getKey());
    JOptionPane.showMessageDialog(null, answer.toString());

    /**
     * FOR TEST PURPOSES
     */
    System.out.println(decryptRSA(userNameRSAEncrypted));
    System.out.println(decryptRSA(passwordRSAEncrypted));
    
    System.out.println(decryptAES(userNameAESEncrypted));
    System.out.println(decryptAES(passwordAESEncrypted));
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

  private String encryptAES(String plainText) {
    String cipherText = AESCipher.encrypt(plainText);
    return cipherText;
  }

  private String decryptAES(String cipherText) {
    String plainText = AESCipher.decrypt(cipherText);
    return plainText;
  }
}