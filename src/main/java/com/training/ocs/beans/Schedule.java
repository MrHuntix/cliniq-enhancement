package com.training.ocs.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="tbl_schedules")
@Data @NoArgsConstructor @AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue
    private int scheduleID;
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="doctorId")
    private Doctor doctorBean;
    private String availableDays;
    private String slots;
}
