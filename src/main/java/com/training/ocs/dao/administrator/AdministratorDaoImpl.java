package com.training.ocs.dao.administrator;

import com.training.ocs.beans.Appointment;
import com.training.ocs.beans.Doctor;
import com.training.ocs.beans.Leave;
import com.training.ocs.beans.Schedule;
import com.training.ocs.exception.CliniqueException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class AdministratorDaoImpl implements AdministratorDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public String addDoctor(Doctor doctor)  throws CliniqueException {
		try {
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(doctor);
			session.getTransaction().commit();
			session.close();
			return "doctor "+doctor.getDoctorName()+" successfully added";
		}catch(Exception e) {
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
		
	}

	@Override
	public List<Doctor> viewAllDoctors()  throws CliniqueException{
		try {
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Doctor");
			List<Doctor> doctors=query.list();
			session.close();
			return doctors;
		}catch(Exception e) {
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public int removeDoctor(int doctorId)  throws CliniqueException{
		try {
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Doctor d=session.get(Doctor.class,doctorId);
			session.delete(d);
			session.getTransaction().commit();
			session.close();
			return d.getDoctorId();
		}catch(Exception e) {
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public String addSchedule(Schedule schedule)  throws CliniqueException{
		try {
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(schedule);
			session.getTransaction().commit();
			session.close();
			return "doctor "+schedule.getDoctorBean().getDoctorName()+" has been added to the database";
		}catch(Exception e) {
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public int applyLeave(Leave leave)  throws CliniqueException{
		try {
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(leave);
			session.getTransaction().commit();
			session.close();
			return leave.getLeaveId();
		}catch(Exception e) {
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
	}

	@Override
	public List<Appointment> getIntiamteReport()  throws CliniqueException{
		try {
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select * from tbl_appointments a where appointmentDate between "
					+ "(select leaveFrom from ocs_tbl_leave l1 where a.doctorId = l1.doctorId) AND"
					+ "(select leaveTo from ocs_tbl_leave l2 where a.doctorId = l2.doctorId )");
			query.addEntity(Appointment.class);
			List<Appointment> intimateReport=query.list();
			session.close();
			return intimateReport;
		}catch(Exception e) {
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public Doctor updateAndReplaceDoctorOnLeave(Date date, String specilization, int dId, String slot)  throws CliniqueException{
		try {
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select * from (select * from ocs_tbl_doctor where doctorId not in "
					+ "(select doctorId from tbl_appointments where appointmentDate=:ad1 and appointmentTime=:slot) "
					+ "and doctorId not in(select doctorId from  ocs_tbl_leave where :ad2 between leaveFrom and leaveTo) "
					+ "and doctorId in(select doctorId from ocs_tbl_doctor where specialization=:splz) "
					+ "order by yearsOfExperience) "
					+ "where rownum=1");
			query.addEntity(Doctor.class);
			query.setDate("ad1", date);
			query.setString("slot", slot);
			query.setDate("ad2", date);
			query.setString("splz", specilization);
			Doctor nd=(Doctor) query.uniqueResult();
			return nd;
		}catch(Exception e) {
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public List<Appointment> getAppointmentsForDate(Date date)  throws CliniqueException{
		try {
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Appointment where appointmentDate=:date");
			query.setDate("date", date);
			List<Appointment> appointments=query.list();
			session.close();
			return appointments;
		}catch(Exception e) {
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public String updateDoctorOnLeave(int oldId, int newId, int aId)  throws CliniqueException{
		try {
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Query q=session.createQuery("update Appointment set doctorId=:newId where doctorId=:oldId");
			q.setParameter("newId", newId);
			q.setParameter("oldId", oldId);
			int result=q.executeUpdate();
			session.getTransaction().commit();
			session.close();	
			return String.valueOf(result);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}

}
