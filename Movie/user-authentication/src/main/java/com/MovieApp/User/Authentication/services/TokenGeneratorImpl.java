package com.MovieApp.User.Authentication.services;


import com.MovieApp.User.Authentication.domain.User;
import com.MovieApp.User.Authentication.repository.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class TokenGeneratorImpl implements TokenGenerator{
    @Autowired
    private UserRepo userRepo;
    @Override
    public Map<String, String> generateToken(User user) {
        user.setName(userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword()).getName());
        Map<String,String> result = new HashMap<>();
        Map<String,Object> userData = new HashMap<>();
        userData.put("Name",user.getName());
        userData.put("email",user.getEmail());
        userData.put("password",user.getPassword());
        String myToken = Jwts.builder()
                .setClaims(userData)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"secretKeyForMovieApp")
                .compact();
        result.put("Name",user.getName());
        result.put("email", user.getEmail());
        result.put("token",myToken);
        result.put("Message","Successfully Log in...");
       return result;
    }
}
