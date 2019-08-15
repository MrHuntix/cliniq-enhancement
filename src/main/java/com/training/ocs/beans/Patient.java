package com.training.ocs.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ocs_tbl_patient")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {
    @Id
    @GeneratedValue
    private int patientId;
    private int userId;
    @Temporal(TemporalType.DATE)
    private Date appointmentDate;
    private String ailmentType;
    private String ailmentDetails;
    private String diagnosisHistory;
}
