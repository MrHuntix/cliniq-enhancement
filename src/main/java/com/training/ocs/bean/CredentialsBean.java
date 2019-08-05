package com.training.ocs.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
//vehicle
@Entity
@Table(name="ocs_tbl_user_credentials")
@NamedQueries({
	@NamedQuery(name="verify",query="from CredentialsBean where useridf=:id"),
	@NamedQuery(name="update_login_status",query="update CredentialsBean set loginstatus=1 where useridf=:id"),
	@NamedQuery(name="update_logout_status",query="update CredentialsBean set loginstatus=0 where useridf=:id")
})
public class CredentialsBean{

	@Id 
	@GeneratedValue//(strategy=GenerationType.SEQUENCE,generator="gen_id")
	//@SequenceGenerator(name="gen_id",sequenceName="cid_seq")
	private int userid;
	private String password;
	private String userType;
	private int loginStatus;
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="useridf")
	private ProfileBean profileBean;
	
	public ProfileBean getProfileBean() {
		return profileBean;
	}
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setProfileBean(ProfileBean profileBean) {
		this.profileBean = profileBean;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}

	@Override
	public String toString() {
		return "CredentialsBean [userid=" + userid + ", password=" + password + ", userType=" + userType
				+ ", loginStatus=" + loginStatus + ", profileBean=" + profileBean + "]";
	}
	
}
