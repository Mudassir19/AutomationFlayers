package com.xebia.automating_flyers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mail_flayers")
public class MailFlayersModel {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Override
	public String toString() {
		return "MailFlayersModel [id=" + id + ", category=" + category + ", subject=" + subject + ", imagePath="
				+ imagePath + ", status=" + status + "]";
	}
	private String category;
	private String subject;
	private String imagePath;
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
