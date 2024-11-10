package com.example.bebiki_hw2_api.ui.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankAccount {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phone;
    private String ssn;
    private String username;
    private String password;
    private String repeatedPassword;
}
