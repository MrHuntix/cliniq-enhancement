package com.training.ocs.controller;

import com.training.ocs.beans.Appointment;
import com.training.ocs.beans.Credentials;
import com.training.ocs.beans.Doctor;
import com.training.ocs.beans.Profile;
import com.training.ocs.exception.CliniqueException;
import com.training.ocs.service.patient.Patient;
import com.training.ocs.service.register.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TestController {
	
		@Autowired
			RegisterService registerService;
		@Autowired
			Patient patientService;
			
			//redirects the user to the index page
			@RequestMapping(value= {"/","home"})
			public ModelAndView login(HttpSession session){
				Credentials sessionbean=(Credentials) session.getAttribute("profile");
				System.out.println("current session: "+sessionbean);
				if(sessionbean==null)
					return new ModelAndView("index");
				else if(sessionbean.getUserType().equals("a")&&sessionbean.getLoginStatus()==1)
					return new ModelAndView("administrator");
				else if(sessionbean.getUserType().equals("p")&&sessionbean.getLoginStatus()==1)
					return new ModelAndView("patient");
				else if(sessionbean.getUserType().equals("r")&&sessionbean.getLoginStatus()==1)
					return new ModelAndView("reporter");
				return new ModelAndView("index");
			}
			
			//registers the user into the database.
			//we need to auto generate a proper id so direct registration will give error 
			@RequestMapping(value="add",method=RequestMethod.POST)
			public ModelAndView register(@Valid @ModelAttribute("profile")Profile p, BindingResult b, @RequestParam("userType")String userType, @RequestParam("password")String password) {//@Valid @ModelAttribute("profile")ProfileBean p
				if(b.hasErrors())
					System.out.println("err: "+b.getFieldError().getDefaultMessage());
				System.out.println("register: "+p);
				System.out.println("usertype: "+userType+", password: "+password);
				Credentials c=new Credentials();
				c.setPassword(password);
				c.setUserType(userType);
				c.setProfileBean(p);
				String username = null;
				try {
					username = registerService.registerUser(c);
				} catch (CliniqueException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ModelAndView("error","errormsg",e.getMessage());
				}
				System.out.println("username: "+username);
				return new ModelAndView("index","username",username);
			}
			
			//redirects user to register page
			@RequestMapping(value="register")
			public ModelAndView registerEmployee() {
				System.out.println("/register: registerEmployee()");
				return new ModelAndView("register");
			}
		 
			@RequestMapping(value="login",method=RequestMethod.POST)
			public ModelAndView loginUser(HttpSession session,@RequestParam("username")String username,@RequestParam("password")String password) {
				Credentials c=new Credentials();
				Profile p=new Profile();
				p.setUserId(Integer.parseInt(username));
				c.setPassword(password);
				c.setCredentialId(Integer.parseInt(username));
				c.setProfileBean(p);
				String message = null;
				try {
					message = registerService.loginUser(c);
				} catch (CliniqueException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ModelAndView("error","errormsg",e.getMessage());
				}
				if(message.equals("invalid")) {
					return new ModelAndView("index","message","user does not exist");
				}else if(message.equals("fail")) {
					return new ModelAndView("index","message","username/password is incorrect");
				}
				else if(message.equals("a")) {
					try {
						c=registerService.getuser(Integer.parseInt(username));
					} catch (CliniqueException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return new ModelAndView("error","errormsg",e.getMessage());
					}
					session.setAttribute("profile",c);
					return new ModelAndView("administrator");
				}
				else if(message.equals("p")) {
					try {
						c=registerService.getuser(Integer.parseInt(username));
					} catch (CliniqueException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return new ModelAndView("error","errormsg",e.getMessage());
					}
					SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
					Date d=new Date();
					session.setAttribute("profile",c);
					return new ModelAndView("patient","date",formatter.format(d));
				} 
				else {
					try {
						//session.setAttribute("profile",c);
						c=registerService.getuser(Integer.parseInt(username));
					} catch (CliniqueException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return new ModelAndView("error","errormsg",e.getMessage());
					}
					session.setAttribute("profile",c);
					return new ModelAndView("reporter");
				}
				
			}
			@RequestMapping(value="patienthome")
			public ModelAndView patientHome(HttpSession session){
				Credentials sessionbean=(Credentials) session.getAttribute("profile");
				if(sessionbean==null)
					return new ModelAndView("index");
				return new ModelAndView("patient");
			}
			@RequestMapping(value="logout")
			public ModelAndView logout(HttpSession session,@RequestParam("patientId")String id) {
				System.out.println("in logout: ");
				System.out.println(session.getAttribute("profile"));
				int res ;
				try {
					res = registerService.logoutUser(Integer.parseInt(id));
				} catch (CliniqueException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ModelAndView("error","errormsg",e.getMessage());
				}
				session.invalidate();
				return new ModelAndView("logout");
			}
			
			@RequestMapping(value="confirmation",method=RequestMethod.POST)
			public ModelAndView bookapp(@RequestParam("date")java.sql.Date date,@RequestParam("slot")String slot,@RequestParam("did")String id
					,@RequestParam("ailment")String ailment,@RequestParam("details")String details,
					@RequestParam("history")String history,@RequestParam("pid")String pid){
				//System.out.println("date: "+date+", slot: "+slot+", id: "+id+", ailment: "+ailment+", history: "+history+", details: "+details+", patientid: "+pid);
				System.out.println("date: "+date.toString());
				String c = null;
				try {
					c = patientService.checkAppointment(Integer.parseInt(id), date, slot);
				} catch (CliniqueException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ModelAndView("error","errormsg",e.getMessage());
				}
				System.out.println("test controller success fail: "+c);
				String message="";
				if(c.equals("1"))
					message="slot "+slot+" for date "+date+" is not available";
				else{
					Appointment a=new Appointment();
					com.training.ocs.beans.Patient p=new com.training.ocs.beans.Patient();
					p.setUserId(Integer.parseInt(pid));
					p.setAppointmentDate(date);
					p.setAilmentDetails(details);
					p.setAilmentType(ailment);
					p.setDiagnosisHistory(history);
					a.setPatientBean(p);
					a.setDoctorID(id);
					a.setAppointmentDate(date);
					a.setAppointmentTime(slot);
					System.out.println("appointment: "+a);
					try {
						message=patientService.bookAppointment(a);
					} catch (CliniqueException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return new ModelAndView("error","errormsg",e.getMessage());
					}
				}
				return new ModelAndView("patient","getappointmentsfordatemsg",message);
			}

			
			@RequestMapping(value="bookappointments",method=RequestMethod.POST)
			public ModelAndView bookappointment(@RequestParam("date")java.sql.Date date,@RequestParam("slot")String slot,
					@RequestParam("ailment")String ailment,@RequestParam("details")String details,
					@RequestParam("history")String history,@RequestParam("pid")String patient){
				ModelAndView mv=new ModelAndView();
				String msg="";
				Doctor doctorBean = null;
				try {
					doctorBean = patientService.getDoctor(date, slot, ailment);
				} catch (CliniqueException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ModelAndView("error","errormsg",e.getMessage());
				}
				System.out.println("doctor result: "+doctorBean);
				if(doctorBean==null) {
					msg="no doctor available for the combination( "+date+", "+slot+", "+ailment+" )";
				}else {
					com.training.ocs.beans.Patient p=new  com.training.ocs.beans.Patient();
					p.setUserId(Integer.parseInt(patient));
					p.setAppointmentDate(date);
					p.setAilmentType(ailment);
					p.setAilmentDetails(details);
					p.setDiagnosisHistory(history);
					Appointment a=new Appointment();
					a.setDoctorID(String.valueOf(doctorBean.getDoctorId()));
					a.setPatientBean(p);
					a.setAppointmentDate(date);
					a.setAppointmentTime(slot);
					try {
						patientService.bookAppointment(a);
					} catch (CliniqueException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return new ModelAndView("error","errormsg",e.getMessage());
					}
					msg="appointment booked successfully for the date "+date+" with doctor "+doctorBean.getDoctorName();
				}
				mv.addObject("getappointmentsfordatemsg", msg);
				mv.setViewName("patient");
				return mv;
			}
			
			@RequestMapping(value="showappointmentsp")
			public ModelAndView showappointments(@RequestParam("patientId")String id) {
				System.out.println("id: "+id);
				List<Appointment> apps = null;
				try {
					apps = patientService.getAppointments(Integer.parseInt(id));
				} catch (CliniqueException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ModelAndView("error","errormsg",e.getMessage());
				}
				System.out.println("appointments: "+apps);
				return new ModelAndView("patient","appointments",apps);
			}
			
			@RequestMapping(value="getappointmentsfordate",method=RequestMethod.POST)
			public ModelAndView getAppointmentsForDate(@RequestParam("did")String id,@RequestParam("date")java.sql.Date date) {
				List<Appointment> appointments = null;
				try {
					appointments = patientService.getAppointmentsByDate(date, Integer.parseInt(id));
				} catch (CliniqueException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ModelAndView("error","errormsg",e.getMessage());
				}
				if(appointments.size()==0)
					return new ModelAndView("patient","getappointmentsfordatemsg","doctor has no appointments scheduled on/for "+date);
				return new ModelAndView("patient","doctorapp",appointments);
			}
			
			@RequestMapping(value="updateailmentsconfirmation",method=RequestMethod.POST)
			public ModelAndView updatePatientAilment(@RequestParam("pid")String pid,@RequestParam("ailment")String ailment,
					@RequestParam("details")String details,@RequestParam("history")String history){
				System.out.println("updating ailment");
				int res = 0;
				try {
					res = patientService.updatePaientAilment(Integer.parseInt(pid), ailment, details, history);
				} catch (CliniqueException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ModelAndView("error","errormsg",e.getMessage());
				}
				if(res<1)//showappointments?patientId=pa1021
					return new ModelAndView("patient","getappointmentsfordatemsg","ailment details not updated");
				return new ModelAndView("patient","getappointmentsfordatemsg","ailment details successfully updated");
			}
}
