package ru.model.db.oracle;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dept implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DEPTNO")
	private int id;

	private String dname;

	public Dept() {
	}

	public Dept(int id, String dname) {
		super();
		this.id = id;
		this.dname = dname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

}
