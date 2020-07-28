package com.landvibe.dstagram.security;

import com.landvibe.dstagram.security.model.SecurityUser;
import com.landvibe.dstagram.security.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 패스워드 값이 일치하면 jwt 토큰을 생성하여 응답 헤더에 넣어준다.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String token = tokenUtils.generateJwtToken(securityUser.getAuthUser());
        response.addHeader("Authorization", "Bearer " + token);
    }
}
