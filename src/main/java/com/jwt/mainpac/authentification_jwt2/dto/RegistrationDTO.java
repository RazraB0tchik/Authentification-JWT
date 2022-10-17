package com.jwt.mainpac.authentification_jwt2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RegistrationDTO {
    private String username;
    private String password;
    private String email;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
