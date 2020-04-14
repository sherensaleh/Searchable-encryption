/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchencrypted;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random ;
import java.util.logging.Level;
import java.util.logging.Logger;

class AES {
byte[] skey = new byte[1000];
String skeyString;
static byte[] raw;
String inputMessage,encryptedData,decryptedMessage;
byte[] ibyte; 

public AES(String filename,String name) {
try {
generateSymmetricKey();

//inputMessage=JOptionPane.showInputDialog(null,"Enter message to encrypt");
//[] ibyte = inputMessage.getBytes();

 File file = new File(filename);
// read data in files
 ibyte = new byte[(int) file.length()];
         try {
               FileInputStream fileInputStream = new FileInputStream(file);
               fileInputStream.read(ibyte);
//               for (int i = 0; i < ibyte.length; i++) {
//                           System.out.print((char)ibyte[i]);
//                }
          } catch (FileNotFoundException e) {
                      System.out.println("File Not Found.");
                      e.printStackTrace();
          }
          catch (IOException e1) {
                   System.out.println("Error Reading The File.");
                    e1.printStackTrace();
          }
byte[] ebyte=encrypt(ibyte,name);
//(String filename,byte[] raw,String name)
//String encryptedData = new String(ebyte);
//System.out.println("Encrypted message "+encryptedData);
//JOptionPane.showMessageDialog(null,"Encrypted Data "+"\n"+encryptedData);

byte[] dbyte= decrypt(ebyte,name);
//String decryptedMessage = new String(dbyte);
//System.out.println("Decrypted message "+decryptedMessage);
//
//JOptionPane.showMessageDialog(null,"Decrypted Data "+"\n"+decryptedMessage);
}
catch(Exception e) {
System.out.println(e);
}

}
void generateSymmetricKey() {
try {
Random r = new Random();
int num = r.nextInt(10000);
String knum = String.valueOf(num);
byte[] knumb = knum.getBytes();
skey=getRawKey(knumb);
skeyString = new String(skey);
//System.out.println("AES Symmetric key = "+skeyString);
}
catch(Exception e) {
System.out.println(e);
}
}
private static byte[] getRawKey(byte[] seed) throws Exception {
KeyGenerator kgen = KeyGenerator.getInstance("AES");
SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
sr.setSeed(seed);
kgen.init(128, sr); // 192 and 256 bits may not be available
SecretKey skey = kgen.generateKey();
raw = skey.getEncoded();
return raw;
}
private static byte[] encrypt(byte[] clear,String filename) throws Exception {
SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
Cipher cipher = Cipher.getInstance("AES");
cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
byte[] encrypted = cipher.doFinal(clear);
FileOutputStream fileOuputStream = new FileOutputStream("C:\\Users\\Alahram\\Desktop\\latestttttt\\latestttttt\\latest\\SearchEncryptedMaster\\src\\ServerSide\\"+filename);
fileOuputStream.write(encrypted);
fileOuputStream.close();
return encrypted;
}

private static byte[] decrypt(byte[] encrypted,String name) throws Exception {
SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
Cipher cipher = Cipher.getInstance("AES");
cipher.init(Cipher.DECRYPT_MODE, skeySpec);
byte[] decrypted = cipher.doFinal(encrypted);
FileOutputStream fileOuputStream = new FileOutputStream("C:\\Users\\Alahram\\Desktop\\latestttttt\\latestttttt\\latest\\SearchEncryptedMaster\\src\\DecryptedSide\\"+name);
fileOuputStream.write(decrypted);
fileOuputStream.close();
return decrypted;
}

//public static void main(String args[]) {
//     File folder = new File("C:\\Users\\ibraheem\\Desktop\\latest\\SearchEncryptedMaster\\src\\papers\\");
//            File[] files = folder.listFiles();
//          //  List<String> fileNames = new ArrayList<>();
//
//            for (int i = 0; i < files.length; i++) {
//                AES aes = new AES(files[i].getAbsolutePath(),files[i].getName());  
//                // startTime = System.currentTimeMillis();
//               // System.out.println("------------" + files[i].getName());
////                Decryption e = new Decryption(files[i].getAbsolutePath(), raw, files[i].getName());
////                try {
////                           
////                    //re.Read("C:\\Users\\ibraheem\\Desktop\\latest\\SearchEncryptedMaster\\src\\papers\\" + files[i].getName(), files[i].getName());
////                    //fileNames.add(files[i].getName());
////                    
////                } catch (Exception ex) {
////                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
////                }
//
//}
//}
}