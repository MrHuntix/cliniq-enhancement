package com.training.ocs.util;

import com.training.ocs.beans.Credentials;
import org.hibernate.Session;
public interface User {
	String login(Credentials credentials, Session session);

}
