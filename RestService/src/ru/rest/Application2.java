package ru.rest;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

//@ApplicationPath(value = "controller2")
public class Application2 {//extends javax.ws.rs.core.Application {
	
	@PostConstruct
	public void init() {
		System.out.println("---Application REST2---");
	}
}
