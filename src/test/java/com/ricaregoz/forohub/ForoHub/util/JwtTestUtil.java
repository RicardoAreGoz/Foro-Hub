package com.dsanchez.forohub.ForoHub.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JwtTestUtil {

    private static final String SECRET_KEY = "mysecretkey"; // Usa la misma clave secreta que en tu configuración
    private static final long EXPIRATION_TIME = 86400000; // 1 día en milisegundos

    public static String generateTestToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY.getBytes()));
    }
}