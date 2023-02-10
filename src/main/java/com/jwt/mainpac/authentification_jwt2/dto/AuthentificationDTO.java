package com.jwt.mainpac.authentification_jwt2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AuthentificationDTO {

//    @NotBlank(message = "Username cannot be empty!")
//    @Max(value = 25, message = "Length username can't be more than 25")
    private String username;

//    @NotBlank(message = "Password cannot be empty!")
//    @Max(value = 30, message = "Length password can't be more than 30")
    private String password;
}
