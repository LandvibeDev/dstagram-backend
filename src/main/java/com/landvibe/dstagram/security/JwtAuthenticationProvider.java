package com.landvibe.dstagram.security;

import com.landvibe.dstagram.security.model.AuthUser;
import com.landvibe.dstagram.security.model.SecurityUser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserAuthService userAuthService;

    @NonNull
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Authentication 정보와 DB에 저장된 유저의 패스워드를 비교하여 유효성을 확인한다.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        AuthUser authUser = (AuthUser) token.getPrincipal();
        String email = authUser.getEmail();
        String password = (String) token.getCredentials();
        SecurityUser user = (SecurityUser) userAuthService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException(email);
        }

        if (!user.isEnabled()) {
            throw new BadCredentialsException(email);
        }

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
