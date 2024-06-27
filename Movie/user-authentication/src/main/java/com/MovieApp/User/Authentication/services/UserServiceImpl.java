package com.MovieApp.User.Authentication.services;


import com.MovieApp.User.Authentication.domain.User;
import com.MovieApp.User.Authentication.exception.UserAlreadyException;
import com.MovieApp.User.Authentication.exception.UserException;
import com.MovieApp.User.Authentication.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepo userRepo;
   @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyException {
       if(userRepo.findById(user.getEmail()).isEmpty())
        return userRepo.save(user);
       else{
           throw new UserAlreadyException();
       }
    }

    @Override
    public User loginUser(User user) throws UserException {
       return userRepo.findByEmailAndPassword(user.getEmail(),user.getPassword());
    }

    @Override
    public User updateUser(String email, User user) {
       User existingUser = userRepo.findByEmail(email);
       if(existingUser!=null)
       {
           existingUser.setName(user.getName());
//           existingUser.setImageName(user.getImageName());
           existingUser.setPassword(user.getPassword());
//           existingUser.setPhoneNo(user.getPhoneNo());
           User upadatedUser = userRepo.save(existingUser);
           return upadatedUser;
       }
        return null;
    }

    @Override
    public boolean deleteUser(String email) throws UserException {
       User user = userRepo.findByEmail(email);
       if(user!=null){
           userRepo.deleteById(email);
           return true;
       }
       else{
           throw new UserException();
       }

    }

    @Override
    public User updatePassword(String email, String password) throws UserException {
        Optional<User> optionalUser = userRepo.findById(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (password != null) {
                user.setPassword(password);
                userRepo.save(user);
            }
            return user;
        } else {
            throw new UserException();
        }
    }



}
