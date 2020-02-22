package ru.model;

import javax.ejb.Local;

@Local
public interface FLocal {
	String info();
	String userInfo();
}
