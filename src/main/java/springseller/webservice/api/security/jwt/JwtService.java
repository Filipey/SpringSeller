package springseller.webservice.api.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import springseller.webservice.domain.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.signatureKey}")
    private String subscriptionKey;

    public String generateToken(User user) {
        long expString = Long.valueOf(expiration);

        LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(expString);

        Instant instant = expirationDate.atZone(ZoneId.systemDefault()).toInstant();

        Date date = Date.from(instant);

        return Jwts
                    .builder()
                    .setSubject(user.getLogin())
                    .setExpiration(date)
                    .signWith(SignatureAlgorithm.HS512, subscriptionKey)
                    .compact();

    }
}
