package com.MovieApp.User.Authentication;

import com.MovieApp.User.Authentication.domain.User;
import com.MovieApp.User.Authentication.repository.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepoTest {
    @Autowired
    private UserRepo userRepo;
    private User user;
    @BeforeEach
    public void setUp(){
        user = new User("Kumar Prince", "kumarprincelkh@gmail.com", "secret456", "9326688553", "/path/to/profile.jpg");

    }
    @AfterEach
    public void tearDown(){
        user=null;
        userRepo.deleteAll();

    }
    @Test
    public void addUserSuccess(){
        User insteredUser = userRepo.save(user);
        assertEquals(user,insteredUser);
    }

    @Test
    public void addUserFailure(){
        User insteredUser = userRepo.save(user);
        User newUser = new User("Kumar Prince", "kumarprincelkh@gmail.com", "secret456", "9326688553", "/path/to/profile.jpg");
      User savedUser=  userRepo.save(newUser);
        assertNotEquals(savedUser,insteredUser);
    }
    @Test
    public void deleteUserSuccess(){
        userRepo.save(user);
        userRepo.deleteById(user.getEmail());
        List<User> userList = userRepo.findAll();
        System.out.println(userList);
        assertEquals(0,userList.size());
    }
    @Test
    public void  deleteUserFailure(){
        userRepo.save(user);
        boolean userExists = userRepo.existsById("abc@example.com");
        if(userExists){
            userRepo.deleteById("abc@example.com");
        }
        else{
            System.out.println("user not found");
        }
        List<User> userList = userRepo.findAll();
        System.out.println(userList);
        assertEquals(1, userList.size());
    }
    @Test
    public void logInSuccess(){
        userRepo.save(user);
        User user1 =userRepo.findByEmailAndPassword(user.getEmail(),user.getPassword());
        assertEquals(user1,user);
    }
    @Test
    public void logInFailure(){
        User user1=null;
        userRepo.save(user);
        boolean userExists = userRepo.existsById("abc@example.com");
        if(userExists){
            user1 = userRepo.findByEmailAndPassword("xyz@gmail.com",user.getPassword());
        }
        else{
            System.out.println("user not found");
        }
        assertNotEquals(user1,user);

    }



}
