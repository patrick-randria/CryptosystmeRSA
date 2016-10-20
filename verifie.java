import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class verifie {

	public static void main(String[] args) throws IOException {
			
			BigInteger n,b;
			int byteLength;
			String message   = "";
			String empreinte ;
			String signatureDechiffree ;
			String ficClePub    = args[0] + ".pub";
			String ficSignature = args[1] ;
			List<BigInteger> listeSignature = new ArrayList<BigInteger>() ;
			
			//Recuperer le message depuis l'entrée standard 
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line ;
			while ((line = br.readLine()) != null) {
				message += line + "\n";
			}
			empreinte = signe.getHash(message, "SHA1");
			
			
			//Récuperer les clés publiques
			Scanner in = new Scanner(new FileReader(ficClePub));
			byteLength = in.nextInt();
			String nStr = in.next();
			String bStr = in.next(); 
			n = new BigInteger(nStr);
			b = new BigInteger(bStr);
			
			//Recuperer la signature
			Scanner inSignature = new Scanner(new FileReader(ficSignature));
			while (inSignature.hasNextLine()) {
                listeSignature.add(new BigInteger(inSignature.nextLine()));
			}
			
			//Déchiffrer la signature
			signatureDechiffree = dechiffre.dechiffrer(listeSignature, byteLength, b, n);
			
			if(empreinte.equals(signatureDechiffree)) {
				System.out.println("Signature verifie !");
			} else {
				System.out.println("Le fichier-signature n'est pas la signature du message!");
			}
			
			System.out.println("Empreinte  : " + empreinte);
			System.out.println("SignatureD : " + signatureDechiffree);
	}
}
