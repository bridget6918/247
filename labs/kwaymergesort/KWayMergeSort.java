package kwaymergesort;

import java.util.Arrays;

import timing.Ticker;

public class KWayMergeSort {

	/**
	 * 
	 * @param K some positive power of 2.
	 * @param input an array of unsorted integers.  Its size is either 1, or some other power of 2 that is at least K
	 * @param ticker call .tick() on this to account for the work you do
	 * @return
	 */
	public static Integer[] kwaymergesort(int K, Integer[] input, Ticker ticker) {
		int n = input.length;

		Integer[] ans = new Integer[n];

		if(n <= 1) { // if n<=1, just return the input array itself - this is also the base case
			return input;
		}
		else { // split by filling the two dimensional array
			Integer[][] arrays = new Integer[K][n/K];
			ticker.tick();
			for(int i=0; i < n; ++i) {
				arrays[i/(n/K)][i%(n/K)] = input[i]; // i/(n/K) = rows, % gives the column by calculating the modulus
				ticker.tick();
			}
			for(int i=0; i < K; ++i) {
				arrays[i] = kwaymergesort(K, arrays[i], ticker); // recursively call kwaymergesort to sort each array
				ticker.tick();
			}
			ans = merge(arrays, ticker);
			ticker.tick();
		}

		return ans;
	}

	public static Integer[] merge(Integer[][] sorted, Ticker ticker) {
		int row = sorted.length; 
		int col = sorted[0].length; 
		int[] index = new int[row]; // index for arrays to be merged
		Integer [] output = new Integer[row*col]; // final output
		ticker.tick(5);

		for(int i=0; i < row*col; ++i) {
			int a = Integer.MAX_VALUE; // this is the min
			int b = 0; // will change as we sort
			ticker.tick(2);
			for(int j=0; j < row; ++j) {
				if(index[j] < col) {
					ticker.tick(2);
					if(sorted[j][index[j]] < a) { // [j] gives the # of subarray, while [index[j]] gives the element tracked by the index array
						a = sorted[j][index[j]];
						b = j;
						ticker.tick(3);
					}
				}
			}
			index[b]++;
			output[i] = a;
			ticker.tick(2);
		}
		return output;
	}

}
