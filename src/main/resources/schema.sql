CREATE SEQUENCE id_seq START with 1000 INCREMENT BY 1;
CREATE SEQUENCE cid_seq START with 100 INCREMENT BY 1;

create table ocs_tbl_user_profile(
  userId number,
  firstName varchar2(15),
  lastName varchar2(15),
  dateOfBirth date,
  gender varchar2(7),
  street varchar2(30),
  location varchar2(15),
  city varchar2(15),
  state varchar2(15),
  pincode varchar2(10),
  mobileNo varchar2(10),
  emailId varchar2(30),
  constraint userid_pk primary key(userId)
);

create table ocs_tbl_user_credentials(
  credentialId NUMBER PRIMARY KEY AUTO_INCREMENT,
  userId number,
  password varchar2(20),
  userType varchar2(15),
  loginStatus number(1),
  constraint fk_userid foreign key(userId) references ocs_tbl_user_profile(userId)
);

create table ocs_tbl_patient (
  patientId number,
  userId number,
  appointmentDate DATE Not Null,
  ailmentType VARCHAR2(30) Not Null,
  ailmentDetails VARCHAR2(50) Not Null,
  diagnosisHistory VARCHAR2(200),
  constraint patientid_pk primary key(patientId),
  constraint fk_ocs_tbl_patient_userid foreign key(userId) references ocs_tbl_user_profile(userId)
);

create table ocs_tbl_doctor(
  doctorId NUMBER,
  doctorName varchar2(25) not null,
  dateOfBirth date not null,
  dateOfJoining date not null,
  gender varchar2(15) not null,
  qualification varchar2(25) not null,
  specialization varchar2(25) not null,
  yearsOfExperience number not null,
  street varchar2(25),
  location varchar2(25) not null,
  city varchar2(25) not null,
  state varchar2(25) not null,
  pincode varchar2(6) not null,
  contactNumber varchar2(10),
  emailId varchar2(30) not null,
  CONSTRAINT doctorid_ocs_tbl_doctor_pk PRIMARY KEY (doctorId)
);

create table ocs_tbl_leave(
  leaveId NUMBER,
  doctorId NUMBER,
  leaveFrom date,
  leaveTo date,
  reason varchar2(20),
  status number(1),
  constraint leaveid_ocs_table_leave_pk PRIMARY KEY (leaveId),
  constraint fk_ocs_table_leave_doctorid foreign key(doctorId) references ocs_tbl_doctor(doctorId)
);

create table tbl_schedules(
  scheduleId NUMBER,
  doctorId NUMBER,
  availableDays varchar2(50),
  slots varchar2(50),
  constraint scheduleid_tbl_schedules_pk PRIMARY KEY (scheduleId),
  constraint fk_schedules_doctorid foreign key(doctorId) references ocs_tbl_doctor(doctorId)
);

create table tbl_appointments(
  appointmentId NUMBER,
  doctorId NUMBER,
  patientId NUMBER,
  appointmentDate date not null,
  appointmentTime varchar2(10) not null,
  constraint tbl_appointments_aid_pk primary key (appointmentId),
  constraint fk_tbl_appointments_doctorid foreign key (doctorId) references ocs_tbl_doctor(doctorId),
  constraint fk_tbl_appointments_patientid foreign key (patientId) references ocs_tbl_patient(patientId)
);

create table ocs_tbl_slots (
  slotnumber number,
  duration varchar2(30) not null,
  constraint ocs_tbl_slots_pk primary key (slotnumber)
);

