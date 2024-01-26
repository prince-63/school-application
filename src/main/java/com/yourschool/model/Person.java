package com.yourschool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import com.yourschool.annotation.FieldValueMatch;
import com.yourschool.annotation.PasswordValidator;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

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
    @JsonIgnore
    private String pwd;

    @NotBlank(message = "confirm password must not be black!")
    @Size(min = 5, message = "Password must be at least 5 character!")
    @Transient
    @JsonIgnore
    private String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private Roles roles;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
    private YourClass yourClass;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "person_courses",
            joinColumns = {
                    @JoinColumn(name = "person_id", referencedColumnName = "personId")},
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "courseId")})
    private Set<Courses> courses = new HashSet<>();
}
