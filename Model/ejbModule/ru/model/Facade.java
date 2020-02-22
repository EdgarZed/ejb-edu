package ru.model;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import ru.model.utils.TestInterceptor;

@Stateless
@RolesAllowed(value = { "admins", "users", "bosses" })
@Interceptors(value = { TestInterceptor.class })
public class Facade implements FLocal, FRemote {

	private Logger log = Logger.getLogger("TEST");
	private long id;

	@Resource
	private SessionContext session;

	@PostConstruct
	public void myInit() {
		id = System.nanoTime();
		log.info("___ Facade id=" + id + " is created___");
	}

	@PreDestroy
	public void myDestroy() {
		log.info("___ Facade id=" + id + " is created___");
	}

	@Override
	public String info() {
		try {
			TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 500));
		} catch (InterruptedException e) {
		}
		return "Hello world! id=" + id;
	}

	@RolesAllowed(value = { "users", "bosses" })
	@Override
	public String userInfo() {
		String r = "";

		if (session.isCallerInRole("users")) {
			r = "Role User";
		}

		if (session.isCallerInRole("bosses")) {
			r = "Role Boss";
		}

		return "User:" + session.getCallerPrincipal() + " " + r;
	}
}
