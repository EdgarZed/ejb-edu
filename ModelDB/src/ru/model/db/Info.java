package ru.model.db;

import java.io.Serializable;
import java.time.LocalTime;

public class Info implements Serializable {
	
	private String caption;
	private LocalTime time;
	private String oracleInfo;
	private String postgresInfo;
	
	public Info() {
		
	}
	
	public Info(String caption, LocalTime time) {
		super();
		this.caption = caption;
		this.time = time;
	}
	
	public String getOracleInfo() {
		return oracleInfo;
	}

	public void setOracleInfo(String oracleInfo) {
		this.oracleInfo = oracleInfo;
	}

	public String getPostgresInfo() {
		return postgresInfo;
	}

	public void setPostgresInfo(String postgresInfo) {
		this.postgresInfo = postgresInfo;
	}

	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
}
