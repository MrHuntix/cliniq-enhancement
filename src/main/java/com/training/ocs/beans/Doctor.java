package com.training.ocs.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "ocs_tbl_doctor")

@NamedQueries({
        @NamedQuery(name="deletes",query="delete from Doctor d where d.doctorId=:id")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor {
    @Id
    @GeneratedValue
    private int doctorId;
    private String doctorName;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Temporal(TemporalType.DATE)
    private Date dateOfJoining;
    private String gender;
    private String qualification;
    private String specialization;
    private int yearsOfExperience;
    private String street;
    private String location;
    private String city;
    private String state;
    private String pincode;
    @Pattern(regexp = "[1-9]{1}[0-9]{9}", message = "cell number not valid")
    private String contactNumber;
    @Email(message = "email id not valid")
    private String emailId;
}
