import java.util.*;
import java.math.*;
public class rsa{
	public static void main(String[] args){
		
		int p = 7;
		int q = 5;
		int n = p * q;
		int phi = (p - 1) * ( q - 1);
		int e = 0;
		for(e = 2; e < phi; e++)
			if(gcd(e,phi) == 1)
				break;
		System.out.println("Public key (e,n): " + e + "," + n);	
		int d = 0;
		for(int i = 0; i < phi ; i++){
			int x = 1 + (i * phi);
			if(x % e == 0){
				d = x / e;
				break;
			}}
		System.out.println("Private key(d,n): " + d + "," + n);
		
		Scanner in = new Scanner(System.in);
		System.out.println("enter text");
		String plainText = in.nextLine();
		//encryption
		String cipherText ="";
		for(int i = 0; i < plainText.length(); i++){
			char pt = plainText.charAt(i);
			BigInteger ct = BigInteger.valueOf(pt).modPow(BigInteger.valueOf(e),BigInteger.valueOf(n));
			cipherText += (char)ct.intValue();
		}
		System.out.println("Cipher Text is: " + cipherText);
		//decrptioin
		String decryptedText = "";
		for(int i = 0; i < cipherText.length(); i++){
			char ct = cipherText.charAt(i);
			BigInteger dt = BigInteger.valueOf(ct).modPow(BigInteger.valueOf(d),BigInteger.valueOf(n));
			decryptedText += (char)dt.intValue();
		}
		System.out.println("Decrypted Text is: " + decryptedText);	
}
	
	static int gcd(int e, int phi){
		if(e == 0)
			return phi;
		else
			return gcd(phi % e, e);
	}
	}