import java.io.*;
import java.util.*;
import java.math.*;

public class dh {
	public static void main(String[] args) {
	try {
		BigInteger ya,yb,key1,key2;
		int p,q,xa,xb;
		Scanner in = new Scanner(System.in);
		System.out.println("enter prime no P");
		p= in.nextInt();
		System.out.println("enter alpha");
		q=in.nextInt();
		
		System.out.println("enter private key of alice xa:");
		xa=in.nextInt();
		System.out.println("enter the private key of bob xb:");
		xb=in.nextInt();
		
		ya=BigInteger.valueOf(q).modPow(BigInteger.valueOf(xa),BigInteger.valueOf(p) );
		yb=BigInteger.valueOf(q).modPow(BigInteger.valueOf(xb),BigInteger.valueOf(p) );
		key1=yb.modPow(BigInteger.valueOf(xa),BigInteger.valueOf(p) );
		key2=ya.modPow(BigInteger.valueOf(xb),BigInteger.valueOf(p) );
		
		System.out.println("public key of alice:-->"+ya);
		System.out.println("public key of bob:-->" +yb);
		System.out.println("secret key of alice:-->"+key1);
		System.out.println("secret key of bob:-->"+key2);
	}
	catch(Exception e){
		System.out.println(e);
	}
	}
	}

