package com.training.ocs.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="ocs_tbl_leave")
@Data @NoArgsConstructor @AllArgsConstructor
public class Leave {
    @Id
    @GeneratedValue
    private int leaveId;
    private String doctorId;
    private Date leaveFrom;
    private Date leaveTo;
    private String reason;
    private int status;
}
