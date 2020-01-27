package se.kth.iv1201.recruitmentbackend.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil  {

	public static final long JWT_EXPIRATION_TIME = 5 * 60 * 60 * 1000;
	@Value("${jwt.secret}")
	private String secret;

	public String getTokenUsername(String token) {
		return exctractTokenClaim(token, Claims::getSubject);
		
	}
	public Date getTokenExpirationDate(String token) {
		return exctractTokenClaim(token, Claims::getExpiration);
		}
	
	public <T> T exctractTokenClaim(String token, Function<Claims, T> claimsResolver) {
			final Claims claims = exctractAllTokenClaims(token);
			return claimsResolver.apply(claims);
	}
	
	public String createToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return generateToken(claims, userDetails.getUsername());
	}
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getTokenUsername(token);
		return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return getTokenExpirationDate(token).before(new Date());
	}
	private String generateToken(Map<String, Object> claims, String username) {
		
		return Jwts.builder().setClaims(claims).setSubject(username)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis()+JWT_EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	private Claims exctractAllTokenClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
}
