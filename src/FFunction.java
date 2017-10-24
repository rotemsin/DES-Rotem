/**
 * This class represents the F function in DES
 * @author rotemsin
 *
 */
public class FFunction {
	
	private int[][] matrix;
	private String key;
	
	/**
	 * FFunction constructor
	 * @param matrix - the input matrix
	 * @param key - the input key
	 */
	public FFunction(int[][] matrix, String key) {
		this.matrix = matrix;
		this.key = key;
	}
	
	/**
	 * The main function of this class that invokes the functions of the class
	 * @return - the eventual matrix computed by the F function
	 */
	public int[][] produceResult() {
		int[][] eFunctionResult = eFunction(fromMatrixToString(matrix));
		String xorResult = xor(fromMatrixToString(eFunctionResult), key);
		String sBoxesResult = computeAllSBoxes(xorResult);
		int[][] permutationResult = permutation(sBoxesResult);
		return permutationResult;
	}
	
	/**
	 * The E function part of the F function
	 * @param str - the given string
	 * @return - a 8x6 matrix which is the expansion of the string
	 */
	public int[][] eFunction(String str) {
		int[][] result = new int[8][6];
		int index = -1;
		for (int i = 0; i < 8; i++) {
			int lineIndex = index;
			for (int j = 0; j < 6; j++) {
				if ((i == 0) && (j == 0)) 
					result[i][j] = Character.getNumericValue(str.charAt(31));
				else if ((i == 7) && (j == 5)) 
					result[i][j] = Character.getNumericValue(str.charAt(0));
				else
					result[i][j] = Character.getNumericValue(str.charAt(lineIndex));
				lineIndex++;
			}
			index += 4;
		}
		return result;
	}
	
	/**
	 * This function performs the xor operation between the 2 strings
	 * @param str1 - the first string
	 * @param str2 - the second string
	 * @return - the xor between the 2 strings
	 */
	public String xor(String str1, String str2) {
		String result = "";
		for (int i = 0; i < 48; i++) {
			result += Character.getNumericValue(str1.charAt(i)) ^ 
					  Character.getNumericValue(str2.charAt(i));
		}
		return result;
	}
	
	/**
	 * This function invokes the operation of all the 8 SBoxes
	 * @param input - the given string
	 * @return - the string which is the result of the computation of all the SBoxes.
	 */
	public String computeAllSBoxes(String input) {
		String result = "";
		int index = 0;
		for (int i = 1; i <= 8; i++) {
			String subStr = sBox(input.substring(index, index + 6), i);
			if (subStr.length() == 1) 
				subStr = "000" + subStr;
			if (subStr.length() == 2) 
				subStr = "00" + subStr;
			if (subStr.length() == 3) 
				subStr = "0" + subStr;
			result += subStr;
			index += 6;
		}
		return result;
	}
	
	/**
	 * This function performs the computation of a single SBox
	 * @param input - the given string
	 * @param number - the index of the SBox, a number between 1 and 8
	 * @return - the string which represents the computation of an SBox.
	 */
	public String sBox(String input, int number) {
		String binaryRow = input.substring(0, 1) + input.substring(5, 6);
		int row = Integer.parseInt(binaryRow, 2);
		String binaryColumn = input.substring(1, 5);
		int column = Integer.parseInt(binaryColumn, 2);
		SBox sBox = new SBox(number);
		int result = sBox.getBox()[row][column];
		return Integer.toBinaryString(result);
	}
	
	/**
	 * the final permutation of the F function
	 * @param input - the given input
	 * @return - the matrix after the permutation
	 */
	public int[][] permutation(String input) {
		int[][] result = new int[8][4];
		result[0][0] = Character.getNumericValue(input.charAt(15));
		result[0][1] = Character.getNumericValue(input.charAt(6));
		result[0][2] = Character.getNumericValue(input.charAt(19));
		result[0][3] = Character.getNumericValue(input.charAt(20));
		result[1][0] = Character.getNumericValue(input.charAt(28));
		result[1][1] = Character.getNumericValue(input.charAt(11));
		result[1][2] = Character.getNumericValue(input.charAt(27));
		result[1][3] = Character.getNumericValue(input.charAt(16));
		result[2][0] = Character.getNumericValue(input.charAt(0));
		result[2][1] = Character.getNumericValue(input.charAt(16));
		result[2][2] = Character.getNumericValue(input.charAt(22));
		result[2][3] = Character.getNumericValue(input.charAt(25));
		result[3][0] = Character.getNumericValue(input.charAt(4));
		result[3][1] = Character.getNumericValue(input.charAt(17));
		result[3][2] = Character.getNumericValue(input.charAt(30));
		result[3][3] = Character.getNumericValue(input.charAt(9));
		result[4][0] = Character.getNumericValue(input.charAt(3));
		result[4][1] = Character.getNumericValue(input.charAt(7));
		result[4][2] = Character.getNumericValue(input.charAt(23));
		result[4][3] = Character.getNumericValue(input.charAt(13));
		result[5][0] = Character.getNumericValue(input.charAt(31));
		result[5][1] = Character.getNumericValue(input.charAt(26));
		result[5][2] = Character.getNumericValue(input.charAt(2));
		result[5][3] = Character.getNumericValue(input.charAt(8));
		result[6][0] = Character.getNumericValue(input.charAt(18));
		result[6][1] = Character.getNumericValue(input.charAt(12));
		result[6][2] = Character.getNumericValue(input.charAt(29));
		result[6][3] = Character.getNumericValue(input.charAt(5));
		result[7][0] = Character.getNumericValue(input.charAt(21)); 
		result[7][1] = Character.getNumericValue(input.charAt(10));
		result[7][2] = Character.getNumericValue(input.charAt(3));  
		result[7][3] = Character.getNumericValue(input.charAt(24));
		return result;
	}
	
	/**
	 * Transforms the matrix into string, row by row
	 * @param input - the given matrix
	 * @return - the matrix as a string, row by row
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

}
