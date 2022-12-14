package com.ecommerce.springbootecommerce.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO extends BaseDTO<AccountDTO> {
    
    private String address;   
    private Double balance;
    private String phoneNumber;
    
    @NotBlank(message = "Please enter your user name!")
    private String userName;
    
    @NotBlank(message = "Please enter your full name!")
    private String fullName;
    
    @NotBlank(message = "Please enter your email!")
    @Email(message = "Please enter a valid email address")
    private String email;
    
    @NotBlank(message = "Please enter your password!")
    @Length(min = 3, message = "Password must be at least 3 characters")
    private String password;    
    
    private String rePassword;
    
    private String newPassword;
    private String ReNewPassword;
    
    private String typeEditProfile;
    
    private boolean status;
}
