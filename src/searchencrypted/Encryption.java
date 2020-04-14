package SearchEncrypted;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

 public class Encryption {
byte[] skey = new byte[1000];
String skeyString;

String encryptedData,decryptedMessage;
byte[] ibyte;

public Encryption(String filename,byte[] raw,String name) {
try {


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
byte[] ebyte=encrypt(raw, ibyte,name);

//String encryptedData = new String(ebyte);
//System.out.println("Encrypted message "+encryptedData);
//JOptionPane.showMessageDialog(null,"Encrypted Data "+"\n"+encryptedData);

//byte[] dbyte= decrypt(raw,ebyte);
//String decryptedMessage = new String(dbyte);
//System.out.println("Decrypted message "+decryptedMessage);
//
//JOptionPane.showMessageDialog(null,"Decrypted Data "+"\n"+decryptedMessage);
}
catch(Exception e) {
System.out.println(e);
}

}


public  static byte[] encrypt(byte[] raw, byte[] clear,String filename) throws Exception {
SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
Cipher cipher = Cipher.getInstance("AES");
cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
byte[] encrypted = cipher.doFinal(clear);
FileOutputStream fileOuputStream = new FileOutputStream("C:\\Users\\Alahram\\Desktop\\latestttttt\\latestttttt\\latest\\SearchEncryptedMaster\\src\\ServerSide\\"+filename);
fileOuputStream.write(encrypted);
fileOuputStream.close();
return encrypted;
}



}