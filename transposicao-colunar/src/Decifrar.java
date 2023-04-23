import java.util.Scanner;

public class Decifrar {

	public static void main(String[] args) {
		System.out.println("Digite a frase a ser decifrada");

		var scanner = new Scanner(System.in);
		var frase = scanner.nextLine();

		System.out.println("Digite as colunas da matriz");
		var colunas = scanner.nextInt();

		var frases = frase.trim().replaceAll("\\s+", "").split("");

		var matriz = new String[(frases.length / colunas) + 1][colunas];

		var fraseIterador = 0;
		for (var i = 0; i < ((frases.length / colunas) + 1); i++) {
			for (var j = 0; j < colunas; j++) {
				if (frases.length <= fraseIterador) {
					matriz[i][j] = "X";
				} else {
					matriz[i][j] = frases[fraseIterador];
					fraseIterador++;
				}
			}
		}

		for (var i = 0; i < ((frases.length / colunas) + 1); i++) {
			for (var j = 0; j < colunas; j++) {
				System.out.print(matriz[i][j]);
			}
		}

		scanner.close();
	}

}
