package aes.core;

public class ContextoDeEntrada {

	// Chave digitada em texto simples
	private String chaveEmTextSimples;
	// Chave ja convertida para um array bidirecional em hexadecimal
	private String[][] chaveEmHexadecimal;
	private byte[][] chaveEmByte;

	public void criarChaveEmHexadecimalDaMatrizDeEstados() {
		// Converte os caracteres de string para array
		var chaveEmArrayDeBytes = chaveEmTextSimples.trim().split(",");
		byte[] bytes = new byte[chaveEmArrayDeBytes.length];
		chaveEmHexadecimal = new String[4][4];
		chaveEmByte = new byte[4][4];
		int indexHex = 0;
		// percorre a lista de dados de entrada
		for (var i = 0; i < chaveEmArrayDeBytes.length; i++) {
			// transforma o inteiro para byte
			bytes[i] = Integer.valueOf((chaveEmArrayDeBytes[i])).byteValue();
			// preenche o byte na matriz de estado
			chaveEmHexadecimal[i % 4][indexHex] = String.format("%02X ", bytes[i]);
			chaveEmByte[i % 4][indexHex] = bytes[i];
			if (i % 4 == 3) {
				indexHex++;
			}
		}
	}

	public String getChaveEmTextSimples() {
		return chaveEmTextSimples;
	}

	public void setChaveEmTextSimples(String chaveEmTextSimples) {
		this.chaveEmTextSimples = chaveEmTextSimples;
	}

	public String[][] getChaveEmHexadecimal() {
		return chaveEmHexadecimal;
	}

	public void setChaveEmHexadecimal(String[][] chaveEmHexadecimal) {
		this.chaveEmHexadecimal = chaveEmHexadecimal;
	}

	public byte[][] getChaveEmByte() {
		return chaveEmByte;
	}

	public void setChaveEmByte(byte[][] chaveEmByte) {
		this.chaveEmByte = chaveEmByte;
	}
}
