package testedev.service.com.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.startup.UserDatabase;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import testedev.service.com.repository.TokenRepository;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter  {

	private JwtService jwtService;
	
	private UserDetailsService userDatailsService;
	
	private TokenRepository tokenRepository;

	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		
		if (authHeader ==null || authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return ;
		}
		jwt = authHeader.substring(7);
		userEmail = jwtService.extractUsername(jwt);
		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userdatails = this.userDatailsService.loadUserByUsername(userEmail);
		     /* var isTokenValid = tokenRepository.findByToken(jwt)
		    	.map(t -> !t.expired() && !t.isRevoked())
		        .orElse(false);  */
		      
			if (jwtService.isTokenValid(jwt, userdatails)) {
				UsernamePasswordAuthenticationToken authToken =new UsernamePasswordAuthenticationToken(userdatails, null, userdatails.getAuthorities());
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authToken);
					
			}
			
		}
		filterChain.doFilter(request, response);
	
	}

}
