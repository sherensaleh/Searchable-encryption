/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchencrypted;

import com.sun.xml.internal.ws.api.message.saaj.SAAJFactory;
import java.util.Random;

/**
 *
 * @author Alahram
 */
public class NewClass1 {
      public static void main(String[] args){
     Random rn = new Random();
        int answer = rn.nextInt(9)+1;
       System.out.println("answer is:"+answer);
    
}
}