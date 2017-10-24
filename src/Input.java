/**
 * This class represents the given input string and the operations implemented on it
 * @author rotemsin
 *
 */
public class Input {

	private String plainText;
	private String bitRepresentation;
	private int[][] matrix;
	
	/**
	 * Input constructor
	 * @param str - the given input
	 */
	public Input(String str) {
		plainText = str;
	}
	
	/**
	 * plainText getter
	 * @return - the plaintext
	 */
	public String getPlainText() {
		return plainText;
	}

	/**
	 * the bit representation of the plaintext getter
	 * @return - the bit representation string
	 */
	public String getBitRepresentation() {
		return bitRepresentation;
	}

	/**
	 * the matriotional representation of the plaintext getter
	 * @return - the matrix representation of the input
	 */
	public int[][] getMatrix() {
		return matrix;
	}
	
	/**
	 * This method creates the bit representation of the plaintext
	 */
	public void setBitRepresentation() {
		byte[] bytes = plainText.getBytes();
		bitRepresentation = "";
		for (int i = 0; i < 8; i++) {
			bitRepresentation += ("0" + Integer.toBinaryString(bytes[i]));
		}
	}
	
	/**
	 * The initial permutation (IP) of the input
	 * @param str - the given input
	 */
	public void initialPermutation(String str) {
		matrix = new int[8][8];
		int index = 57;
		for (int i = 0; i < 8; i++) {
			int lineIndex = index;
			for (int j = 0; j < 8; j++) {
				matrix[i][j] = Character.getNumericValue(str.charAt(lineIndex));
				lineIndex -= 8;
			}
			if (i == 3) index -= 7; else index += 2;
		}
	}
	
	/**
	 * A getter for the left part of the matriotional representation of the input
	 * @return - a matrix which is the left part of the input
	 */
	public int[][] getLeftPart() {
		int[][] result = new int[8][4];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 4; j++) {
				result[i][j] = matrix[i][j];
			}
		}
		return result;
	}

	/**
	 * A getter for the right part of the matriotional representation of the input
	 * @return - a matrix which is the right part of the input
	 */
	public int[][] getRightPart() {
		int[][] result = new int[8][4];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 4; j++) {
				result[i][j] = matrix[i][j + 4];
			}
		}
		return result;
	}

	/**
	 * This helper method converts a matrix to a string, row by row 
	 * @param input - the given input
	 * @return - the converted matrix as a string
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
	 * This function performs the xor operation between 2 matrices
	 * @param str1 - the first matrix
	 * @param str2 - the second matrix
	 * @return - the xor between the 2 matrices, entry by entry
	 */
	public int[][] xor(int[][] matrix1, int[][] matrix2) {
		int[][] result = new int[8][4];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 4; j++) {
				result[i][j] = matrix1[i][j] ^ matrix2[i][j];
			}
		}
		return result;
	}
	
	/**
	 * The eventual operation of the DES, the inverse permutation of the initial permutation
	 * @param matrix - the matrix before the inverse permutation
	 * @return - the matrix after the inverse permutation
	 */
	public int[][] inverseInitialPermutation(int[][] matrix) {
		String input = fromMatrixToString(matrix);
		int[][] result = new int[8][8];
		int[] startValues = {39, 7, 47, 15, 55, 23, 63, 31};
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				result[i][j] = Character.getNumericValue(input.charAt(startValues[j]));
				startValues[j]--;
			}
		}
		return result;
	}
	
	/**
	 * This method creates a matrix which is the attachment of the right part matrix to the 
	 * left part matrix
	 * @param matrix1 - the left matrix
	 * @param matrix2 - the right matrix
	 * @return - the "attached" matrix
	 */
	public int[][] uniteMatrices(int[][] matrix1, int[][] matrix2) {
		int[][] result = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 4; j++) {
				result[i][j] = matrix1[i][j];
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 4; j < 8; j++) {
				result[i][j] = matrix2[i][j - 4];
			}
		}
		return result;
	}

	/**
	 * A helper method to the given matrix, row by row
	 */
	public void printMatrix() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
