package ru.model.db;

import java.io.Serializable;

import ru.model.db.postgres.Emp;

public class UpdateEmp implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String action;
	private Emp emp;

	public UpdateEmp() {
		
	}

	public UpdateEmp(String action, Emp emp) {
		super();
		this.action = action;
		this.emp = emp;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	@Override
	public String toString() {
		return "UpdateEmp [action=" + action + ", emp=" + emp + "]";
	}
	
	
}
