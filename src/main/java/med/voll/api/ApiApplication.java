package med.voll.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static med.voll.api.infra.JWTInspector.inspectToken;
import static med.voll.api.infra.JWTUtil.generateToken;

@SpringBootApplication
public class ApiApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
        //String token = generateToken();
        //System.out.println("Generated Token: " + token);
        //inspectToken(token);

    }

}
