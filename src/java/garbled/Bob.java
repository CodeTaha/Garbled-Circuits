/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package garbled;

import static java.lang.Math.pow;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Taha
 */
public class Bob {
    static Alice alice;
    static int bob_input;
    public byte[][] Randoms;
    public byte[] k;
    public byte[] v=new byte[16];
    public byte[] preKb0=new byte[16];
    public byte[] preKb1=new byte[16];
    public Bob(Alice a)
    {
    alice=a;    
    }

    public void initiate_OT(int input) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        
        bob_input=input;
        Randoms=alice.getRandomValues();
        
        //k=rand.nextInt((1000 - 10) + 1) + 10;
        k = new byte[16];
        new Random().nextBytes(k);
       
        if(bob_input==0)
        {
            for(int i=0; i<k.length;i++)
            {
                v[i]=(byte) ((Randoms[0][i]+ Math.pow(k[i],Alice.publicKey))%Alice.n);
            }
        //v=(int) ((Randoms[0]+ Math.pow(k,alice.publicKey))%alice.n);
        }
        else
        {
            for(int i=0; i<k.length;i++)
            {
                v[i]=(byte) (Randoms[1][i]+Math.pow(k[i],Alice.publicKey)%Alice.n);
            }
        //v=(int) ((Randoms[1]+ Math.pow(k,alice.publicKey))%alice.n);  
        }
        
        System.out.println("v="+Arrays.toString(v));
        int r=alice.getM(v);
        recalculateBobsKey();
    }
    public void recalculateBobsKey()
    {
        for(int i=0;i<alice.m0.length;i++)
        {
            preKb0[i]=(byte) (alice.m0[i] - k[i]);
            preKb1[i]=(byte) (alice.m1[i] - k[i]);
        }
       
    }
    
    public int gbi()
    {
        return bob_input;
    }
}
