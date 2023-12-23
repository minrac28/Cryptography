
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class BlowfishFileEncryptionDemo {
	
	static BigInteger ya,yb,key1,key2;
	private static String  z ;

    private static final String ALGORITHM = "Blowfish";
    private static String key;
    private static final String SAMPLE_FILE_PATH = 
            "/C:\\Users\\Admin\\OneDrive\\Desktop\\\\minaz.pdf";
    private static final String ENCRYPTED_FILE_PATH = 
            "/C:\\\\Users\\\\Admin\\\\OneDrive\\\\Desktop\\\\file.pdf";
    private static final String DECRYPTED_FILE_PATH = 
            "/C:\\\\Users\\\\Admin\\\\OneDrive\\\\Desktop\\\\decryptedfile.pdf";
    
    private static final String demoencrypt= 
            "/C:\\\\Users\\\\Admin\\\\OneDrive\\\\Desktop\\\\file1.pdf";
    private static final String demodecrypt = 
            "/C:\\\\Users\\\\Admin\\\\OneDrive\\\\Desktop\\\\ddecryptedfile1.pdf";

    public static void main(String[] args) {
    	
    	long startTime = System.nanoTime();
    	BlowfishFileEncryptionDemo obj = new BlowfishFileEncryptionDemo();

        File sampleFile = new File(SAMPLE_FILE_PATH);
        File encryptedFile = new File(ENCRYPTED_FILE_PATH);
        File encryptedFile1 = new  File (demodecrypt );
        File decryptedFile = new File(DECRYPTED_FILE_PATH);
        File  decryptedFile1 = new  File (demodecrypt );

        try {
        	
        	int p,q,xa,xb;
        	Scanner s =new Scanner(System.in);
        	 p=7919;
        	 q=453;
 
        	System.out.println("enter private key of user1 xa:");
        	xa=s.nextInt();
        	System.out.println("enter private key of user2 xb:");
        	xb=s.nextInt();
        	
        	
        	ya =  BigInteger.valueOf(q).modPow(BigInteger.valueOf(xa), BigInteger.valueOf(p));
        	System.out.println("public key of user1:" +ya);
        	yb =  BigInteger.valueOf(q).modPow(BigInteger.valueOf(xb), BigInteger.valueOf(p));
            System.out.println("public key of user2:" +yb);
            
            key1 =yb.modPow(BigInteger.valueOf(xa), BigInteger.valueOf(p));
            System.out.println("shared key of user1:" +key1);
            key2 =ya.modPow(BigInteger.valueOf(xb), BigInteger.valueOf(p));
            System.out.println("shared key of user2:" +key2);
            
            final String key3=String.valueOf(key2);
            
            // BlowfishFileEncryptionDemo.encrypt1(sampleFile, encryptedFile1); 
            long start = System.nanoTime();
            obj.encrypt1(sampleFile, encryptedFile1);
            long end = System.nanoTime();
            long execution = end - start;
            System.out.println("Execution time of encryption using shared key : " + execution/1000000 +"ms");
          
           // BlowfishFileEncryptionDemo.encrypt(encryptedFile1, encryptedFile);
            long start1 = System.nanoTime();
            obj.encrypt(encryptedFile1, encryptedFile);
            long end1 = System.nanoTime();
            long execution1 = end1 - start1;
            System.out.println("Execution time of encryption using blowfish key : " + execution1/1000000 +"ms");
            
            //BlowfishFileEncryptionDemo.decrypt(encryptedFile, decryptedFile1);
            long start2 = System.nanoTime();
            obj.decrypt(encryptedFile, decryptedFile1);
            long end2 = System.nanoTime();
            long execution2 = end2 - start2;
            System.out.println("Execution time of decryption using blowfish key : " + execution2/1000000 +"ms");
            
            //BlowfishFileEncryptionDemo.decrypt1(decryptedFile1, decryptedFile);
            long start3 = System.nanoTime();
            obj.decrypt1(decryptedFile1, decryptedFile);
            long end3 = System.nanoTime();
            long execution3 = end3 - start3;
            System.out.println("Execution time of decryption using shared key : " + execution3/1000000 +"ms");
            
            //execution time of full program
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("Execution time to complete process: " + timeElapsed / 1000000+"ms");
           
            }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //-----------------------------------------------------------------------
    //shared key encryption
    //encrption of file
    public static void encrypt1(File sampleFile, File outputFile)
            throws Exception {
        doCrypto(Cipher.ENCRYPT_MODE, sampleFile, outputFile);

    }

    //decryprion of file
    
    public static void decrypt1(File sampleFile, File outputFile)
            throws Exception {
    	doCrypto(Cipher.DECRYPT_MODE, sampleFile, outputFile);

    }
    
  private static void doCrypto(int cipherMode, File sampleFile,File outputFile) throws Exception {
	  
	    final String shkey =String.valueOf(key1);
	  	final byte[] KeyData = shkey.getBytes();
	  	//System.out.println("blowfish key from diffie hellman--" +KeyData);  		
	  	SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
	  	
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(cipherMode, KS);
	
	   
	    /*Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
	    String z=secretKey.toString();
     Cipher cipher = Cipher.getInstance(ALGORITHM);
     cipher.init(cipherMode, secretKey);*/
      
      InputStream inputStream = new FileInputStream(sampleFile);
      byte[] inputBytes = new byte[(int) sampleFile.length()];
      inputStream.read(inputBytes);

      byte[] outputBytes = cipher.doFinal(inputBytes);

      OutputStream outputStream = new FileOutputStream(outputFile);
      outputStream.write(outputBytes);

      inputStream.close();
      outputStream.close();
      
  }
  
  
  //---------------------------------------------------------------------------------------------------------------
  //blowfish encrption
  public static void encrypt(File sampleFile, File outputFile)
          throws Exception {
      doCrypto1(Cipher.ENCRYPT_MODE, sampleFile, outputFile);

  }

  //decryprion of file
  
  public static void decrypt(File sampleFile, File outputFile)
          throws Exception {
  	doCrypto1(Cipher.DECRYPT_MODE, sampleFile, outputFile);

  }
  
private static void doCrypto1(int cipherMode, File encryptedFile1,File outputFile) throws Exception {
	    key="minaz";
	    Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
	    String z=secretKey.getEncoded().toString();
	    //System.out.println("blowfishkey--" +z);
	    Cipher cipher = Cipher.getInstance(ALGORITHM);
	    cipher.init(cipherMode, secretKey);
    
	    InputStream inputStream = new FileInputStream(encryptedFile1);
	    byte[] inputBytes = new byte[(int) encryptedFile1.length()];
	    inputStream.read(inputBytes);

  
	    byte[] outputBytes = cipher.doFinal(inputBytes);

	    OutputStream outputStream = new FileOutputStream(outputFile);
    	outputStream.write(outputBytes);

    	inputStream.close();
    	outputStream.close();
    
}}