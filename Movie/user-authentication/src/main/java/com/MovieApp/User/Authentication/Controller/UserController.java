package com.MovieApp.User.Authentication.Controller;
import com.MovieApp.User.Authentication.domain.User;
import com.MovieApp.User.Authentication.domain.UserDto;
import com.MovieApp.User.Authentication.exception.UserAlreadyException;
import com.MovieApp.User.Authentication.exception.UserException;
import com.MovieApp.User.Authentication.services.TokenGenerator;
import com.MovieApp.User.Authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private  UserService userService;
    @Autowired
    private TokenGenerator tokenGenerator;

    // http://localhost:8081/api/v1/RegisterUser
    @PostMapping("/RegisterUser")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) throws UserAlreadyException {
     User user = new User(userDto.getName(), userDto.getEmail(),userDto.getPassword(), userDto.getPhoneNo(), userDto.getImageName());
        System.out.println(user);
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }
   // http://localhost:8081/api/v1/login
    @PostMapping ("/login")
    public ResponseEntity<?> logIn(@RequestBody User user) throws UserException {
        System.out.println(user);
        User retrivedUser = userService.loginUser(user);
        if(retrivedUser != null){
            return new ResponseEntity<>(tokenGenerator.generateToken(retrivedUser),HttpStatus.OK);
        }
        else {
            return  new ResponseEntity<>("Failed.......",HttpStatus.EXPECTATION_FAILED);
        }
    }
    // http://localhost:8081/api/v1/deleteUser/kumarprincelkh@gmail.com
    @DeleteMapping("/deleteUser/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String  email) throws UserException {
        return new ResponseEntity<>(userService.deleteUser(email),HttpStatus.OK);
    }
    @PostMapping("/updateUser/{email}")
    public ResponseEntity updateUser( @PathVariable String email, @RequestBody UserDto userDto){
        User user = new User(userDto.getName(), userDto.getEmail(),userDto.getPassword(), userDto.getPhoneNo(), userDto.getImageName());
        System.out.println(user);
        return new ResponseEntity<>(userService.updateUser(email,user),HttpStatus.OK);
    }
    @PostMapping("/updatePassword/{email}/{password}")
    public ResponseEntity<?> updatePassword(@PathVariable String email,@PathVariable String password) throws UserException {
        return new ResponseEntity<>(userService.updatePassword(email,password),HttpStatus.OK);
    }
}
