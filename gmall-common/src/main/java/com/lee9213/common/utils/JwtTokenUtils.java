package com.lee9213.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Map;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-21 17:17
 */
public class JwtTokenUtils {

    private static final String key = "7e6d6570cb224fcbb5ed6d570942be7b";

    public static String generatorToken(Map<String, Object> payLoad) {
        
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return Jwts.builder().setPayload(objectMapper.writeValueAsString(payLoad))
                    .signWith(SignatureAlgorithm.HS256, generatorKey()).compact();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Claims phaseToken(String token) {
        Jws<Claims> claimsJwt = Jwts.parser().setSigningKey(generatorKey()).parseClaimsJws(token);

        return claimsJwt.getBody();
    }

    private static Key generatorKey() {
        SignatureAlgorithm saa = SignatureAlgorithm.HS256;
        byte[] bin = DatatypeConverter.parseBase64Binary
                ("f3973b64918e4324ad85acea1b6cbec5");
        Key key = new SecretKeySpec(bin, saa.getJcaName());
        return key;
    }
}
