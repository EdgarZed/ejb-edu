package ru.model.db;

import java.io.Serializable;

public class Message implements Serializable {

	private int id;
	private String text;

	public Message() {

	}

	public Message(int id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
