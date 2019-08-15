package com.training.ocs.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "ocs_tbl_user_profile")
@NoArgsConstructor @AllArgsConstructor @Data
public class Profile {
    @Id
    private int userId;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String gender;
    private String street;
    private String location;
    private String city;
    private String state;
    private String pincode;
    @Pattern(regexp = "[1-9]{1}[0-9]{9}", message = "cell number not valid")
    private String mobileNo;
    @Email(message = "email id not valid")
    private String emailId;
}
