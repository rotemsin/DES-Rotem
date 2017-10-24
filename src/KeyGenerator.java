/**
 * This class is generates the keys for DES, starting from an all-zeros key
 * @author rotemsin
 *
 */
public class KeyGenerator {
	
	/**
	 * This method generates the next key, which is the incrementation of the binary string by 1
	 * @param previousKey - the previous key as a string
	 * @return - the new current key, which is the incrementation of the previous binary string 
	 * by 1
	 */
	public String generateNextKey(String previousKey) {
		int index = previousKey.lastIndexOf('0');
		String newKey = previousKey.substring(0, index) + '1' + previousKey.substring(index + 1);
		for (int i = index + 1; i < newKey.length(); i++) {
			newKey = newKey.substring(0, i) + '0' + newKey.substring(i + 1);
		}
		return newKey;
	}

}
