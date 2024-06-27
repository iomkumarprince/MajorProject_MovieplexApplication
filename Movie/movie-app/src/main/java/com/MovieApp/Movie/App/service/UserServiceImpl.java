package com.MovieApp.Movie.App.service;

import com.MovieApp.Movie.App.config.MovieDTO;
import com.MovieApp.Movie.App.domain.Movie;
import com.MovieApp.Movie.App.domain.User;
import com.MovieApp.Movie.App.domain.UserDto;
import com.MovieApp.Movie.App.exception.MovieAlreadyExist;
import com.MovieApp.Movie.App.exception.MovieException;
import com.MovieApp.Movie.App.exception.UserAlreadyException;
import com.MovieApp.Movie.App.exception.UserException;
import com.MovieApp.Movie.App.proxy.MovieProxy;
import com.MovieApp.Movie.App.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jshell.spi.ExecutionControl;
import org.apache.commons.io.FilenameUtils;
import org.bson.json.JsonObject;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private MovieProxy movieProxy;
    private RabbitTemplate rabbitTemplate;
    private DirectExchange directExchange;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, MovieProxy movieProxy, RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.userRepository = userRepository;
        this.movieProxy = movieProxy;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }


    @Override
    public String uploadImage(MultipartFile file) throws IOException {

        // Check if the file is empty
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty.");
        }

        // Construct the complete path to store the file
        String directoryPath = "E:\\Project\\Movieplex\\movieflex\\src\\assets";
        String fileName = file.getOriginalFilename();
        String filePath = directoryPath + File.separator + fileName;

        // Create the directory if it does not exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Save the file to the specified directory
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        // Return the complete path of the saved file
        System.out.println(fileName);
        return fileName;
    }

    @RabbitListener(queues = "EmailQueue")
    @Override
    public User addUser(User user) throws UserAlreadyException {
        System.out.println(user.getEmail());
        Optional<User> existingUser = userRepository.findById(user.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyException();
        } else {
            UserDto userDto = new UserDto(user.getName(), user.getEmail(),
                    user.getPassword(), user.getPhoneNo(), user.getImageName());
            movieProxy.registerUser(userDto);
            System.out.println("in userDTO"+userDto);
            String Message =  "Dear " + user.getName() + ",\n\n" +
                    "Thank you for joining Movieplex! We are delighted to have you as a participant in our event.\n\n" +
                    user.getName() + ", we are excited to have you on our participant list and can't wait to see your contributions.\n\n" +
                    "If you have any questions or need assistance, feel free to reach out to our support team.\n\n" +
                    "Once again, welcome to Movieplex!\n\n" +
                    "Best regards,\n" +
                    "Team Movieplex\n";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("to",user.getEmail());
            jsonObject.put("subject", "Welcome to Movieplex!");
            jsonObject.put("message",Message);
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setJsonObject(jsonObject);
            rabbitTemplate.convertAndSend(directExchange.getName(),"Movie_routing",movieDTO);
            System.out.println("Success: "+directExchange.getName()+movieDTO);
            System.out.println("after save"+user);
            return userRepository.save(user);
        }
    }


    @Override
    public User updateUserWithEmail(MultipartFile file, String userEmail, String userJson) throws IOException {
        // Find the user by email
        User existingUser = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("user error"));

        // Update user details
        User updatedUser = new ObjectMapper().readValue(userJson, User.class);
        existingUser.setName(updatedUser.getName());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setPhoneNo(updatedUser.getPhoneNo());
        // ... other fields you want to update

        // Update favorite movies
        List<Movie> favoriteMovies = updatedUser.getFavouriteMovielist();
        if (favoriteMovies != null) {
            existingUser.setFavouriteMovielist(favoriteMovies);
        }

        // Update image details
        byte[] imageBytes = file.getBytes();
        existingUser.setImagePath(imageBytes);

        String originalFilename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(originalFilename)
                + "_" + Instant.now().toEpochMilli()
                + "." + FilenameUtils.getExtension(originalFilename);

        existingUser.setImageName(newFileName);

        return userRepository.save(existingUser);
    }



    @Override
    public boolean deleteUser(String id) throws UserException {
        if (!userRepository.findById(id).isEmpty()) {
            movieProxy.deleteUser(id);
            userRepository.deleteById(id);
            return true;
        } else {
            throw new UserException();
        }
    }

    @Override
    public List<Movie> getAllFavouriteMovie(String id) throws UserException {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get().getFavouriteMovielist();
        } else {
            throw new UserException();
        }
    }

    @Override
    public User addMovieToFavorite(String email, Movie movie) throws MovieAlreadyExist, UserException {
        Optional<User> optionalUser = userRepository.findById(email);
        if (!optionalUser.isPresent()) {
            throw new UserException();
        }

        User user = optionalUser.get();
        if (user.getFavouriteMovielist() != null) {
            List<Movie> movieList = user.getFavouriteMovielist();
            for (Movie movieIterator : movieList) {
                if (movieIterator.getId() == movie.getId()) {
                    throw new MovieAlreadyExist();
                }
            }

            movieList.add(movie);
            user.setFavouriteMovielist(movieList);
        }
        return userRepository.save(user);
    }


    @Override
    public boolean deleteFavoriteMovie(String email, Movie movie) throws MovieException, UserException {
        Optional<User> optionalUser = userRepository.findById(email);
        if (!optionalUser.isPresent()) {
            throw new UserException();
        }

        User user = optionalUser.get();
        List<Movie> movieList = user.getFavouriteMovielist();

        boolean movieFound = false;
        for (Movie movieIterator : movieList) {
            if (movieIterator.getId() == movie.getId()) {
                movieList.remove(movieIterator);
                user.setFavouriteMovielist(movieList);
                userRepository.save(user);
                movieFound = true;
                break;
            }
        }

        if (!movieFound) {
            throw new MovieException();
        }

        return true;
    }


    @Override
    public User getUser(String email) throws UserException {
        if (userRepository.findById(email).isPresent()) {
            return userRepository.findById(email).get();
        } else {
            throw new UserException();
        }
    }


    @Override
    public byte[] getUserProfileImg(String email) {
        User user = userRepository.findById(email).get();
        if (user.getEmail() != null) {
            System.out.println(user.getImagePath());
            return user.getImagePath();
        } else {
            return null;
        }
    }

    @Override
    public String updatePassword(String email, String password) throws UserException {
        Optional<User> userOptional = userRepository.findById(email);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            if (password != null) {
                existingUser.setPassword(password);
                movieProxy.updatePassword(email, password);
                userRepository.save(existingUser);

                return "Password updated Successfully.";
            } else {
                return "Please provide a valid password.";
            }

        } else {
            throw new UserException();
        }
    }



@Override
    public User updateFullUserDetails(User updatedUser) {
    if(userRepository.findById(updatedUser.getEmail()).isPresent()) {
        return userRepository.save(updatedUser);
    }
    else{
        return null;
    }
    }
}
