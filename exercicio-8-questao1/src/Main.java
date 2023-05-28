import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		var scanner = new Scanner(System.in);
		System.out.println("informe o diretório do arquivo");
		var fileName = scanner.nextLine();
		System.out.println("informe o hash SHA256");
		var hash = scanner.nextLine();
		var path = Paths.get(fileName);
		var bytes = Files.readAllBytes(path);
		if (hash.equals(sha256(bytes))) {
			System.out.println("O Arquivo está integro");
		} else {
			System.out.println("O arquivo não está integro");
		}
		scanner.close();
	}

	public static String sha256(final byte[] base) {
		try {
			final MessageDigest digest = MessageDigest.getInstance("SHA-256");
			final byte[] hash = digest.digest(base);
			final StringBuilder hexString = new StringBuilder();
			for (int i = 0; i < hash.length; i++) {
				final String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}
