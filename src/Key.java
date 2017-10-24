/**
 * This class represents the key and its' three versions
 * @author rotemsin
 *
 */
public class Key {
	
	private String key;
	private String key1;
	private String key2;
	private String key3;
	
	/**
	 * Key constructor
	 * @param input - the given key as a string
	 */
	public Key(String input) {
		this.key = input;
	}
	
	/**
	 * The getter for the key
	 * @param whatKey - the number of the key, 0 for the original key, 1-3 for the key 
	 * permutations
	 * @return
	 */
	public String getKey(int whatKey) {
		if (whatKey == 0) return key;
		if (whatKey == 1) return key1;
		if (whatKey == 2) return key2;
		if (whatKey == 3) return key3;
		return "";
	}
	
	/**
	 * This method performs the creation of the 3 versions of the key 
	 */
	public void produceKeys() {
		int[][] keyAfterPC1 = PC1();
		int[][] c0 = getCPart(keyAfterPC1);
		int[][] d0 = getDPart(keyAfterPC1);
		String c0AfterLeftShift = leftShift(fromMatrixToString(c0));
		String d0AfterLeftShift = leftShift(fromMatrixToString(d0));
		int[][] c1 = fromStringtoMatrix(c0AfterLeftShift);
		int[][] d1 = fromStringtoMatrix(d0AfterLeftShift);
		int[][] c1d1 = uniteMatrices(c1, d1);
		int[][] key1Matrix = PC2(fromMatrixToString(c1d1));
		key1 = fromMatrixToString(key1Matrix);
		//key 2
		String c1AfterLeftShift = leftShift(fromMatrixToString(c1));
		String d1AfterLeftShift = leftShift(fromMatrixToString(d1));
		int[][] c2 = fromStringtoMatrix(c1AfterLeftShift);
		int[][] d2 = fromStringtoMatrix(d1AfterLeftShift);
		int[][] c2d2 = uniteMatrices(c2, d2);
		int[][] key2Matrix = PC2(fromMatrixToString(c2d2));
		key2 = fromMatrixToString(key2Matrix);
		//key 3
		String c2AfterLeftShift = doubleLeftShift(fromMatrixToString(c2));
		String d2AfterLeftShift = doubleLeftShift(fromMatrixToString(d2));
		int[][] c3 = fromStringtoMatrix(c2AfterLeftShift);
		int[][] d3 = fromStringtoMatrix(d2AfterLeftShift);
		int[][] c3d3 = uniteMatrices(c3, d3);
		int[][] key3Matrix = PC2(fromMatrixToString(c3d3));
		key3 = fromMatrixToString(key3Matrix);
	}
	
	/**
	 * This method performs the PC1 action  
	 * @return - a matrix which is the result of the PC1 action
	 */
	public int[][] PC1() {
		int[][] result = new int[8][7];
		int lineIndex = 56;
		for (int j = 0; j < 7; j++) {
			result[0][j] = Character.getNumericValue(key.charAt(lineIndex));
			result[4][j] = Character.getNumericValue(key.charAt(lineIndex + 6));
			lineIndex -= 8;
		}
		result[1][0] = Character.getNumericValue(key.charAt(0));
		result[4][0] = Character.getNumericValue(key.charAt(6));
		lineIndex = 57;
		for (int j = 1; j < 7; j++) {
			result[1][j] = Character.getNumericValue(key.charAt(lineIndex));
			result[5][j] = Character.getNumericValue(key.charAt(lineIndex + 4));
			lineIndex -= 8;
		}
		result[2][0] = Character.getNumericValue(key.charAt(9));
		result[2][1] = Character.getNumericValue(key.charAt(1));
		result[5][0] = Character.getNumericValue(key.charAt(13));
		result[5][1] = Character.getNumericValue(key.charAt(5));
		lineIndex = 58;
		for (int j = 2; j < 7; j++) {
			result[2][j] = Character.getNumericValue(key.charAt(lineIndex));
			result[6][j] = Character.getNumericValue(key.charAt(lineIndex + 2));
			lineIndex -= 8;
		}
		result[3][0] = Character.getNumericValue(key.charAt(18));
		result[3][1] = Character.getNumericValue(key.charAt(10));
		result[3][2] = Character.getNumericValue(key.charAt(2));
		result[7][0] = Character.getNumericValue(key.charAt(20));
		result[7][1] = Character.getNumericValue(key.charAt(12));
		result[7][2] = Character.getNumericValue(key.charAt(4));
		lineIndex = 59;
		for (int j = 3; j < 7; j++) {
			result[3][j] = Character.getNumericValue(key.charAt(lineIndex));
			result[7][j] = Character.getNumericValue(key.charAt(lineIndex - 32));
			lineIndex -= 8;
		}
		return result;
	}
	
