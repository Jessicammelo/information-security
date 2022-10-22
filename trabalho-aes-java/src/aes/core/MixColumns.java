package aes.core;

import aes.tabelas.MatrizMultiplicacao;
import aes.tabelas.TabelaE;
import aes.tabelas.TabelaL;

public class MixColumns {

	public byte[][] mixColumn(byte[][] shiftRows) {
		byte[][] mixColumn = new byte[4][4];
		for (var i = 0; i < 4; i++) {
			for (var j = 0; j < 4; j++) {
				var r1 = new TabelaL().obterTabelaL(shiftRows[0][i]);
				var m1 = new TabelaL().obterTabelaL(MatrizMultiplicacao.matriz[i][0].byteValue());
				var soma1 = r1 + m1;
				if (soma1 > 255) {
					soma1 = 255;
				}
				if (r1 == 0 || m1 == 0) {
					soma1 = 0;
				}
				if (r1 == 1) {
					soma1 = m1;
				}
				if (m1 == 1) {
					soma1 = r1;
				}
				var somae1 = new TabelaE().obterTabelaE(Integer.valueOf(String.valueOf(soma1)).byteValue());

				var r2 = new TabelaL().obterTabelaL(shiftRows[1][i]);
				var m2 = new TabelaL().obterTabelaL(MatrizMultiplicacao.matriz[i][1].byteValue());
				var soma2 = r2 + m2;
				if (soma2 > 255) {
					soma2 = 255;
				}
				if (r2 == 0 || m2 == 0) {
					soma2 = 0;
				}
				if (r2 == 1) {
					soma2 = m1;
				}
				if (m2 == 1) {
					soma2 = r2;
				}
				
				var somae2 = new TabelaE().obterTabelaE(Integer.valueOf(String.valueOf(soma2)).byteValue());

				var r3 = new TabelaL().obterTabelaL(shiftRows[2][i]);
				var m3 = new TabelaL().obterTabelaL(MatrizMultiplicacao.matriz[i][2].byteValue());
				var soma3 = r3 + m3;
				if (soma3 > 255) {
					soma3 = 255;
				}
				
				if (r3 == 0 || m3 == 0) {
					soma3 = 0;
				}
				if (r3 == 1) {
					soma3 = m3;
				}
				if (m3 == 1) {
					soma3 = r3;
				}
				
				var somae3 = new TabelaE().obterTabelaE(Integer.valueOf(String.valueOf(soma3)).byteValue());

				var r4 = new TabelaL().obterTabelaL(shiftRows[3][i]);
				var m4 = new TabelaL().obterTabelaL(MatrizMultiplicacao.matriz[i][3].byteValue());
				var soma4 = r4 + m4;
				if (soma4 > 255) {
					soma4 = 255;
				}
				if (r4 == 0 || m4 == 0) {
					soma4 = 0;
				}
				if (r4 == 1) {
					soma4 = m4;
				}
				if (m1 == 1) {
					soma4 = r4;
				}
				
				var somae4 = new TabelaE().obterTabelaE(Integer.valueOf(String.valueOf(soma4)).byteValue());
				
				var xor = somae1 ^ somae2 ^ somae3 ^ somae4;
				mixColumn[i][j] = (byte) (0xff & xor);
			}
		}
		return mixColumn;
	}

}
