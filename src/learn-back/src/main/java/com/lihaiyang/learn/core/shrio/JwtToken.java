package com.lihaiyang.learn.core.shrio;


import lombok.Data;
import org.apache.shiro.authc.HostAuthenticationToken;

@Data
public class JwtToken implements HostAuthenticationToken {


    private String token;
    private String host;


    public JwtToken(String token) {
        this(token, (String)null);
    }

    public JwtToken(String token, String host) {
        this.token = token;
        this.host = host;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }


}
