import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

public class gencle {

	public static BigInteger generatePrimeBigInteger(int bitLength) {
		Random rnd = new SecureRandom();
		BigInteger generatedValue = BigInteger.probablePrime( bitLength, rnd );
	    
	    return generatedValue;
	    
	}
	
	// ./gencle 512 jol
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		int bitLength = Integer.valueOf(args[0]);
		String fileName  = args[1];
		
		BigInteger n,p,q,a,b ;
		
		//Generate p and q
		p = generatePrimeBigInteger(bitLength/2);
		q = generatePrimeBigInteger(bitLength/2);
		n = p.multiply(q) ;
		
		System.out.println("  ## generating RSA keys for bloc size " + bitLength + "bits");
		System.out.println("  ## p,q pair generated. public n is: " );
		System.out.println("  ## " + n );
		
		//Generate b=2^16+1
		b = new BigInteger("2");
		b = b.pow(16).add(BigInteger.ONE);
		
		//Generate a = b^-1 modulo (p-1)(q-1)
		BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)) ;
		a = b.modInverse(phiN);
		
		System.out.println(" ## a,b pair generated. public b is");
		System.out.println(" ##" + b);
		
		//Check if a*b=1 mod phi(n)
		BigInteger ab = a.multiply(b);
		System.out.println("  ## check a*b=1 mod phi(n), a*b=" + ab.mod(phiN) );
		
		//Store keys in file t n p q a b
		// # Public/private RSA keys: t n p q a b
		
		PrintWriter writer = new PrintWriter(fileName+".priv", "UTF-8");
		String tnpqab = bitLength + "\t" + n + "\t" + p + "\t" + q + "\t" + a + "\t" + b ;
		writer.println(tnpqab);
		writer.close();
		System.out.println("  ## paire de cle privee stockee dans " + fileName + ".priv");
		
		PrintWriter writerPub = new PrintWriter(fileName+".pub", "UTF-8");
		String tnp = bitLength + "\t" + n + "\t" + b ;
		writerPub.println(tnp);
		writerPub.close();
		System.out.println("  ## paire de cle publique stockee dans " + fileName + ".pub");
		
	}
}
