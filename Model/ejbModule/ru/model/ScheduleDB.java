package ru.model;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class ScheduleDB {
	private Logger log = Logger.getLogger("TEST");

	@PersistenceContext(unitName = "ModelPG")
	private EntityManager em;

	@PostConstruct
	public void myInit() {
		log.info("---Module Model started---");
	}

	@PreDestroy
	public void myDestroy() {
		log.info("---Module Model destroyed---");
	}

	@Schedule(hour = "*", minute = "*", second = "*/60")
	public void testConnect() throws NamingException {
		Context context = new InitialContext();
		String error = "";
		try {
			em.createNativeQuery("select now()").getResultList();
			log.info("Ok");
		} catch (Exception e) {
			log.info("---Test Connect failed!!!---" + em);
			error = e.getMessage();
		}
		context.rebind("java:/error", error);
	}
}
