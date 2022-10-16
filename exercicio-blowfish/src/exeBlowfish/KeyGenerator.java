package exeBlowfish;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class KeyGenerator {

	private final static String KEY = "FURB";
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static void main(String[] args) throws Exception {
		System.out.println("Questão 1");
		cripto("SABONETESABONETE", "ECB");
		System.out.println();
		System.out.println("Questão 2");
		cripto("COMPUTADOR", "ECB");
		System.out.println();
		System.out.println("Questão 3");
		cripto("SABONETE", "ECB");
		System.out.println();
		System.out.println("Questão 4");
		cripto("SABONETESABONETESABONETE", "ECB");
		System.out.println();
		System.out.println("Questão 5");
		cripto("FURB", "ECB");
		try {
			unCripto(cripto("FURB", "ECB"), "ECB"); // tente decifrar o texto cifrado
			System.out.println();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println();
		System.out.println("Questão 6");
		byte[] bites = {1, 1, 2, 2, 3, 3, 4, 4};
		cripto("FURB", "CBC", bites);
		System.out.println();
		System.out.println("Questão 7");
		byte[] bites7 = {1, 1, 2, 2, 3, 3, 4, 4};
		cripto("SABONETESABONETESABONETE", "CBC", bites7);
		System.out.println();
		System.out.println("Questão 8");
		byte[] bites8 = {10, 20, 30, 40, 50, 60, 70, 80};
		cripto("SABONETESABONETESABONETE", "CBC", bites8);
		try {
			unCripto(cripto("SABONETESABONETESABONETE", "CBC", bites8), "CBC"); // tente decifrar o texto cifrado
			System.out.println();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println();
		System.out.println("Questão 9");
		cripto("FURB", "ECB");
		byte[] bites9 = {1, 1, 1, 1, 1};
		try {
			unCripto(cripto("FURB", "ECB", bites9), "ECB"); // tente decifrar o texto cifrado
			System.out.println();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println();
	}

	public static String cripto(String value, String mode) throws Exception {
		return cripto(value, mode, null);
	}

	public static String cripto(String value, String mode, byte[] bites) throws Exception {
		Cipher cipher = getCipher(mode);
		SecretKeySpec key = getKey();
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] bytes = cipher.doFinal(value.getBytes("UTF-8"));
		if (bites != null) {
			byte[] newBytes = ByteBuffer.allocate(bytes.length + bites.length).put(bites).put(bytes).array();
			bytes = newBytes;
		}
		String crito = bytesToHex(bytes);
		System.out.print(crito);
		System.out.print(" | ");
		System.out.println(crito.length());
		return crito;
	}

	public static void unCripto(String cifred, String mode) throws Exception {
		SecretKeySpec key = new SecretKeySpec(getKey().getEncoded(), "Blowfish");
		Cipher cipher = getCipher(mode);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decrypted = cipher.doFinal(hexToBytes(cifred));
		System.out.println(new String(decrypted));
	}

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	public static byte[] hexToBytes(String str) {
		if (str == null) {
			return null;
		} else if (str.length() < 2) {
			return null;
		} else {
			int len = str.length() / 2;
			byte[] buffer = new byte[len];
			for (int i = 0; i < len; i++) {
				buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
			}
			return buffer;
		}

	}

	private static SecretKeySpec getKey() throws UnsupportedEncodingException {
		return new SecretKeySpec(KEY.getBytes("UTF-8"), "Blowfish");
	}

	private static Cipher getCipher(String mode) throws NoSuchAlgorithmException, NoSuchPaddingException {
		return Cipher.getInstance("Blowfish/" + mode + "/PKCS5Padding");
	}
}
