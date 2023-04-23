package core;

import java.util.Scanner;

public class Aplication {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		System.out.println("1 - Gerar chaves");
		System.out.println("2 - Encriptar");
		System.out.println("3 - Decriptar");
		int operation = s.nextInt();
		switch (operation) {
		case 1:
			new GenerateKey().Key();
			break;
		case 2:
			new Encrypt().encryptGenerated();
			break;
		case 3:
		default:
			new Decrypt().decryptGenerated();
			break;
		}
		s.close();
	}
}
