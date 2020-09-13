import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES with CBC mode
 */
public class AESUtils {
  private String key = "aesEncryptionKey"; // must be 16 bytes long
  private String initVector = "encryptionIntVec"; // must be 16 bytes long

  public AESUtils() {};

  public AESUtils(String key) {
    this.key = key;
  }  

  public String encrypt(String value) {
    try {
      IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
      SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
  
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
  
      byte[] encrypted = cipher.doFinal(value.getBytes());
      return Base64.getEncoder().encodeToString(encrypted);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  } 

  public String decrypt(String encrypted) {
    try {
      IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
      SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
  
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
      byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
  
      return new String(original);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  
    return null;
  }

  public static void main(String[] args) {
    AESUtils aesCipher = new AESUtils();
    String pt = "password";
    System.out.println(pt);
    String ct = aesCipher.encrypt(pt);
    System.out.println(ct);
    String dt = aesCipher.decrypt(ct);
    System.out.println(dt);
  }
}
