package com.jparandag.microservices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDTO  implements Serializable {

    @Email
    @NotNull
    @NotEmpty
    @JsonProperty("email")
    private String email;

    @NotNull
    @Size(min=2, message="User should have at least 2 characters")
    @JsonProperty("user")
    private String user;

    @NotNull
    @JsonProperty("notification")
    private String notification;
}
