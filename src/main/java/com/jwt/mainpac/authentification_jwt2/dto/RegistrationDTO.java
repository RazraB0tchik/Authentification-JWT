package com.jwt.mainpac.authentification_jwt2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RegistrationDTO {
    @NotBlank(message = "Username cannot be empty!")
//    @Max(value = 25, message = "Length username can't be more than 25")
    private String username;

    @NotBlank(message = "Password cannot be empty!")
//    @Max(value = 30, message = "Password username can't be more than 25")
    private String password;

    @NotBlank(message = "Email cannot be empty!")
//    @Max(value = 40, message = "Email username can't be more than 25")
    private String email;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
