package ru.model;

import java.time.LocalTime;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@Local
@WebService
public class Info {

	public String time() {
		return LocalTime.now().toString();
	}
}
