package SearchEncrypted;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

 public class Decryption {
byte[] skey = new byte[1000];
String skeyString;
//public static String filename="src\\papers\\ffdecryption.txt";
String encryptedData,decryptedMessage;
byte[] ibyte;
public Decryption(String filename,byte[] raw,String name) {
try {

 //filename="src\\papers\\Eecryption\\"+name;
//inputMessage=JOptionPane.showInputDialog(null,"Enter message to encrypt");
 File file = new File(filename);
// read data in files
 ibyte = new byte[(int) file.length()];
         try {
               FileInputStream fileInputStream = new FileInputStream(file);
               fileInputStream.read(ibyte);
               for (int i = 0; i < ibyte.length; i++) {
                           System.out.print((char)ibyte[i]);
                }
          } catch (FileNotFoundException e) {
                      System.out.println("File Not Found.");
                      e.printStackTrace();
          }
          catch (IOException e1) {
                   System.out.println("Error Reading The File.");
                    e1.printStackTrace();
          }

 //
//byte[] ebyte=encrypt(raw, ibyte);

//String encryptedData = new String(ebyte);
//System.out.println("Encrypted message "+encryptedData);
//JOptionPane.showMessageDialog(null,"Encrypted Data "+"\n"+encryptedData);

byte[] dbyte= decrypt(raw,ibyte,name);
//String decryptedMessage = new String(dbyte);
//System.out.println("Decrypted message "+decryptedMessage);
//
//JOptionPane.showMessageDialog(null,"Decrypted Data "+"\n"+decryptedMessage);
}
catch(Exception e) {
System.out.println(e);
}

}


public static byte[] decrypt(byte[] raw, byte[] encrypted,String name) throws Exception {
SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
Cipher cipher = Cipher.getInstance("AES");
cipher.init(Cipher.DECRYPT_MODE, skeySpec);
byte[] decrypted = cipher.doFinal(encrypted);
FileOutputStream fileOuputStream = new FileOutputStream("C:\\Users\\Alahram\\Desktop\\latestttttt\\latestttttt\\latest\\SearchEncryptedMaster\\src\\DecryptedSide\\"+name);
fileOuputStream.write(decrypted);
fileOuputStream.close();
return decrypted;
}


}