import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class signe {

	public static void main(String[] args) throws IOException {
		BigInteger n,p,q,a,b;
		int byteLength;
		String message = "";
		String empreinte = "";
		List<BigInteger> blocSigne;
		String fileName   = args[0] + ".priv";
		
		//Récuperer le message sur l'entrée standard
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = br.readLine()) != null) {
			message += line + "\n";
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
		
		//Ajouter l'empreinte
		empreinte = getHash(message, "SHA1");
		
		//Chiffrer l'empreinte avec la clé privé
		blocSigne = chiffre.chiffrer(empreinte, byteLength, a, n);
		
		System.out.println(" #Signature: ");
		
		//Enregistrer les séquences chiffrées dans un fichier texte
		PrintWriter writer = new PrintWriter(args[0]+".sign", "UTF-8");
		for (BigInteger bloc : blocSigne) {
			writer.println(bloc);
			System.out.print(bloc);
		}
		writer.close();
		System.out.println("");
		
	}
	
	/**
     * 
     * @param txt, text in plain format
     * @param hashType MD5 OR SHA1
     * @return hash in hashType 
     */
    public static String getHash(String txt, String hashType) {
        try {
                java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
                byte[] array = md.digest(txt.getBytes());
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < array.length; ++i) {
                    sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
             }
                return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            //error action
        }
        return null;
    }

    public static String md5(String txt) {
        return getHash(txt, "MD5");
    }

    public static String sha1(String txt) {
        return getHash(txt, "SHA1");
    }
}
