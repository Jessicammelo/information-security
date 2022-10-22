package aes.core;

public class XorTextoSimplesRoundKey0 {

	public byte[][] xorTextoSimples(String texto, byte[][] roundKey) {
		var roundKey0 = getRoundKey0(roundKey);
		var chaveEmArrayDeBytes = texto.toCharArray();
		byte[] bytes = new byte[chaveEmArrayDeBytes.length];
		int indexHex = 0;
		byte[][] chaveEmByte = new byte[4][4];
		for (var i = 0; i < chaveEmArrayDeBytes.length; i++) {
			bytes[i] = Integer.valueOf((chaveEmArrayDeBytes[i])).byteValue();
			chaveEmByte[i % 4][indexHex] = bytes[i];
			if (i % 4 == 3) {
				indexHex++;
			}
		}
		byte[][] xors = new byte[4][4];
		for (var i = 0; i < 4; i++) {
			for (var j = 0; j < 4; j++) {
				var xor = chaveEmByte[i][j] ^ roundKey0[i][j];
				xors[i][j] = (byte) (0xff & xor);
			}
		}
		return xors;
	}

	private byte[][] getRoundKey0(byte[][] roundKey) {
		byte[][] roundKey0 = new byte[4][4];
		for (var i = 0; i < 4; i++) {
			for (var j = 0; j < 4; j++) {
				roundKey0[i][j] = roundKey[i][j];
			}
		}
		return roundKey0;
	}

}
