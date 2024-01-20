package com.learn.model;

import org.hibernate.annotations.GenericGenerator;

import com.learn.annotation.FieldValueMatch;
import com.learn.annotation.PasswordValidator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@FieldValueMatch.List({
        @FieldValueMatch(field = "pwd", fieldMatch = "confirmPwd", message = "Passwords do not match!"),
        @FieldValueMatch(field = "email", fieldMatch = "confirmEmail", message = "Email addresses do not match!")
})
public class Person extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    public int personId;

    @NotBlank(message = "Name must not be blank!")
    @Size(min = 3, message = "Name must be at least 3 characters long!")
    private String name;

    @NotBlank(message = "Mobile number must not be blank!")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit!")
    private String mobileNumber;

    @NotBlank(message = "Email must not be black!")
    @Email(message = "Please provice a valid email address!")
    private String email;

    @NotBlank(message = "confirm email must not be black!")
    @Email(message = "Please provice a valid email address!")
    @Transient
    private String confirmEmail;

    @NotBlank(message = "password must not be black!")
    @Size(min = 5, message = "Password must be at least 5 character!")
    @PasswordValidator
    private String pwd;

    @NotBlank(message = "confirm password must not be black!")
    @Size(min = 5, message = "Password must be at least 5 character!")
    @Transient
    private String confirmPwd;
}
