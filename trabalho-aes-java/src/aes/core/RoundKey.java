package aes.core;

import aes.tabelas.SBox;

public class RoundKey {

	private byte[][] chaveEmByte;
	private byte[][] roundKey;
	private byte[] roundKeyCopy;
	private byte[] roundConstant = new byte[10];
	private int roundKeyOld = 0;
	private int roundKeyCurrent = 3;
	private int roundKeyNext = 4;

	public void set(byte[][] chaveEmByte) {
		this.chaveEmByte = chaveEmByte;
		gerarRoundConstant();
	}

	public void gerarRoundKeys() {
		roundKey = new byte[4][44];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				roundKey[i][j] = chaveEmByte[i][j];
			}
		}
	}

	public void copiarRoundKeyAnterior() {
		roundKeyCopy = new byte[4];
		roundKeyCopy[0] = roundKey[0][roundKeyCurrent];
		roundKeyCopy[1] = roundKey[1][roundKeyCurrent];
		roundKeyCopy[2] = roundKey[2][roundKeyCurrent];
		roundKeyCopy[3] = roundKey[3][roundKeyCurrent];
	}

	public void RotWord() {
		var roundKeyCopyRotWord = new byte[4];
		for (var i = 0; i < 4; i++) {
			if (i < 3) {
				roundKeyCopyRotWord[i] = roundKeyCopy[i + 1];
			} else {
				roundKeyCopyRotWord[i] = roundKeyCopy[0];
			}
		}
		roundKeyCopy = roundKeyCopyRotWord;
	}

	public void substituirByteSBoxSubWord() {
		for (var i = 0; i < 4; i++) {
			roundKeyCopy[i] = new SBox().obterByteSbox(roundKeyCopy[i]);
		}
	}

	public void aplicarXorPrimeiraPalavra(int index) {
		byte roundConstantByte = roundConstant[index];
		int xor = roundKeyCopy[0] ^ roundConstantByte;
		roundKeyCopy[0] = (byte) (0xff & xor);
	}

	public void gerarPalavra() {
		for (int i = 0; i < 4; i++) {
			var xor = roundKey[i][roundKeyOld] ^ roundKeyCopy[i];
			roundKey[i][roundKeyNext] = (byte) (0xff & xor);
		}
		roundKeyOld++;
		roundKeyCurrent++;
		roundKeyNext++;
	}

	public byte[][] addRoundKey(byte[][] mixColumns, int index) {
		var roundKey = getRoundKey(index);
		byte[][] addROundKeyXor = new byte[4][4];
		for (var i = 0; i < 4; i++) {
			for (var j = 0; j < 4; j++) {
				var xor = roundKey[i][j] ^ mixColumns[i][j];
				addROundKeyXor[i][j] = (byte) (0xff & xor);
			}
		}
		return addROundKeyXor;
	}

	private byte[][] getRoundKey(int index) {
		byte[][] roundKeyIndex = new byte[4][4];
		for (var i = 0; i < 4; i++) {
			for (var j = index; j < (4 + index); j++) {
				roundKeyIndex[i][j - index] = roundKey[i][j];
			}
		}
		return roundKeyIndex;
	}

	private void gerarRoundConstant() {
		roundConstant[0] = Integer.valueOf("01", 16).byteValue();
		roundConstant[1] = Integer.valueOf("02", 16).byteValue();
		roundConstant[2] = Integer.valueOf("04", 16).byteValue();
		roundConstant[3] = Integer.valueOf("08", 16).byteValue();
		roundConstant[4] = Integer.valueOf("10", 16).byteValue();
		roundConstant[5] = Integer.valueOf("20", 16).byteValue();
		roundConstant[6] = Integer.valueOf("40", 16).byteValue();
		roundConstant[7] = Integer.valueOf("80", 16).byteValue();
		roundConstant[8] = Integer.valueOf("1B", 16).byteValue();
		roundConstant[9] = Integer.valueOf("36", 16).byteValue();
	}

	public byte[][] getRoundKey() {
		return roundKey;
	}

	public void setRoundKey(byte[][] roundKey) {
		this.roundKey = roundKey;
	}

}
