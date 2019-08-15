package com.training.ocs.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ocs_tbl_user_credentials")
@NamedQueries({
        @NamedQuery(name="verify",query="from Credentials c where c.credentialId=:id"),
        @NamedQuery(name="update_login_status",query="update Credentials c set c.loginStatus=1 where c.credentialId=:id"),
        @NamedQuery(name="update_logout_status",query="update Credentials c set c.loginStatus=0 where c.credentialId=:id")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Credentials {
    @Id
    private int credentialId;
    @OneToOne
    @JoinColumn(name = "userId")
    private Profile profileBean;
    private String password;
    private String userType;
    private int loginStatus;

}
