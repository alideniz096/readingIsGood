package com.getir.readingIsGood.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(value = "/tokenApi")
public class SecurityController {
    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @Value("${userName}")
    private String name;
    @Value("${userPassword}")
    private String userPassword;
    @Value("${jwt.expireTime}")
    private String expireTime;

    @PostMapping("/getToken")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> getToken(@RequestParam String username, @RequestParam String password) {
        if (username.equals(name) && password.equals(userPassword)) {
            logger.debug("Username={} got token successfully.", username);
            return ResponseEntity.ok(getJWTToken(username));
        } else {
            return ResponseEntity.ok("Username or password not correct");
        }
    }

    private String getJWTToken(String username) {
        String secretKey = "secretKey007";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts.builder().setId("id").setSubject(username)
                .claim("authorities", grantedAuthorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Integer.parseInt(expireTime)))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
        return "Bearer " + token;
    }
}
