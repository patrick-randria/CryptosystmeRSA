import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class dechiffre {
	
	
	public static void main(String[] args) throws IOException {
		BigInteger n,p,q,a,b;
		int byteLength;
		List<BigInteger> blocChiffre ;
		String texteClair = "";
		String fileName   = args[0] + ".priv";
		
		//Récuperer les séquences chiffrées et les enregistrer dans un arraylist
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		blocChiffre = new ArrayList<BigInteger>();
		String line;
		while ((line = br.readLine()) != null) {
			blocChiffre.add(new BigInteger(line));
		}
		
		// Récuperer les clés privées depuis le fichier .priv
		Scanner in = new Scanner(new FileReader(fileName));
		byteLength = in.nextInt();
		String nStr = in.next();
		String pStr = in.next();
		String qStr = in.next();
		String aStr = in.next();
		String bStr = in.next(); 
		n = new BigInteger(nStr);
		p = new BigInteger(pStr);
		q = new BigInteger(qStr);
		a = new BigInteger(aStr);
		b = new BigInteger(bStr);
		
		//déchiffrement par séquence
		texteClair = dechiffrer(blocChiffre, byteLength, a, n);
		
		System.out.println("");
		System.out.println(texteClair);
		
	}
	
	public static String dechiffrer(List<BigInteger> blocChiffre, int bitSize, BigInteger a, BigInteger n){
		String txtToBinary = "" ;
		for (BigInteger bloc : blocChiffre) {
			
			BigInteger dy 		= bloc.modPow(a, n);
			String binaireTmp 	= dy.toString(2);
			//Mode ECB / S'assurer qu'on a un bloc de T bits
			while(binaireTmp.length() < bitSize) { binaireTmp = "0" + binaireTmp; }
			txtToBinary += binaireTmp;
			System.out.println("    " + dy);
		}
		
		BigInteger txtToBigInt = new BigInteger(txtToBinary,2);
		String 	   texteClair  = new String(txtToBigInt.toByteArray());
		
		return texteClair;
	}

	
}
