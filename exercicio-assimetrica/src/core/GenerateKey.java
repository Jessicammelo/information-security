package core;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class GenerateKey {

	public void Key() throws Exception {
		KeyPair keyPair = buildKeyPair();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

		System.out.println("Modulo");
		System.out.println(privateKey.getModulus());
		System.out.println("");
		System.out.println("Chave privada");
		System.out.println(privateKey.getPrivateExponent());
		System.out.println("");
		System.out.println("Modulo");
		System.out.println(publicKey.getModulus());
		System.out.println("");
		System.out.println("Chave publica");
		System.out.println(publicKey.getPublicExponent());
	}

	public KeyPair buildKeyPair() throws NoSuchAlgorithmException {
		final int keySize = 2048;
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(keySize);
		return keyPairGenerator.genKeyPair();
	}
}
