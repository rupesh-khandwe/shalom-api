package com.shalom.shalomapi.service;

import com.shalom.shalomapi.model.RefreshToken;
import com.shalom.shalomapi.repository.RefreshTokenRepository;
import com.shalom.shalomapi.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserProfileRepository userRepository;

    @Value("${jwt.refreshtoken.expiry}")
    private Long refreshtokenExpiry;

    public RefreshToken createRefreshToken(String username){
        RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(userRepository.findByUserName(username))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(refreshtokenExpiry)) // set expiry of refresh token to 10 minutes - you can configure it application.properties file
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }
}
