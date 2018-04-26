package studio13;

import static org.junit.Assert.assertEquals;

public class DH {
	
	private final long privKey;
	public  final long base, modulus;
	private final MExp modexp;
	
	public DH(long base, long modulus,long privKey) {
		this.privKey = privKey;
		this.modulus = modulus;
		this.base    = base;
		this.modexp  = new MExp(base, modulus);
	}
	
	/**
	 * Compute the public key for the contained private key.
	 * As shown in lecture this is base to the privKey power mod modulus
	 * @return
	 */
	public long getPubKey() {
		return modexp.toThePower(privKey);
	}
	
	/**
	 * Compute Diffie--Hellman agreement:  raise the other agent's public key
	 *   to this private key power, mod the common modulus.
	 * @param otherPubKey
	 * @return
	 */
	public long getAgreedNum(long otherPubKey) {
		return MExp.gToTheXModP(otherPubKey, privKey, modulus);
	}

	public static void main(String[] args) {
		testLectureExample();
		int agreedBase = 5;
		int agreedModulus = 17;
		int mySecret = 11;
		int otherPubKey = 3;
		DH ok = new DH(agreedBase, agreedModulus, mySecret);
		long publicKey = ok.getPubKey();
		long sharedKey = ok.getAgreedNum(otherPubKey);
		
		System.out.println(publicKey + " " + sharedKey + " " + otherPubKey);
	}

	public static void testLectureExample() {
		DH alice = new DH(5,23,6);
		DH bob = new DH(5,23,15);
		assertEquals("Alice's public key should be 8 for private key 6", 8,  alice.getPubKey());
		assertEquals("Bob's public key should be 19 for private key 15", 19, bob.getPubKey());
		assertEquals("Alice and Bob agree on 2", 2,  alice.getAgreedNum(19));
		assertEquals("Alice and Bob agree on 2", 2,  bob.getAgreedNum(8));
	}
	
}
