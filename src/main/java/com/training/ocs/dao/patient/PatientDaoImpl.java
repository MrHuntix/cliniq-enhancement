package com.training.ocs.dao.patient;

import com.training.ocs.beans.Appointment;
import com.training.ocs.beans.Doctor;
import com.training.ocs.exception.CliniqueException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public class PatientDaoImpl implements PatientDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Doctor> getAvailableDoctors() throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select * from ocs_tbl_doctor where doctorId not in(select doctorId from ocs_tbl_leave)");
			query.addEntity(Doctor.class);
			List doctors=query.list();
			session.close();
			return doctors;
		}catch(Exception e){
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}
	
	@Override
	public String checkAppointment(int doctor_id, Date appointment_date, String appointment_time) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select count(doctorId) from tbl_appointments where appointmentDate=:date and appointmentTime=:time and doctorId=:id");
			query.setDate("date", appointment_date);
			query.setString("time", appointment_time);
			query.setParameter("id", doctor_id);
			BigDecimal c=(BigDecimal) query.uniqueResult();
			System.out.println("book:"+c);
			return String.valueOf(c);
		}catch(Exception e){
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}
	
	@Override
	public String bookAppointment(Appointment appointment) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(appointment);
			session.getTransaction().commit();
			session.close();
			return "appointment created for"+appointment.getAppointmentDate()+", on "+appointment.getAppointmentTime();
		}catch(Exception e){
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}
	
	@Override
	public List<Appointment> getAppointments(int user_id) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select * from tbl_appointments where patientId in (select patientId from ocs_tbl_patient where userId=:user_id) order by appointmentDate desc");
			query.addEntity(Appointment.class);
			query.setParameter("user_id", user_id);
			List<Appointment> appointments=query.list();
			session.close();
			return appointments;
		}catch(Exception e){
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}
	@Override
	public Doctor getDoctor(Date date, String time, String specilization) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select * from (select * from ocs_tbl_doctor where specialization=:ailment and doctorId not in"
					+ "(select doctorId from tbl_appointments where appointmentDate=:dates and appointmentTime=:time) and "
					+ "doctorId not in (select doctorId from ocs_tbl_leave) order by yearsOfExperience desc) where rownum=1");
			query.addEntity(Doctor.class);
			query.setString("ailment", specilization);
			query.setDate("dates", date);
			query.setString("time", time);
			Doctor d=(Doctor) query.uniqueResult();
			return d;
		}catch(Exception e){
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}
	@Override
	public List<Appointment> getAppointmentsByDate(Date date, int id) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from AppointmentBean where doctorId=:id and appointmentDate=:date");
			query.setParameter("id", id);
			query.setDate("date", date);
			List<Appointment> appointments=query.list();
			session.close();
			return appointments;
		}catch(Exception e){
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}
	@Override
	public int updatePaientAilment(int pId, String ailment, String details, String history) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Query query=session.createQuery("update Patient set ailmentType=:ailment, ailmentDetails=:details, diagnosisHistory=:history where patientId=:pid");
			query.setString("ailment", ailment);
			query.setString("details", details);
			query.setString("history", history);
			query.setParameter("pid", pId);
			int res=query.executeUpdate();
			session.getTransaction().commit();
			return res;
		}catch(Exception e){
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
	}

}
