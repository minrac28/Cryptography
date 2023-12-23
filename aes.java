import java.util.*;
import javax.crypto.*;

public class aes{
	private static Scanner in;

	public static void main(String[] args) {
		try {
			in = new Scanner (System.in);
			System.out.println("enter the text to  encryp");
			String pt =in.nextLine();
			
			KeyGenerator kg = KeyGenerator.getInstance("AES");
			kg.init(128);
			SecretKey k = kg.generateKey();
			Cipher c =Cipher.getInstance("AES");
			
			byte[] pb1 = pt.getBytes();
			c.init(Cipher.ENCRYPT_MODE, k);
			byte[] cb1= c.doFinal(pb1);
			System.out.println("encrypted text:"+ new String(cb1));
			
			c.init(Cipher.DECRYPT_MODE, k);
			byte [] pb2 = c.doFinal(cb1);
			System.out.println("decrytped text:"+new String(pb2));
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	}