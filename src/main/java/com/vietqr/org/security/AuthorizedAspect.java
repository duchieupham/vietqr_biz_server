package com.vietqr.org.security;

import com.vietqr.org.service.MerchantStaffService;
import com.vietqr.org.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.nio.file.AccessDeniedException;

@Component
@Aspect
public class AuthorizedAspect {
    private final MerchantStaffService merchantStaffService;

    public AuthorizedAspect(MerchantStaffService merchantStaffService) {
        this.merchantStaffService = merchantStaffService;
    }

    /*
     * Type
     * 0: tid
     * 1: mid
     * */
    @Around("@annotation(com.vietqr.org.security.Authorized) && args(id, type)")
    public Object Authorized(ProceedingJoinPoint joinPoint, String id, int type) throws Throwable {
        String HEADER = "Authorization";
        String PREFIX = "Bearer ";

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Authorized authorized = method.getAnnotation(Authorized.class);
        String permissionId = authorized.value();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader(HEADER);

        if (token != null && token.startsWith(PREFIX)) {
            token = token.substring(7);
        }

        Claims claims = JwtUtil.parse(token);
        String userId = claims.get("userId", String.class);

        // Check permission
        if (merchantStaffService.isAuthorized(userId, permissionId, id, type)) {
            return joinPoint.proceed();
        } else {
            throw new AccessDeniedException("User with id: " + userId + " does not have the necessary permissions.");
        }
    }
}
