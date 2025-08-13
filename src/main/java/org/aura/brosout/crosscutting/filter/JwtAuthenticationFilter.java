package org.aura.brosout.crosscutting.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aura.brosout.common.responseWrapper.ApiResponse;
import org.aura.brosout.utils.JwtUtilsService;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtilsService jwtUtilsService;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            final String token = authHeader.substring(7).trim();
            try{
            final String userName = jwtUtilsService.extractUsername(token);

            if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
                final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

                if (jwtUtilsService.isTokenValid(token, userDetails.getUsername())){
                     UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                     authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                     SecurityContextHolder.getContext().setAuthentication(authToken);
                     log.debug("Authenticated user: {}", userName);
                } else {
                    writeUnauthorizedResponse(response, "Invalid or Expired JWT token");
                    return;
                }
            }
        }
        catch (ExpiredJwtException ex) {
            log.warn("Expired JWT token: {}", ex.getMessage());
            writeUnauthorizedResponse(response, "Token expired: " + ex.getMessage());
            return;
        } catch (JwtException ex) {
            log.warn("JWT processing failed: {}", ex.getMessage());
            writeUnauthorizedResponse(response, "Invalid JWT token: " + ex.getMessage());
            return;
        } catch (Exception ex) {
            log.error("Unexpected error while processing JWT authentication", ex);
        }

        filterChain.doFilter(request, response);
    }
    private void writeUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        ApiResponse<?> payload = ApiResponse.error(message);
        objectMapper.writeValue(response.getWriter(), payload);
    }
}
