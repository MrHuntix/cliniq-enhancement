package com.training.ocs.bean;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="ocs_tbl_doctor")
@NamedQueries({
	@NamedQuery(name="deletes",query="delete from DoctorBean where doctorid=:id")
})
public class DoctorBean {
	@Id @GeneratedValue
	private int doctorID;
	private String doctorName;
	//@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dateOfBirth;
	//@DateTimeFormat(pattern="MM/dd/yyyy")
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
	private String emailID;
	public int getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public int getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	@Override
	public String toString() {
		return "DoctorBean [doctorID=" + doctorID + ", doctorName=" + doctorName + ", dateOfBirth=" + dateOfBirth
				+ ", dateOfJoining=" + dateOfJoining + ", gender=" + gender + ", qualification=" + qualification
				+ ", specialization=" + specialization + ", yearsOfExperience=" + yearsOfExperience + ", street="
				+ street + ", location=" + location + ", city=" + city + ", state=" + state + ", pincode=" + pincode
				+ ", contactNumber=" + contactNumber + ", emailID=" + emailID + "]";
	}
	
	
}