	/**
	 * A getter for the C part (the bottom part) of the key in its' matritional form
	 * @param key - the key in its' matritional form
	 * @return - the bottom part of the given matrix
	 */
	public int[][] getCPart(int[][] key) {
		int[][] result = new int[4][7];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 7; j++) {
				result[i][j] = key[i][j];
			}
		}
		return result;
	}
	
	/**
	 * A getter for the D part (the top part) of the key in its' matritional form
	 * @param key - the key in its' matritional form
	 * @return - the top part of the given matrix
	 */
	public int[][] getDPart(int[][] key) {
		int[][] result = new int[4][7];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 7; j++) {
				result[i][j] = key[i + 4][j];
			}
		}
		return result;
	}
	
	/**
	 * This method performs the PC2 action 
	 * @param input - the given key as a string 
	 * @return - a matrix which is the result of the PC2 action
	 */
	public int[][] PC2(String input) {
		int[][] result = new int[8][6];
		result[0][0] = Character.getNumericValue(input.charAt(13)); 
		result[0][1] = Character.getNumericValue(input.charAt(16));
		result[0][2] = Character.getNumericValue(input.charAt(10)); 
		result[0][3] = Character.getNumericValue(input.charAt(23));
		result[0][4] = Character.getNumericValue(input.charAt(0));  
		result[0][5] = Character.getNumericValue(input.charAt(4));
		result[1][0] = Character.getNumericValue(input.charAt(2));  
		result[1][1] = Character.getNumericValue(input.charAt(27));
		result[1][2] = Character.getNumericValue(input.charAt(14)); 
		result[1][3] = Character.getNumericValue(input.charAt(5));
		result[1][4] = Character.getNumericValue(input.charAt(20)); 
		result[1][5] = Character.getNumericValue(input.charAt(9));
		result[2][0] = Character.getNumericValue(input.charAt(22)); 
		result[2][1] = Character.getNumericValue(input.charAt(18));
		result[2][2] = Character.getNumericValue(input.charAt(11)); 
		result[2][3] = Character.getNumericValue(input.charAt(3));
		result[2][4] = Character.getNumericValue(input.charAt(25)); 
		result[2][5] = Character.getNumericValue(input.charAt(7));
		result[3][0] = Character.getNumericValue(input.charAt(15)); 
		result[3][1] = Character.getNumericValue(input.charAt(6));
		result[3][2] = Character.getNumericValue(input.charAt(26)); 
		result[3][3] = Character.getNumericValue(input.charAt(19));
		result[3][4] = Character.getNumericValue(input.charAt(12)); 
		result[3][5] = Character.getNumericValue(input.charAt(1));
		result[4][0] = Character.getNumericValue(input.charAt(40)); 
		result[4][1] = Character.getNumericValue(input.charAt(51));	
		result[4][2] = Character.getNumericValue(input.charAt(30)); 
		result[4][3] = Character.getNumericValue(input.charAt(36));	
		result[4][4] = Character.getNumericValue(input.charAt(46)); 
		result[4][5] = Character.getNumericValue(input.charAt(54));	
		result[5][0] = Character.getNumericValue(input.charAt(29)); 
		result[5][1] = Character.getNumericValue(input.charAt(39));
		result[5][2] = Character.getNumericValue(input.charAt(50)); 
		result[5][3] = Character.getNumericValue(input.charAt(44));
		result[5][4] = Character.getNumericValue(input.charAt(32)); 
		result[5][5] = Character.getNumericValue(input.charAt(47));
		result[6][0] = Character.getNumericValue(input.charAt(43)); 
		result[6][1] = Character.getNumericValue(input.charAt(48));
		result[6][2] = Character.getNumericValue(input.charAt(38)); 
		result[6][3] = Character.getNumericValue(input.charAt(55));
		result[6][4] = Character.getNumericValue(input.charAt(33)); 
		result[6][5] = Character.getNumericValue(input.charAt(52));
		result[7][0] = Character.getNumericValue(input.charAt(45)); 
		result[7][1] = Character.getNumericValue(input.charAt(41));
		result[7][2] = Character.getNumericValue(input.charAt(49)); 
		result[7][3] = Character.getNumericValue(input.charAt(35));
		result[7][4] = Character.getNumericValue(input.charAt(28)); 
		result[7][5] = Character.getNumericValue(input.charAt(31));
		return result;
	}
	
	/**
	 * This method shifts the key one place to the left
	 * @param input - the key as a string
	 * @return - the result the left shift operation
	 */
	public String leftShift(String input) {
		return input.substring(1) + input.charAt(0);
	}
	
	/**
	 * This method shifts the key two places to the left
	 * @param input - the key as a string
	 * @return - the result the double left shift operation
	 */
	public String doubleLeftShift(String input) {
		return input.substring(2) + input.substring(0, 2);
	}
	
	/**
	 * A helper method that converts a matrix to a string, row by row
	 * @param input - the given matrix
	 * @return - the string which is the result of the conversion from a matrix
	 */
	public String fromMatrixToString(int[][] input) {
		String result = "";
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				result += input[i][j];
			}
		}
		return result;
	}
	
	/**
	 * A helper method that converts a string to a 4x7 matrix, bit by bit
	 * @param str - the given string
	 * @return - the matrix which is the result of the conversion
	 */
	public int[][] fromStringtoMatrix(String str) {
		int[][] result = new int[4][7];
		int index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 7; j++) {
				result[i][j] = Character.getNumericValue(str.charAt(index));
				index++;
			}
		}
		return result;
	}
	
	/**
	 * This method creates a matrix which is the attachment of the top part matrix to the 
	 * bottom part matrix
	 * @param matrix1 - the top matrix
	 * @param matrix2 - the bottom matrix
	 * @return - the "attached" matrix
	 */
	public int[][] uniteMatrices(int[][] matrix1, int[][] matrix2) {
		int[][] result = new int[8][7];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 7; j++) {
				result[i][j] = matrix1[i][j];
			}
		}
		for (int i = 4; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				result[i][j] = matrix2[i - 4][j];
			}
		}
		return result;
	}

}
