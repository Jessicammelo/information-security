import java.util.Scanner;

public class Cifrar {

	public static void main(String[] args) {
		System.out.println("Digite a frase a ser cifrada");

		var scanner = new Scanner(System.in);
		var frase = scanner.nextLine();

		System.out.println("Digite as colunas da matriz");
		var colunas = scanner.nextInt();

		var frases = frase.trim().replaceAll("\\s+", "").split("");

		var matriz = new String[(frases.length / colunas)][colunas];

		var fraseIterador = 0;
		for (var j = 0; j < colunas; j++) {
			for (var i = 0; i < (frases.length / colunas); i++) {
				if (frases.length <= fraseIterador) {
					matriz[i][j] = "X";
				} else {
					matriz[i][j] = frases[fraseIterador];
					fraseIterador++;
				}
			}
		}

		for (var i = 0; i < ((frases.length / colunas)); i++) {
			for (var j = 0; j < colunas; j++) {
				System.out.print(matriz[i][j]);
			}
		}

		scanner.close();
	}

}
