package com.jwt.mainpac.authentification_jwt2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AuthentificationDTO {
    private String username;
    private String password;
}
