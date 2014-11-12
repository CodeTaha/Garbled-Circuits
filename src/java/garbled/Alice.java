/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package garbled;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author Taha
 */
public class Alice {
        
        static SecretKey Ka0 ;
        static SecretKey Ka1; 
        static SecretKey Kb0 ;
        static SecretKey Kb1 ;
        public static int publicKey=5;//361;
        public static int n=91;//589;
        public static int privateKey=29;//181;
        public static SecretKey Alice_key;
        public byte[][] Randoms=new byte[2][16];
        String Kw0="For 0 output" ;
        String Kw1="For 1 output";
        public ArrayList truthTable=new ArrayList();
        public byte[] m0,m1;
        
    public Alice(int alice_input) throws NoSuchPaddingException, InvalidKeyException
    {
        System.out.println("Creating Keys and Tables");
            KeyGenerator keyGen;
        try {
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
         Ka0 = keyGen.generateKey();
         Ka1 = keyGen.generateKey();
         Kb0 = keyGen.generateKey();
         Kb1 = keyGen.generateKey();
        
        
        switch(alice_input)
        {
            case 0:Alice_key=Ka0;break;
            case 1:Alice_key=Ka1;break;
        }
       
         
        
        System.out.println(" ka0="+Ka0.toString()+" ka1="+Ka1.toString());
        System.out.println(" kb0="+Kb0.toString()+" ka1="+Kb1.toString());
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Exception= "+ex);
        
            
    }
    }
        @SuppressWarnings("empty-statement")
    public void generateTable() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {
       
        SecretKey [][]XOR_tbl=new SecretKey[4][2];
        String output[]=new String[4];
        XOR_tbl[0][0]=Ka0; XOR_tbl[0][1]=Kb0;output[0]=Kw0;
        XOR_tbl[1][0]=Ka0; XOR_tbl[1][1]=Kb1;output[1]=Kw1;
        XOR_tbl[2][0]=Ka1; XOR_tbl[2][1]=Kb0;output[2]=Kw1;
        XOR_tbl[3][0]=Ka1; XOR_tbl[3][1]=Kb1;output[3]=Kw0;
        
        for(int i=0;i<4;i++)
        {
             ArrayList truthTableArr=new ArrayList();
             truthTableArr.add(XOR_tbl[i][0]);
             truthTableArr.add(XOR_tbl[i][1]);
             truthTableArr.add(encryptString(XOR_tbl[i][0],XOR_tbl[i][1],output[i]));
             //truthTable.add(XOR_tbl[i][0]);
             //truthTable.add(XOR_tbl[i][1]);
             truthTable.add(truthTableArr);
             
             /*
        byte[] byteDataToEncrypt = strDataToEncrypt.getBytes();
        byte[] byteCipherText = aesCipher.doFinal(byteDataToEncrypt); 
        String strCipherText = new BASE64Encoder().encode(byteCipherText);
        System.out.println("cipher text: " +strCipherText);*/
        }
       
    }
    
    public byte[] encryptString(SecretKey a1, SecretKey a2, String a3) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {
             //SecretKey strDataToEncrypt=a3;
             Cipher aesCipher = Cipher.getInstance("AES");
             aesCipher.init(Cipher.ENCRYPT_MODE, a2);
             byte[] byteDataToEncrypt = a3.getBytes();
             byte[] byteCipherText = aesCipher.doFinal(byteDataToEncrypt);
            
             aesCipher.init(Cipher.ENCRYPT_MODE, a1);
             byteCipherText = aesCipher.doFinal(byteCipherText);
            
             //System.out.println("Encryption="+Arrays.toString(byteCipherText));
             String s = new String(byteCipherText);
             System.out.println("Encryption final="+s);
             return byteCipherText;
    }

    public byte[][] getRandomValues() {
       //Randoms=new int[2];
       //Random rand = new Random();
       //Randoms[0]=rand.nextInt((1000 - 10) + 1) + 10;
       //Randoms[1]=rand.nextInt((1000 - 10) + 1) + 10;
       
       byte[] b1 = new byte[16];
        new Random().nextBytes(b1);
        byte[] b2 = new byte[16];
        new Random().nextBytes(b2);
        Randoms[0]=b1;
        Randoms[1]=b2;
       return Randoms;
    }

    public int getM(byte[] v) {
       
        m0=new byte[Kb0.getEncoded().length];
        m1=new byte[Kb1.getEncoded().length];
        byte[] b = new byte[16];
        new Random().nextBytes(b);
        System.out.println(Arrays.toString(b));
        for(int i=0;i<m1.length; i++)
        {
            m0[i]=(byte) (Kb0.getEncoded()[i]+Math.pow((v[i]-Randoms[0][i]),privateKey)%n);
            m1[i]=(byte) (Kb1.getEncoded()[i]+Math.pow((v[i]-Randoms[1][i]),privateKey)%n);
        }
       return 0;
    }
    
    public byte[] gk(int in)
    {
        if(in==0)
        {
            return Kb0.getEncoded();
        }
        else
        {
            return Kb1.getEncoded();
        }
    }
}
