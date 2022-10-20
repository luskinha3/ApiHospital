package br.com.five.spring.consultorio.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class CsrfLoggerFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain filterChain) throws ServletException, IOException {
		
		CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		response.setHeader("CSRF-TOKEN-VALUE", csrfToken.getToken());
		response.setHeader("Set-Cookie", sessionId);
		
		filterChain.doFilter(request, response);
		
	}

}
