package com.training.ocs.dao.reporter;

import com.training.ocs.beans.Appointment;
import com.training.ocs.beans.Doctor;
import com.training.ocs.exception.CliniqueException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ReporterDaoImpl implements ReporterDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Appointment> intimateAdmin() throws CliniqueException{//Date date, String status
		// TODO Auto-generated method stub
		try{
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select * from tbl_appointments a where appointmentDate between "
					+ "(select leaveFrom from ocs_tbl_leave l1 where a.doctorId = l1.doctorId) AND"
					+ "(select leaveTo from ocs_tbl_leave l2 where a.doctorId = l2.doctorId )");
			query.addEntity(Appointment.class);
			List<Appointment> appointedPatient=query.list();
			session.close();
			System.out.println(appointedPatient);
			return appointedPatient;
		}catch(Exception e){
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public List<Doctor> getAvailableDoctors(Date date, String slot) throws CliniqueException{
		// TODO Auto-generated method stub
		try{
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select * from ocs_tbl_doctor where doctorId not in(select doctorId from tbl_appointments where appointmentDate=:appdate and appointmentTime=:appslot) and doctorId not in(select doctorId from  ocs_tbl_leave where :appdate1 between leaveFrom and leaveTo)");
			query.setDate("appdate", date);
			query.setString("appslot", slot);
			query.setDate("appdate1", date);
			query.addEntity(Doctor.class);
			List doctors=query.list();
			session.close();
			System.out.println("reporter: "+doctors);
			return doctors;
		}catch(Exception e){
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}

}
