package ru.message;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ru.model.db.UpdateEmp;
import ru.model.db.postgres.Emp;

/**
 * Message-Driven Bean implementation class for: UpdateOracle
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/ScottQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "java:/jms/ScottQueue")
public class UpdateOracle implements MessageListener {

	@PersistenceContext(unitName = "oracle-scott")
	private EntityManager emOracle;

	@EJB
	private StateFullScott sf;

	/**
	 * Default constructor.
	 */
	public UpdateOracle() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * XA transaction here because of XA Datasource in wildfly
	 * 
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		System.out.println("========= ON MESSAGE =========");
		sf.info();
		sf.myRemove(); //stateful bean -> memory leak if not called !!! 
		try {
			if (message instanceof ObjectMessage) {
				UpdateEmp ue = message.getBody(UpdateEmp.class);
				Emp emp = ue.getEmp();
				emOracle.merge(emp);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
