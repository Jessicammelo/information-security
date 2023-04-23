package aes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import aes.core.ContextoDeEntrada;
import aes.core.MixColumns;
import aes.core.RoundKey;
import aes.core.ShiftRows;
import aes.core.SubBytes;
import aes.core.XorTextoSimplesRoundKey0;
//20,1,94,33,199,0,48,9,31,94,112,40,59,30,100,248
public class Application {

	public static void main(String args[]) throws IOException {
		System.out.println("Digite a chave");
		// Le a chave do console
		Scanner scanner = new Scanner(System.in);
		//var chave = scanner.next();
		var chave = scanner.next();
		System.out.println("Digite o path do arquivo");
		var pathStr = scanner.next();
		Path path = Paths.get(pathStr);
		List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
		var textoSimples = allLines.get(0);
		System.out.println("Digite o path do arquivo a ser salvo");
		var pathSaveStr = scanner.next();
		// Cria a instancia da classe de contexto
		var contexto = new ContextoDeEntrada();
		// Seta a chave em texto simples
		contexto.setChaveEmTextSimples(chave);
		// Cria a chave em hexadecimal
		contexto.criarChaveEmHexadecimalDaMatrizDeEstados();

		// Executa o round key
		var roundKey = new RoundKey();
		roundKey.set(contexto.getChaveEmByte());
		roundKey.gerarRoundKeys();
		for (var i = 0; i < 10; i++) {
			for (int j = 0; j < 4; j++) {
				roundKey.copiarRoundKeyAnterior();
				roundKey.RotWord();
				roundKey.substituirByteSBoxSubWord();
				roundKey.aplicarXorPrimeiraPalavra(i);
				roundKey.gerarPalavra();
			}
		}
		var roundKeys = roundKey.getRoundKey();
		var textSimplesXor = new XorTextoSimplesRoundKey0().xorTextoSimples(textoSimples, roundKeys);
		for (var i = 0; i < 10; i++) {
			var subBytes = new SubBytes().aplicarSubBytes(textSimplesXor);
			var shiftRows = new ShiftRows().rotacionar(subBytes);
			var mixedColumns = new MixColumns().mixColumn(shiftRows);
			textSimplesXor = roundKey.addRoundKey(mixedColumns, (i * 4) + 4);
		}
		try (FileOutputStream stream = new FileOutputStream(pathSaveStr)) {
			for (var b : textSimplesXor) {
				stream.write(b);
			}
		}
		scanner.close();
	}

}
