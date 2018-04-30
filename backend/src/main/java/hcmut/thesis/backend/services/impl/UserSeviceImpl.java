/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services.impl;

/**
 *
 * @author MinBui
 */
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import hcmut.thesis.backend.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserSeviceImpl implements UserService {
    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";

    @Override
    public String createJWT(String UserID)  {
        String JWT = Jwts.builder()
                .setAudience("")
                .setId(UserID)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        
        System.out.println(JWT);
        return JWT;
    }

    @Override
    public Claims parseJWT(String token) {
        if (token != null) {
            // parse the token.
            Claims claims  = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            return claims;
        }
        return null;
    }
}
