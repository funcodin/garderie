package com.garderie.service.impl.jwt;

import com.garderie.service.exception.model.UserNotFoundException;
import com.garderie.service.impl.auth.UserAuthenticationServiceImpl;
import com.garderie.service.interfaces.TokenService;
import com.garderie.types.security.auth.Authority;
import com.garderie.types.security.auth.UserAuthentication;
import com.garderie.types.security.auth.permissions.ActionPermissions;
import com.garderie.types.security.auth.token.JwtTokenData;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class JsonWebTokenService implements TokenService {

    @Value("security.token.secret.key")
    private String tokenKey;

    @Autowired
    private UserAuthenticationServiceImpl userAuthenticationService;

    @Override
    public String generateJwtToken(final UserAuthentication userAuthentication) {
        Map<String, Object> jwtToken = new HashMap<>();
        //jwtToken.put("clientType", "user");
        jwtToken.put("user_id", userAuthentication.getUserAccountDetails().getId());
        jwtToken.put("username", userAuthentication.getUserAccountDetails().getUsername());
        //tokenData.put("token_create_date", LocalDateTime.now());
        jwtToken.put("user_role", userAuthentication.getUserAccountDetails().getAuthorities());
        jwtToken.put("user_permissions", userAuthentication.getUserPermissions().getActionPermissions());
        jwtToken.put("org_id", userAuthentication.getUserPermissions().getOrganisationId());
        JwtBuilder jwtBuilder = Jwts.builder();
        Instant instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        jwtBuilder.setExpiration(Date.from(instant));
        jwtBuilder.setClaims(jwtToken);
        return jwtBuilder.signWith(SignatureAlgorithm.HS512, tokenKey).compact();

    }

    @Override
    public String generateJwtTokenByEmailId(final String emailId) {
        final UserAuthentication userAuthentication = this.userAuthenticationService.getUserAuthenticationByEmailId(emailId);
        return this.generateJwtToken(userAuthentication);
    }

    @Override
    public List<ActionPermissions> getActionPermissionFromJwtToken(final String jwtToken) {
        final Jws<Claims> jwsClaims = this.parseToken(jwtToken);
        JwtTokenData jwtTokenData = this.getUserRequestAuthenticationFromToken(jwsClaims);
        return jwtTokenData.getActionPermissions();
    }

    @Override
    public JwtTokenData getJwtTokenDataFromJwtToken(final String jwtToken) {
        final Jws<Claims> jwsClaims = this.parseToken(jwtToken);
        final JwtTokenData jwtTokenData = this.getUserRequestAuthenticationFromToken(jwsClaims);
        return jwtTokenData;
    }

    public Jws<Claims> parseToken(final String token) {
        if (token != null) {
            try {
                return Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(token);
            } catch (UnsupportedJwtException | MalformedJwtException
                    | SignatureException | IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }

    public JwtTokenData getUserRequestAuthenticationFromToken(final Jws<Claims> tokenData) {
        try {
            JwtTokenData jwtTokenData = new JwtTokenData();
            Claims claims = tokenData.getBody();
            jwtTokenData.setUserName(this.getTokenValueByKey(claims, "username").toString());
            jwtTokenData.setAuthorities((List<Authority>) this.getTokenValueByKey(claims, "user_role"));
            jwtTokenData.setActionPermissions((List<ActionPermissions>) this.getTokenValueByKey(claims, "user_permissions"));
            jwtTokenData.setOrgId((String) this.getTokenValueByKey(claims, "org_id"));
            jwtTokenData.setUserId((String) this.getTokenValueByKey(claims, "user_id"));
            return jwtTokenData;
        } catch (UsernameNotFoundException usernamenotfound) {
            throw new UserNotFoundException("username not found ");
        }
    }

    private Object getTokenValueByKey(Claims claims, String key) {
        return claims.get(key);
    }
}
