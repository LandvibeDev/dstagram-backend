package com.landvibe.dstagram.security;

import com.landvibe.dstagram.security.utils.TokenUtils;
import com.landvibe.dstagram.user.UserRepository;
import com.landvibe.dstagram.user.model.DstagramUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private TokenUtils tokenUtils;
    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, TokenUtils tokenUtils, UserRepository userRepository) {
        super(authenticationManager);
        this.tokenUtils = tokenUtils;
        this.userRepository = userRepository;
    }

    /**
     * 1. 요청 해더의 jwt 토큰 값에서 사용자의 email 정보를 추출한다.
     * 2. 추출된 email 정보로 DB에서 유저 정보를 쿼리한다.
     * 3. 유저 정보를 SecurityContextHolder에 저장한다.(인메모리 캐시)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = getUsernamePasswordAuthentication(authHeader);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(String authHeader) {
        String token = authHeader.replace("Bearer", "").trim();
        String email = tokenUtils.getEmailFromToken(token);

        if (email != null) {
            Optional<DstagramUser> user = userRepository.findByEmail(email);
            if (!user.isPresent()) {
                return null;
            }

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.get(), null, Collections.singleton(new SimpleGrantedAuthority(user.get().getRole())));
            return auth;
        }
        return null;
    }
}
