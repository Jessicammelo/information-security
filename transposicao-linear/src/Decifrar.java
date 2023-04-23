import java.util.Scanner;

public class Decifrar {

	public static void main(String[] args) {
		System.out.println("Digite a frase a ser decifrada");

		var scanner = new Scanner(System.in);
		var frase = scanner.nextLine();

		System.out.println("Digite a quantidade de trilhas");
		var colunas = scanner.nextInt();

		var frases = frase.trim().replaceAll("\\s+", "").split("");

		var matriz = new String[colunas][frases.length];

		var iterador = 0;
		var coluna = 0;
		var linha = 0;
		var invert = false;
		for (var c = 0; c < frases.length; c++) {
			if (iterador == coluna) {
				matriz[coluna][linha] = frases[c];
			}
			linha++;
			if ((coluna + 1) == colunas && !invert) {
				invert = true;
			}
			if (coluna == 0) {
				invert = false;
			}
			if (invert) {
				coluna--;
			} else {
				coluna++;
			}
			while (iterador != coluna) {
				linha++;
				if ((coluna + 1) == colunas && !invert) {
					invert = true;
				}
				if (coluna == 0) {
					invert = false;
				}
				if (invert) {
					coluna--;
				} else {
					coluna++;
				}
			}
			if (linha >= frases.length) {
				iterador ++;
				coluna = iterador;
				linha = iterador;
			}
		}

		coluna = 0;
		linha = 0;
		invert = false;
		for (var j = 0; j < frases.length; j++) {
			System.out.print(matriz[coluna][linha]);
			linha++;
			if ((coluna + 1) == colunas && !invert) {
				invert = true;
			}
			if (coluna == 0) {
				invert = false;
			}
			if (invert) {
				coluna--;
			} else {
				coluna++;
			}
		}

		scanner.close();
	}

}
