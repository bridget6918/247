package rabinkarp;

public class RK {

	//
	// Be sure to look at the write up for this assignment
	//  so that you get full credit by satisfying all
	//  of its requirements
	//

	private int m;
	private int[] cb;
	private int pointer;
	private int exp = 1;
	private int hash;

	// Be sure to look at the write up for this assignment
	//  so that you get full credit by satisfying all
	//  of its requirements
	//


	/**
	 * Rabin-Karp string matching for a window of the specified size
	 * @param m size of the window
	 */
	public RK(int m) {
		this.m = m;
		this.cb = new int [m];
		this.pointer = 0;
		for (int j = 0; j<m; j++){
			exp = (exp*31) % 511;
		}
		this.hash = 0;
	}


	/**
	 * Compute the rolling hash for the previous m-1 characters with d appended.
	 * @param d the next character in the target string
	 * @return
	 */


	public int nextCh(char d) {
		// c is about to leave the window and d is about to be incorporated
		// two conditions -1 if cb fits all char strings and -2 if cb is full and need to reset pointer (circulate back to the beginning)
		int dv = (int)d;
		if (pointer < m) {
			int cv = (exp*cb[pointer]) % 511;
			hash = (((hash*31) % 511 + dv % 511) % 511 - cv) % 511;
		}
		else{ 
			pointer = 0;
			int cv = (exp*cb[pointer]) % 511;
			hash = (((hash*31) % 511 + dv % 511) % 511 - cv) % 511;
		}
		
		if (hash < 0){
			hash += 511;
		}
		
		cb[pointer] = dv;
		pointer++;
		return hash;
	}

}
