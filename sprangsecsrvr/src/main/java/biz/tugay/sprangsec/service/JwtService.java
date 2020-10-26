package biz.tugay.sprangsec.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private RSAPublicKey publicKey;

  private RSAPrivateKey privateKey;

  public String generateToken(String username, String role) {
    return JWT
        .create()
        .withClaim("username", username)
        .withClaim("role", role)
        .sign(Algorithm.RSA256(publicKey, privateKey));
  }

  public String validateToken(String token) {
    String payload = JWT
        .require(Algorithm.RSA256(publicKey, privateKey))
        .build()
        .verify(token)
        .getPayload();

    return new String(Base64.getDecoder().decode(payload));
  }

  @PostConstruct
  private void generateKeyPair() throws NoSuchAlgorithmException {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    privateKey = (RSAPrivateKey) keyPair.getPrivate();
    publicKey = (RSAPublicKey) keyPair.getPublic();
  }
}
