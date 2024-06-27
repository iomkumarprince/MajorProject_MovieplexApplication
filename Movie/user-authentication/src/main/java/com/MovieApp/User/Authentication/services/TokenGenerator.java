package com.MovieApp.User.Authentication.services;





import com.MovieApp.User.Authentication.domain.User;

import java.util.Map;

public interface TokenGenerator {
    public Map<String,String> generateToken(User user);

}
