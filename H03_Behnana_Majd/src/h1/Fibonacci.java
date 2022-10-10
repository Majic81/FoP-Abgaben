package h1;


public class Fibonacci {

	// H1.2 Matrix Implementation


	/**
	 * The method fibMat calculates the fibonacci number´fn corresponding to the given index n recursively
	 * @param n the index of the fibonacci number, which should be calculated
	 * @return the n-fibonacci number fn, or -1 in case the given index is negative.
	 */
	public static int fibRek(int n) {
		if (n<0) {
			System.out.println("Error, please enter a positive number"); 
			return -1;
		}
		if (n==0) return 1;
		if (n==1) return 1;
		return (fibRek(n-1) + fibRek(n-2));	
	}




	/**
	 * This method gets an array as parameter and fills every field in the array with the corresponding fibonacci number 
	 * with the same index as the field  
	 * @param a the array of numbers which should be filled with fibonacci numbers
	 */
	public static void fibIt (int[] a) {
		for (int i = 0; i < a.length ; i++) {
			if (i == 0) a[i] = 1;
			else if (i == 1) a[i] = 1;
			else a[i] = a[i-1] + a[i-2];	
		}	
	}


	/**
	 * method fibSum gets an int parameter n and returns the fibonacci number fn using a sum form of binomial coefficients
	 * @param n the index of the fibonacci number, which should be returned 
	 * @return the fibonacci number fn corresponding to the given index n. 
	 */
	public static int fibSum (int n) {
		int fn = 0;
		if (n % 2 == 0) {
			for (int i = 1; i <= n/2; i++) {
				fn+= Util.binomialCoefficient(n-i, i-1);	
			}	 
		}
		else {
			for (int i = 1; i <= (n+1)/2; i++) {
				fn+= Util.binomialCoefficient(n-i, i-1);	
			}
		}
		return fn;
	}


	/**
	 * The method fibMat gets an integer number with the name index, and returns the corresponding fibonacci number to this index
	 * using a matrix multiplication method as follows:
	 *      n 
	 * |1 1|     | fn+1 fn  |
	 * |0 1|  =  | fn   fn-1|
	 * 
	 * @param index
	 * @return the corresponding fibonacci number to the given index 
	 */
	public static int fibMat(int index) {
		int m = 1;
		int[][] matrix = {{1,1},{1,0}};
		int[][] Ls = {{1,0},{0,1}};
		while(m <= index) {
			Ls = Util.matrixMultiplication(Ls, matrix);
		}	
		return Ls[0][1];
	}



	/**
	 * Method Collatz gets an integer parameter n and returns the number of the Collatz-Sequence elements beginning with the 
	 * given index n and ending with the number 1 after reaching the sequence (4, 2, 1). 
	 * 
	 * @param n the beginning index, with which the Collatz-Sequence should start. 
	 * @return the number of the elements of the Collatz-Sequence till reaching the end sequece of (4, 2, 1) including these
	 * three numbers in the counting process
	 */
	public static int collatz(int n) {
		int counter = 0;
		int index = 0;
		boolean finished = false; 	
		boolean startCountDown = false;

		while (!finished) {
			if (n == 4) startCountDown = true;
			if (startCountDown) {	
				index++;
			}
			if (index == 2) finished = true;
			if (n % 2 == 0) n /= 2; 
			else n = 3*n + 1;
			counter ++;
		}	
		return counter;
	}



	/**
	 * The method cycle gets a parameter int array, checks of any patterns which would repeat itself inside of the array without
	 * any interruptions and returns the length of the smallest pattern which can be identified. In case there is no pattern to be 
	 * found inside the array, the method returns -1.
	 * @param the given integer array for which a repeating pattern should be identified 
	 * @return the length of the smallest repeating pattern in case it exists, otherwise -1
	 */
	public static int cycle (int[] array) {
		int length = array.length;
		int patternLength= 1;
		int maximalPossibleLength = 0;
		boolean innerForLoop = true;
		boolean patternIsFound = true;
		
		if (length % 2 == 0) maximalPossibleLength = length / 2;
		else maximalPossibleLength = (length-1)/2;
		
		while (patternLength <= maximalPossibleLength) {
			patternIsFound = true;
			for (int i = 1; i <= length/patternLength-1; i++) {
				for (int j = 0; j < patternLength; j++) {
					if (array[j] != array[i*patternLength+j]) {
						patternLength++;
						while (length % patternLength != 0) patternLength++;
						innerForLoop = false;
						patternIsFound = false;
						break;
					}
				}
				if (!innerForLoop) break;
			}
			innerForLoop = true;
			if (patternIsFound) break;
		}		
		if (patternLength <= maximalPossibleLength) return patternLength;
		return -1;
	}
}



