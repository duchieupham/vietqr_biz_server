package com.vietqr.org.security;

import com.vietqr.org.utils.JwtUtil;
import io.jsonwebtoken.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthAspect {
    @Around("@annotation(com.vietqr.org.security.Auth) && args(authDTO)")
    public Object Authorized(ProceedingJoinPoint joinPoint, AuthDTO authDTO) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        System.out.println("token hg: " + token);

        if (token != null && token.startsWith("Bearer ")) {
            // Remove "Bearer " prefix
            token = token.substring(7);
            try {
                Claims claims = JwtUtil.parse(token);
                String userId = claims.get("userId", String.class);
                System.out.println("userId: " + userId);

// Xet permission
                return joinPoint.proceed();
                // Xử lý claims
            } catch (ExpiredJwtException ex) {
                // Token đã hết hạn
                System.out.println("Token has expired");
            } catch (SignatureException ex) {
                // Signature không hợp lệ
                System.out.println("Invalid signature");
            } catch (MalformedJwtException ex) {
                // Token có định dạng không hợp lệ
                System.out.println("Invalid JWT token");
            } catch (UnsupportedJwtException ex) {
                // JWT không được hỗ trợ
                System.out.println("Unsupported JWT");
            } catch (IllegalArgumentException ex) {
                // Token rỗng hoặc null
                System.out.println("Token is null or empty");
            }
        }
        return "Missing authorization token";
    }
}
