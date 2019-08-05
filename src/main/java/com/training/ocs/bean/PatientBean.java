package com.training.ocs.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ocs_tbl_patient")
public class PatientBean {
	@Id @GeneratedValue
	private int patientID;
	private String userID;
	private Date appointmentDate; 
	//@Column(name="ailment_type")
	private String ailmentType;
	//@Column(name="ailment_details")
	private String ailmentDetails;
	//@Column(name="diagnosis_history")
	private String diagnosisHistory;
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAilmentType() {
		return ailmentType;
	}
	public void setAilmentType(String ailmentType) {
		this.ailmentType = ailmentType;
	}
	public String getAilmentDetails() {
		return ailmentDetails;
	}
	public void setAilmentDetails(String ailmentDetails) {
		this.ailmentDetails = ailmentDetails;
	}
	public String getDiagnosisHistory() {
		return diagnosisHistory;
	}
	public void setDiagnosisHistory(String diagnosisHistory) {
		this.diagnosisHistory = diagnosisHistory;
	}
	@Override
	public String toString() {
		return "PatientBean [patientID=" + patientID + ", userID=" + userID + ", appointmentDate=" + appointmentDate
				+ ", ailmentType=" + ailmentType + ", ailmentDetails=" + ailmentDetails + ", diagnosisHistory="
				+ diagnosisHistory + "]";
	}
	
}
