package com.giboow.boilerplate.dto;

import com.giboow.boilerplate.dto.validator.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


/**
 * User subscription data transfert object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubscriptionDTO {

    @Email(message = "Email should be valid")
    protected String email;

    @ValidPassword
    protected String password;

    @NotBlank
    protected String firstname;

    @NotBlank
    protected String lastname;

}
