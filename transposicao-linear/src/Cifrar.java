import java.util.Scanner;

public class Cifrar {

	public static void main(String[] args) {
		System.out.println("Digite a frase a ser cifrada");

		var scanner = new Scanner(System.in);
		var frase = scanner.nextLine();

		System.out.println("Digite a quantidade de trilhas");
		var colunas = scanner.nextInt();

		var frases = frase.trim().replaceAll("\\s+", "").split("");

		var matriz = new String[colunas][frases.length];

		var coluna = 0;
		var linha = 0;
		var invert = false;
		for (var j = 0; j < frases.length; j++) {
			matriz[coluna][linha] = frases[j];
			linha ++;
			if ((coluna + 1) == colunas && !invert) {
				invert = true;
			}
			if (coluna == 0) {
				invert = false;
			}
			if (invert) {
				coluna --;
			} else {
				coluna ++;
			}
		}

		for (var i = 0; i < colunas; i++) {
			for (var j = 0; j < frases.length; j++) {
				if (matriz[i][j] != null) {
					System.out.print(matriz[i][j]);
				}
			}
		}

		scanner.close();
	}

}
