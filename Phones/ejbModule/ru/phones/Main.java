package ru.phones;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ru.model.db.Info;
import ru.model.db.Message;

@Stateless
@LocalBean
@Path(value = "/phones")
@Produces(value = "application/json")
@Consumes(value = "application/json")
public class Main {

	@PersistenceContext(unitName = "oracle-scott")
	private EntityManager emOracle;

	@PersistenceContext(unitName = "postgres-scott")
	private EntityManager emPostgres;

	@Path(value = "/info")
	@GET
	public Info info() {
		Info result = new Info("Info", LocalTime.now());
		result.setOracleInfo(emOracle.toString());
		result.setPostgresInfo(emPostgres.toString());
		return result;
	}

	@Path(value = "/phones")
	@GET
	public List<Object[]> getPhones(@QueryParam(value = "find") String number) {
		//System.out.println("+++++++");
		Thread.currentThread().setName("+++JDBC-Phones+++");
		if (number.length() > 2) {
			return emOracle.createNativeQuery("select * from base where nomer like ? ||'%'").setParameter(1, number)
					.getResultList();
		} else {
			return Collections.singletonList(new Object[] { new Message(1, "Number is less than 2") });
		}
	}
}
