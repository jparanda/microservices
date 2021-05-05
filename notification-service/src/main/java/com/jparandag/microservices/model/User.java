package com.jparandag.microservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class User implements Serializable {

    @JsonProperty("user")
    private String user;

    @JsonProperty("email")
    private String email;

    @JsonProperty("notification")
    private String notification;
}
