package med.voll.api.infra;

import java.util.Base64;

public class JWTInspector {
    public static void inspectToken(String token) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("El token no tiene el formato correcto de JWT.");
        }

        String header = new String(Base64.getUrlDecoder().decode(parts[0]));
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
        String signature = parts[2]; // La firma no necesita decodificarse para inspección básica

        System.out.println("Header: " + header);
        System.out.println("Payload: " + payload);
        System.out.println("Signature: " + signature);

    }
}
