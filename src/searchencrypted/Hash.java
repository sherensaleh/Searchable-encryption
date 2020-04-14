/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchencrypted;

import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import src.utilABE.Files;
import src.utilABE.Utils;

public class Hash
{
  public static String MD2 = "MD2";
  public static String MD5 = "MD5";
  public static String SHA1 = "SHA-1";
  public static String SHA224 = "SHA-224";
  public static String SHA256 = "SHA-256";
  public static String SHA384 = "SHA-384";
  public static String SHA512 = "SHA-512";
  static double timing = 0.0D;
  
  private static String toHexadecimal(byte[] paramArrayOfByte)
  {
    String str = "";
    for (int k : paramArrayOfByte)
    {
      int m = k & 0xFF;
      if (Integer.toHexString(m).length() == 1) {
        str = str + "0";
      }
      str = str + Integer.toHexString(m);
    }
    return str;
  }
  
  public static byte[] getStringMessageDigest(byte[] paramArrayOfByte, String paramString)
  {
    byte[] arrayOfByte = null;
    try
    {
      long l1 = System.nanoTime();
      
      MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
      localMessageDigest.reset();
      localMessageDigest.update(paramArrayOfByte);
      arrayOfByte = localMessageDigest.digest();
      
      long l2 = System.nanoTime();
      timing = (l2 - l1) / 1.0E9D;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      System.out.println("Error creando Digest");
    }
    return arrayOfByte;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    String str1 = "PrivateLetter.pdf";
    
    byte[] arrayOfByte = Files.readBytesFromFile(str1);
    
    int[] arrayOfInt = { 80, 112, 128, 256 };
    
    String[] arrayOfString = { MD2, MD5, SHA1, SHA224, SHA256, SHA384, SHA512 };
    
    String str2 = "";
    for (int i = 0; i < arrayOfInt.length; i++)
    {
      String str3 = toHexadecimal(getStringMessageDigest(arrayOfByte, Utils.selectSha(arrayOfInt[i])));
      
      System.out.println(arrayOfInt[i] + ": " + timing);
    }
  }
}
