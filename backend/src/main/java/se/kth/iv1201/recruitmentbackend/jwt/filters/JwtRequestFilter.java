package se.kth.iv1201.recruitmentbackend.jwt.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import se.kth.iv1201.recruitmentbackend.jwt.JwtTokenUtil;
import se.kth.iv1201.recruitmentbackend.security.MyUserDetailsService;

/**
 * Gets Executed for every request to the API, checks if request has valid JWT.
 **/
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	private final String AUTH_HEADER = "Authorization";
	private final String BEARER_START = "Bearer ";

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * internal filter to apply to requests.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestHeader = request.getHeader(AUTH_HEADER);
		String username = null;
		String jwtToken = null;
		if (requestHeader != null && requestHeader.startsWith(BEARER_START)) {
			jwtToken = requestHeader.substring(BEARER_START.length());

			username = jwtTokenUtil.getTokenUsername(jwtToken);

		} else {
			logger.warn("Jwt Token does not begin with Bearer String");
		}

		// Once we get the token validate it against the username in token.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
			}

		}
		filterChain.doFilter(request, response);

	}

}
