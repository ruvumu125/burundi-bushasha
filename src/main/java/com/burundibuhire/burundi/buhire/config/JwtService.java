package com.burundibuhire.burundi.buhire.config;
import com.burundibuhire.burundi.buhire.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    @Value("${jwt.refresh}")
    private long refreshExpiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            User userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public String generateRefreshToken(
            User userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            User userDetails,
            long expiration
    ) {

//        extraClaims.put("id", userDetails.getId());
//        extraClaims.put("memberIdNumber", userDetails.getMemberIdNumber());
//        extraClaims.put("firstName", userDetails.getFirstName());
//        extraClaims.put("lastName", userDetails.getLastName());
//        extraClaims.put("dateOfBirth", userDetails.getDateOfBirth().toString());
//        extraClaims.put("gender", userDetails.getGender().toString());
//        extraClaims.put("email", userDetails.getEmail());
//        extraClaims.put("phoneNumber", userDetails.getPhoneNumber());
//        extraClaims.put("whatsappNumber", userDetails.getWhatsappNumber());
//        extraClaims.put("username", userDetails.getUsername());
//        extraClaims.put("password", userDetails.getPassword());
        extraClaims.put("role", userDetails.getMemberType().toString());
//        extraClaims.put("memberGrade", userDetails.getMemberGrade().toString());
//        extraClaims.put("countryOfBirth", userDetails.getCountryOfBirth().getCountryName());
//        extraClaims.put("placeOfBirth", userDetails.getPlaceOfBirth());

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
