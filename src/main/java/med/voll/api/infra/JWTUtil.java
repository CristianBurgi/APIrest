package med.voll.api.infra;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JWTUtil {
    @Value("${api.security.secret}")
    private static byte[] apisecret;

    private static final String SECRET = "123456"; // Cambia esto por tu clave secreta
    private static final String ISSUER = "voll med";

    public static String generateToken() {
        Algorithm algorithm = Algorithm.HMAC256("123456");
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject("Cristian.burgi")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1 hora de expiración
                .sign(algorithm);
    }
}