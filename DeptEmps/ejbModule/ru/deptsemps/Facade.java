package ru.deptsemps;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ru.model.db.UpdateEmp;
import ru.model.db.oracle.Dept;
import ru.model.db.postgres.Emp;

@Stateless
@LocalBean
@Path(value = "/hr")
@Produces(value = "application/json")
@Consumes(value = "application/json")
@TransactionManagement(value = TransactionManagementType.CONTAINER) 
public class Facade {

	@PersistenceContext(unitName = "oracle-scott")
	private EntityManager emOracle;

	@PersistenceContext(unitName = "postgres-scott")
	private EntityManager emPostgres;

	@Resource(mappedName = "java:/jms/ScottFactory")
	private QueueConnectionFactory qcf;
	
	@Resource(mappedName = "java:/jms/ScottQueue")
	private Queue queue;
	
	@Path(value = "/depts")
	@GET
	public List<Dept> getDepts() {
		return emOracle.createNativeQuery("select * from dept order by dname", Dept.class).getResultList();
	}

	@Path(value = "/emps/{deptno}")
	@GET
	public List<Emp> getEmps(@PathParam(value = "deptno") int deptno) {
		return emPostgres.createNamedQuery("GetEmpsByDeptno", Emp.class).setParameter("p", deptno).getResultList();
	}

	@Path(value = "/emp")
	@POST
	public Emp getEmp(int empno) throws Exception {
		Emp emp = emPostgres.find(Emp.class, empno);
		if (emp == null) {
			throw new Exception("Not found " + empno);
		} else {
			return emp;
		}
	}

	@Path(value = "/updateemp")
	@POST
	@TransactionAttribute(value = TransactionAttributeType.REQUIRED) 
	public Emp updateEmp(Emp emp) throws Exception {
		Emp oldemp = emPostgres.find(Emp.class, emp.getEmpno());
		if (oldemp == null) {
			throw new Exception("Not found " + emp.getEmpno());
		} else {
			emPostgres.merge(emp);
			UpdateEmp ue = new UpdateEmp("", emp);
			sendMessage(ue);
			return emp;
		}
	}

	/*
	 * Send message in XA transaction
	 */
	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	private void sendMessage(UpdateEmp ue) {
		try (Connection conn = qcf.createConnection()){
			try (Session sess = conn.createSession(Session.AUTO_ACKNOWLEDGE)){
				try (MessageProducer mp = sess.createProducer(queue)){
					ObjectMessage om = sess.createObjectMessage(ue);
					mp.send(om);
				} catch (Exception e) {
					throw e;
				}
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
