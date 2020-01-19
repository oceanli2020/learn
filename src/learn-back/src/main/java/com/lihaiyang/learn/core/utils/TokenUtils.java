package com.lihaiyang.learn.core.utils;


import com.lihaiyang.learn.core.config.MobileProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;
import org.apache.commons.lang3.StringUtils;


public class TokenUtils {

    private static MobileProperties mobileProperties = (MobileProperties)SpringContextHolder.getBean(MobileProperties.class);

    public static String createToken(String userId, String deviceType) {
        return createJwt(mobileProperties.getJwtSecretKey(), userId, deviceType, mobileProperties.getTokenTimeout(), (Map)null, SignatureAlgorithm.HS256);
    }

    public static String createRefreshToken(String userId) {
        String userAgent = HttpUtils.getUserUAInfo(userId);
        long nowTime = System.currentTimeMillis();
        long exp = nowTime + mobileProperties.getRefreshTokenTimeout();
        StringBuilder sb = (new StringBuilder(userAgent)).append("|").append(exp).append("|").append(userId);

        try {
            return EncryptUtils.encryptHexString(sb.toString());
        } catch (Exception var8) {
            var8.printStackTrace();
            return null;
        }
    }


    public static String createJwt(String jwtSecretKey, String subject, String issuer, Long period, Map<String, Object> extra, SignatureAlgorithm algorithm) {
        long currentTimeMillis = System.currentTimeMillis();
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
        JwtBuilder jwt = Jwts.builder();
        jwt.setId(UUID.randomUUID().toString());
        jwt.setSubject(subject);
        if (null != issuer && !"".equals(issuer)) {
            jwt.setIssuer(issuer);
        }

        jwt.setIssuedAt(new Date(currentTimeMillis));
        if (null != period) {
            Date expiration = new Date(currentTimeMillis + period);
            jwt.setExpiration(expiration);
        }

        jwt.signWith(algorithm, secretKeyBytes);
        if (Objects.nonNull(extra) && !extra.isEmpty()) {
            jwt.addClaims(extra);
        }

        return jwt.compact();
    }

    public static Claims parseJwt(String jwt, String appKey) {

        if (StringUtils.isBlank(jwt)) {

            return null;
        } else {
            jwt = jwt.replaceAll("Bearer ", "");
            return (Claims)Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(appKey)).parseClaimsJws(jwt).getBody();
        }
    }

    public static TokenUtils.RefreshToken parseRefreshToken(String refreshToken) {
        String content = null;
        TokenUtils.RefreshToken rToken = new TokenUtils.RefreshToken();

        try {
            content = EncryptUtils.decryptHexString(refreshToken);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        if (Objects.nonNull(content)) {
            String[] params = content.split("\\|");
            if (params.length == 3) {
                rToken.setUserAgent(params[0]).setExp(Long.valueOf(params[1])).setUserId(params[2]);
            }
        }

        return rToken;
    }
    public static class RefreshToken {

        private String userAgent;
        private String userId;
        private long exp;


        public String getUserAgent() {
            return this.userAgent;
        }
        public String getUserId() {
            return this.userId;
        }
        public long getExp() {
            return this.exp;
        }

        public TokenUtils.RefreshToken setUserAgent(final String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public TokenUtils.RefreshToken setExp(final long exp) {
            this.exp = exp;
            return this;
        }

        public TokenUtils.RefreshToken setUserId(final String userId) {
            this.userId = userId;
            return this;
        }


    }




}
