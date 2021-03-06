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

/**
 * Component that helps to handle JSON Web Tokens (JWTs) such as: generating new JWTs,
 * validation, and extraction.
 *
 */
@Component
public class JwtTokenUtil {
	public static final long JWT_EXPIRATION_TIME = 5 * 60 * 60 * 1000;

	@Value("${jwt.secret}")
	private String secret;

	/**
	 * Extracts username from a token.
	 * 
	 * @param token The JWT.
	 * @return the username from the token.
	 */
	public String getTokenUsername(String token) {
		return exctractTokenClaim(token, Claims::getSubject);

	}

	/**
	 * Extracts the role from the token.
	 * 
	 * @param token The JWT.
	 * @return the role in the token.
	 */
	public String getTokenRole(String token) {
		String sub = exctractAllTokenClaims(token).get("roles").toString().substring(6);
		return sub.substring(0, sub.length() - 1);

	}

	/**
	 * Extracts expiration date from token.
	 * 
	 * @param token The JWT.
	 * @return a <code>Date</code> with the expiration date.
	 */
	public Date getTokenExpirationDate(String token) {
		return exctractTokenClaim(token, Claims::getExpiration);
	}

	/**
	 * Extracts specified info from the JWT.
	 * 
	 * @param <T>            Type to be extracted.
	 * @param token          The JWT.
	 * @param claimsResolver The functionality
	 * @return the extracted data.
	 */
	public <T> T exctractTokenClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = exctractAllTokenClaims(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * Creates a JWT.
	 * 
	 * @param userDetails The information to create the JWT from.
	 * @return a generated JWT.
	 */
	public String createToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("roles", userDetails.getAuthorities().toString());
		return generateToken(claims, userDetails.getUsername());
	}

	/**
	 * Validates a JWT, if it's equal to UserDetails and if it's not expired.
	 * 
	 * @param token       the JWT
	 * @param userDetails The UserDetails to validate token to.
	 * @return <code>true</code> if valid; <code>false</code> otherwise.
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getTokenUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private String generateToken(Map<String, Object> claims, String username) {
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	private boolean isTokenExpired(String token) {
		return getTokenExpirationDate(token).before(new Date());
	}

	private Claims exctractAllTokenClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
}
