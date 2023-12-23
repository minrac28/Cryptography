import java.util.*;
class shift{
public static void main(String [] args){
try{
Scanner s = new Scanner(System.in);
String pt="";
String ct="";
String pt1="";
char x;
int i,k;

System.out.println("enter the plaintext:");
pt=s.nextLine();
pt=pt.toLowerCase();

System.out.println("enter key");
k=s.nextInt();
k=k%26;

for (i=0; i<pt.length(); i++){
	x=(char)(pt.charAt(i)+ k);
	if(x>'z')
		x-=26;
		ct +=x;
}
System.out.println("cipertext::"+ct);

for (i=0;i<ct.length();i++){
	x=(char)(ct.charAt(i)- k);
	if(x<'a')
		x+=26;
		pt1 +=x;
}
System.out.println("decrypted text::"+pt1);
}
catch(Exception e){
System.out.println(e);
}
}
}
