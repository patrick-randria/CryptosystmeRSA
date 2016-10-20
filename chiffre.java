import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class chiffre {
	
	
	 
	
	public static void main(String[] args) throws IOException {
		
		BigInteger n,b;
		int byteLength;
		String message = "";
		String fileName   = args[0] + ".pub";
		int bytesTotal ;
		List<BigInteger> blocChiffre ;
		
		//Recuperer le message texte >> cat msg | chiffre 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line ;
		while ((line = br.readLine()) != null) {
			message += line + "\n";
		}
		bytesTotal = message.length() ;
		System.out.println(" ## Text to hash :");
		System.out.println(message);	
		
		
		//Récuperer les clés publiques
		Scanner in = new Scanner(new FileReader(fileName));
		byteLength = in.nextInt();
		String nStr = in.next();
		String bStr = in.next(); 
		n = new BigInteger(nStr);
		b = new BigInteger(bStr);
		
		
		//Chiffrer le texte clair
		blocChiffre = chiffrer(message, byteLength, b, n);
		
		//Enregistrer les séquences chiffrées dans un fichier texte
		PrintWriter writer = new PrintWriter(args[0]+".chiffre", "UTF-8");
		for (BigInteger bloc : blocChiffre) {
			writer.println(bloc);
		}
		writer.close();
		
		//Afficher log
		System.out.println("  ## " + bytesTotal + " bytes read, " + blocChiffre.size() + " blocs read");
	}
	
	public static List<BigInteger> chiffrer(String texteClair, int bitSize, BigInteger b, BigInteger n){
		List<BigInteger> blocChiffre = new ArrayList<BigInteger>() ;
		BigInteger  txtToBigInt = new BigInteger(texteClair.getBytes()); //Transformer le message en Big Int
		String 		txtToBinary = txtToBigInt.toString(2); //Transformer le bigInt en binaire puis cast en String
		
		/*
		 * Découper le message en bloc de T bits où T = byteLength
		 * Puis chiffrer par séquence (ECB)
		 */
		List<String> blocDeTBit = splitToBloc(txtToBinary, bitSize);
		
		for(String blocBinaire : blocDeTBit) {
			BigInteger x  = new BigInteger(blocBinaire,2);
			BigInteger ex =  x.modPow(b, n);
			System.out.println("    " + x );
			blocChiffre.add(ex);
		}
		
		
		return blocChiffre;
	}
	
	/*
	 * Découper un texte en bloc de T bits
	 */
	public static List<String> splitToBloc(String data, int bitSize) {
		List<String> listeBloc = new ArrayList<String>();
		while (data.length() > bitSize) {
			String bloc = data.substring(0, bitSize-1);
			listeBloc.add(bloc);
			data = data.substring(bitSize);
		}
		if (data.length() > 0) {
			listeBloc.add(data);
		}
		return listeBloc;
	}
	
	
}
