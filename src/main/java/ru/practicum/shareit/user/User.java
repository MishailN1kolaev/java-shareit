package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    @NotBlank(message = "email является обязательным")
    @Email
    private String email;
    @NotBlank(message = "login является обязательным")
    private String name;
}
