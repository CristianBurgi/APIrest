package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.voll.api.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            var tok = JWT.create()
                    .withIssuer("voll med")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
            System.out.println(tok);
            return tok;

        } catch (JWTCreationException exception) {
            throw new RuntimeException("NO SE GENERO UN TOKEN");
            // Invalid Signing configuration / Couldn't convert Claims.
        }

    }


    public String getSubject(String token) {

        if (token == null){
            throw new RuntimeException();
        }
        DecodedJWT decodedJWT = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            // Crea el verificador de JWT
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("voll med")
                    .build();

            // Verifica el token
            decodedJWT = verifier.verify(token);

        } catch (JWTVerificationException exception) {
            // Imprime el mensaje de error para depuración
            System.err.println("Error en la verificación del JWT: " + exception.getMessage());
            throw new RuntimeException("Invalid signature/claims 11", exception);
        }

        if (decodedJWT.getSubject() == "" || decodedJWT.getSubject() == null) {
            throw new RuntimeException("Verifier Invalido");
        }
        System.out.println(decodedJWT.getSubject());

        return decodedJWT.getSubject();
    }


    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }


}
