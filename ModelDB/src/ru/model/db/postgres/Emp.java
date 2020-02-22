package ru.model.db.postgres;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity(name = "emp")
@Table(name = "emp", schema = "scott")
@NamedQueries(value = { @NamedQuery(name = "GetEmpsByDeptno", query = "SELECT o FROM emp o WHERE o.deptno = :p") })
public class Emp implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int empno;
	private String ename;
	private String job;
	private Timestamp hiredate;
	private Double sal;
	private Integer deptno;

	public Emp() {
	}

	public Emp(int empno, String ename, String job, Timestamp hiredate, Double sal) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.hiredate = hiredate;
		this.sal = sal;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Timestamp getHiredate() {
		return hiredate;
	}

	public void setHiredate(Timestamp hiredate) {
		this.hiredate = hiredate;
	}

	public Double getSal() {
		return sal;
	}

	public void setSal(Double sal) {
		this.sal = sal;
	}

	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", job=" + job + ", hiredate=" + hiredate + ", sal=" + sal
				+ ", deptno=" + deptno + "]";
	}

}
