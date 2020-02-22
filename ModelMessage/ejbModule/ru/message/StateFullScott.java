package ru.message;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.jboss.logging.Logger;

/**
 * Session Bean implementation class StateFullScott
 */
@Stateful
@LocalBean
public class StateFullScott {

	private static final Logger log = Logger.getLogger("TEST.MODEL");
	
	private long id;
	
    /**
     * Default constructor. 
     */
    public StateFullScott() {
        this.id = System.nanoTime();
    }

	@PostConstruct
	public void myInit() {
		id = System.nanoTime();
		log.info("___ StateFullScott id=" + id + " is created___");
	}

	@PreDestroy
	public void myDestroy() {
		log.info("___ StateFullScott id=" + id + " is created___");
	}
	
	public void info() {
		log.info("___ StateFullScott id=" + id + " is created___");
	}
	
	@Remove
	public void myRemove() {
		log.info("___ StateFullScott id=" + id + " is removed___");
	}
}
