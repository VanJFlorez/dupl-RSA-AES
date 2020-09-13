import java.io.IOException;
import java.security.*;
import java.util.Base64;

public class RSAKeyPairGenerator {

  private PrivateKey privateKey;
  private PublicKey publicKey;

  public RSAKeyPairGenerator() throws NoSuchAlgorithmException {
      KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
      keyGen.initialize(1024);
      KeyPair pair = keyGen.generateKeyPair();
      this.privateKey = pair.getPrivate();
      this.publicKey = pair.getPublic();
  }

  public PrivateKey getPrivateKey() {
      return privateKey;
  }

  public PublicKey getPublicKey() {
      return publicKey;
  }
  
  public void printKeysInTerminal() throws NoSuchAlgorithmException, IOException {
      System.out.println("###########################");
      System.out.println(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
      System.out.println("###########################");
      System.out.println(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
  }
}