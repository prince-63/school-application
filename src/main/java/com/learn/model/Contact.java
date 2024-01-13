package com.learn.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Contact {
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;
}
