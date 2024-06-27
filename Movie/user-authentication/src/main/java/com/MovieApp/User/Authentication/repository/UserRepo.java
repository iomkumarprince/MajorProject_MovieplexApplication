package com.MovieApp.User.Authentication.repository;



import com.MovieApp.User.Authentication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
    public User findByEmailAndPassword(String email, String password);
    public User findByEmail(String email);
}
