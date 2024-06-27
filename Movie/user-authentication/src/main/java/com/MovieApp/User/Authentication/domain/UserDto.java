package com.MovieApp.User.Authentication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class UserDto {

    private String name;
    @Id
    private String email;
    private String password;
    private String phoneNo;
    private String imageName;
}
