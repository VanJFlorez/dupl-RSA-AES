import java.util.Base64;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES with CBC mode
 */
public class AESUtils {
  private String key; // must be 16 bytes long
  private String initVector; // must be 16 bytes long

  public AESUtils() {
    key = getSixteenChars();
    initVector = getSixteenChars();
  };

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

  public String getKey() {
    return key;
  }

  public String getInitVector() {
    return initVector;
  }

  public String getSixteenChars() {
    char[] str = new char[16];
    Random rnd = new Random();
    for(int i = 0; i < 16; i++) {
      str[i] = (char) (rnd.nextInt(26) + 65);
    }
    return new String(str);
  }
}
