/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchencrypted;

import be.tarsos.lsh.Vector;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Alahram
 */
public class NewClass {
    byte[] skey = new byte[1000];
String skeyString;
static byte[] raw;
String inputMessage,encryptedData,decryptedMessage;
byte[] ibyte;
	//int offset;
	/* (non-Javadoc)
	 * @see be.hogent.tarsos.lsh.families.DistanceMeasure#distance(be.hogent.tarsos.lsh.Vector, be.hogent.tarsos.lsh.Vector)
	 */
	   String generateSymmetricKey() {
           
try {
    for(int i=0;i<3;i++){
Random r = new Random();
int num = r.nextInt(10000);
String knum = String.valueOf(num);
byte[] knumb = knum.getBytes();
skey=getRawKey(knumb);
skeyString = new String(skey);

System.out.println("AES Symmetric key = "+skeyString);
}}
catch(Exception e) {
System.out.println(e);
}return skeyString;
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
        public static void main(String[] args) {
            NewClass sd=new NewClass();
      //    sd. generateSymmetricKey();
         int offset;
          int offset1;
               Random rand = new Random(25);
		//this.w = w;
		offset = rand.nextInt();
                   Random rand1 = new Random(25);
                offset1 = rand1.nextInt();
             //   System.out.println("rand is:"+offset);
               //  System.out.println("rand1 is:"+offset1);
                 // Random r1 = new Random(3);
//        Random r2 = new Random(3);
//        int cd=r1.nextInt();
//        int dd=r2.nextInt();
      //  System.out.println("rand is:"+offset);
               //  System.out.println("rand1 is:"+offset1);
                 
                   Random rn = new Random();
                   
               int randomForIndexArray = rn.nextInt(9) + 0;
                System.out.println("rand1 is:"+randomForIndexArray);
                String heba=new String();
               byte[] h= heba.getBytes();
          int fd= Integer.valueOf(heba);
           // byte r=  5(h);
   byte[] array = new byte[7]; // length is bounded by 7
    new Random().nextBytes(array);
    String generatedString = new String(array, Charset.forName("UTF-8"));
 
    System.out.println("string is"+fd);                 
    }
       
        
        public String scrambleWord(String word){
        ArrayList<Character> chars = new ArrayList<Character>(word.length());
for ( char c : word.toCharArray() ) {
   chars.add(c);
}
Collections.shuffle(chars);
char[] shuffled = new char[chars.size()];
for ( int k = 0; k < shuffled.length; k++ ) {
   shuffled[k] = chars.get(k);
}
String shuffledWord = new String(shuffled);
return shuffledWord;
}
}