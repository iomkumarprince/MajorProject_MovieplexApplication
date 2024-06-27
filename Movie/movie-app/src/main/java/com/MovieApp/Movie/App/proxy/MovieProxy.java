package com.MovieApp.Movie.App.proxy;



import com.MovieApp.Movie.App.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-authentication", url = "http://localhost:8081")
public interface MovieProxy {
    @PostMapping("/api/v1/RegisterUser")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto);
    @DeleteMapping("/api/v1/deleteUser/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String  email);
    @PostMapping("/api/v1/updateUser/{email}")
    public ResponseEntity<?> updateUser( @PathVariable String email, @RequestBody UserDto userDto);
    @PostMapping("/api/v1/updatePassword/{email}/{password}")
    public ResponseEntity<?> updatePassword(@PathVariable String email,@PathVariable String password);


}
