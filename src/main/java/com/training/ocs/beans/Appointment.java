package com.training.ocs.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tbl_appointments")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Appointment {
    @Id
    @GeneratedValue
    private int appointmentID;
    private String doctorID;
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="patientId")
    private Patient patientBean;
    //@Column(name="appointment_date")
    private Date appointmentDate;
    //@Column(name="appointment_time")
    private String appointmentTime;
}
