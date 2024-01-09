package openway.com.security

import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import openway.com.payload.jwt.ClaimsGenerator
import openway.com.payload.jwt.JwtGeneratorOptions
import openway.com.payload.jwt.JwtPayload

interface JwtManager {
    fun generateToken(claims: ClaimsGenerator, secretKey: String, options: JwtGeneratorOptions?): String
    fun getVerifiedClaims(token: String): JwtPayload
    fun getVerifier(secretKey: String): JWTVerifier
    fun getAlgorithm(secretKey: String?): Algorithm
    fun generateTokenPair(claims: ClaimsGenerator): Pair<String, String>
}