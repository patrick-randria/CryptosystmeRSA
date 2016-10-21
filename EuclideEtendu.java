import java.math.BigInteger;
/**
 * Classe pour les algorithme d'euclide etendu et la fonction qui fait la modInverse
 */
public class EuclideEtendu {

	private static final BigInteger ZERO = BigInteger.ZERO;
	private static final BigInteger ONE = BigInteger.ONE;
	private static final BigInteger ONENEGATIF = new BigInteger("-1");
	public EuclideEtendu() { }
	/**
	 * ecludeEtendu :Calcul de l'inverse en arithmétique modulaire
	 * @param a
	 * @param b
	 * @return array{r,u,v} tel que r = u*a + v*b = 1
	 */
	public static  BigInteger[] ecludeEtendue(BigInteger a, BigInteger b) {
		BigInteger[] ma = new BigInteger[] { a, ONE, ZERO};
		BigInteger[] na = new BigInteger[] { b, ZERO, ONE };
		BigInteger[] ta; // Temporary variable
		BigInteger q; // Quotient
		BigInteger r; // Rest

		if (a.compareTo(b) == -1) {
			ta = na;
			na = ma;
			ma = ta;
		}

		while (na[0].signum() == 1) {
			q = ma[0].divide(na[0]); // Quotient
			for (int i = 0; i < 3; i++) {
				r = ma[i].subtract(q.multiply(na[i]));
				ma[i] = na[i];
				na[i] = r;
			}

		}
		return ma;
	}
	/**
	 * modInverse 
	 * @param n
	 * @param mod
	 * @return
	 */
	public static BigInteger modInverse(BigInteger n, BigInteger mod) {
		BigInteger[] g = ecludeEtendue(mod, n);

		if (g[0].equals(ONE))
			return reduce(g[2], mod);
		else
			return ONENEGATIF;
	}
	/**
	 * 
	 * @param n
	 * @param mod
	 * @return
	 */
	public static  BigInteger reduce(BigInteger n, BigInteger mod) {
		BigInteger m = n.mod(mod);

		if (ZERO.compareTo(m) == 0 || ZERO.compareTo(m) == -1)
			return m;
		else
			return m.multiply(mod);
	}
	
	public static void main(String[] args) {
		BigInteger a, b  ;
		
		a 		= new BigInteger(args[0]);
		b 		= new BigInteger(args[1]);
		
		BigInteger[] tabEuclide = ecludeEtendue(a, b);
		
		// r = u*a + v*b = 1
		
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		
		System.out.println("r = u*a + v*b => " + tabEuclide[0] + " = " + tabEuclide[1] + "*" + a + " + " + tabEuclide[2] + "*" + b);
	}

}