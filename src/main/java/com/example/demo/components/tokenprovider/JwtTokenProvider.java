package com.example.demo.components.tokenprovider;

import com.example.demo.modules.authen.model.AuthenEntity;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.example.demo.common.Constants.JWT_LIFE_TIME;
import static com.example.demo.common.Constants.JWT_SECRET;

@Component
@Slf4j
public class JwtTokenProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    public String generateToken(AuthenEntity auth) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat ldf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        Date now = null;
        Date expiryDate = null;
        try {
            now = ldf.parse(sdf.format(new Date()));
            expiryDate = new Date(now.getTime() + JWT_LIFE_TIME);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Jwts.builder()
                .setSubject(auth.getUid() + ","  + auth.getEmail() + "," + auth.getStatus() + "," + auth.getCreatedAt())
                .setIssuer("ducnd58233")
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            LOGGER.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            LOGGER.error("JWT claims string is empty.");
        }
        return false;
    }
}
