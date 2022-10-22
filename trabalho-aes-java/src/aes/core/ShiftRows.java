package aes.core;

public class ShiftRows {
	
	public byte[][] rotacionar(byte[][] subBytes) {
		var shiftRows = new byte[4][4];
		shiftRows[0][0] = subBytes[0][0];
		shiftRows[0][1] = subBytes[0][1];
		shiftRows[0][2] = subBytes[0][2];
		shiftRows[0][3] = subBytes[0][3];

		shiftRows[1][3] = subBytes[1][0];
		shiftRows[1][0] = subBytes[1][1];
		shiftRows[1][1] = subBytes[1][2];
		shiftRows[1][2] = subBytes[1][3];
		
		shiftRows[2][2] = subBytes[2][0];
		shiftRows[2][3] = subBytes[2][1];
		shiftRows[2][0] = subBytes[2][2];
		shiftRows[2][1] = subBytes[2][3];
		
		shiftRows[3][1] = subBytes[3][0];
		shiftRows[3][2] = subBytes[3][1];
		shiftRows[3][3] = subBytes[3][2];
		shiftRows[3][0] = subBytes[3][3];
		
		return shiftRows;
	}

}
