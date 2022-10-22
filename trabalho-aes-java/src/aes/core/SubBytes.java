package aes.core;

import aes.tabelas.SBox;

public class SubBytes {

	public byte[][] aplicarSubBytes(byte[][] xorTextoSimplesRoundKey0) {
		byte[][] matrizResultante = new byte[4][4];
		for (var i = 0; i < 4; i++) {
			for (var j = 0; j < 4; j++) {
				matrizResultante[i][j] = new SBox().obterByteSbox(xorTextoSimplesRoundKey0[i][j]);
			}
		}
		return matrizResultante;
	}
	
}
