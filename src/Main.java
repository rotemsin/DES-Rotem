import java.math.BigInteger;
import java.util.Arrays;
/**
 * 
 * @author rotemsin
 *
 */
public class Main {

	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {
		
		BigInteger bigInteger = new BigInteger("d8164228f290cbaf", 16);
	    String cypherText = bigInteger.toString(2);
		System.out.println("the cyphertext is " + cypherText);
		
		Input input = new Input("nonsense");
		input.setBitRepresentation();
		System.out.println("The bit representation of 'nonsense' is " + 
							input.getBitRepresentation());
		input.initialPermutation(input.getBitRepresentation());
		String firstKey = "";
		for (int i = 0; i < 64; i++) {
			firstKey += "0";
		}
		String lastKey = "00000000";
		for (int i = 0; i < 56; i++) {
			lastKey += "1";
		}
		KeyGenerator generator = new KeyGenerator(); 
		Key key = new Key(firstKey);
		boolean keyFound = false;
		while (key.getKey(0).equals(lastKey) == false || keyFound) {
			key.produceKeys();
			//round 1
			FFunction f = new FFunction(input.getRightPart(), key.getKey(1));
			int[][] fFunctionResult = f.produceResult();
			int[][] r1 = input.xor(input.getLeftPart(), fFunctionResult);
			int[][] l1 = input.getRightPart();
			//round 2
			f = new FFunction(r1, key.getKey(2));
			fFunctionResult = f.produceResult();
			int[][] r2 = input.xor(l1, fFunctionResult);
			int[][] l2 = r1;
			//round 3
			f = new FFunction(r2, key.getKey(3));
			fFunctionResult = f.produceResult();
			int[][] r3 = input.xor(l2, fFunctionResult);
			int[][] l3 = r2;
			int[][] matrixAfter3Rounds = input.uniteMatrices(l3, r3);
			int[][] matrixAfterInverseIP = input.inverseInitialPermutation(matrixAfter3Rounds);
			String output = input.fromMatrixToString(matrixAfterInverseIP);
			System.out.println("The current encrypted text is " + output);
			if (output.equals(cypherText)) {
				keyFound = true;
				System.out.println("Key found!!!!!!");
			}
			else System.out.println("This is not the key " + key.getKey(0));
			String nextKey = generator.generateNextKey(key.getKey(0));
			key = new Key(nextKey);
		}
	}
	
	/**
	 * A helper function thet prints out any matrix
	 * @param matrix - the given matrix
	 */
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	/**
	 * Transforms the matrix into string, row by row
	 * @param input - the given matrix
	 * @return - the matrix as a string, row by row
	 */
	public static String fromMatrixToString(int[][] input) {
		String result = "";
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				result += input[i][j];
			}
		}
		return result;
	}
}
