package ru.model;

import javax.ejb.Remote;

@Remote
public interface FRemote {
	String info();
}
