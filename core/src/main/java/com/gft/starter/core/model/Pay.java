package com.gft.starter.core.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Definindo que é uma model e uma tabela no DB
@Entity
@Table(name = "tb_pay")
public class Pay {

	//Gerando chave primária
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "uuidPay", columnDefinition = "char(36)", nullable = false, unique = true)
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID uuidPay;
	
	@Column(name = "valuePay", columnDefinition = "decimal(15,2)", nullable = false)
	private Float valuePay;
	
	@Column(name = "datePay", columnDefinition = "varchar(255)", nullable = false)
	private String datePay;
	
	@Column(name = "dueDatePay", columnDefinition = "varchar(255)", nullable = false)
	private String dueDatePay;
	
	//Relacionamento entre as tabelas user e bookkeeper
	@ManyToOne
	@JsonIgnoreProperties("pay")
	private User user;
	
	@ManyToOne
	@JsonIgnoreProperties("pay")
	private Bookkeeper bookkeeper;

	//Metódos construtores
	public Pay(UUID uuidPay, Float valuePay, String datePay, String dueDatePay, User user, Bookkeeper bookkeeper) {
		this.uuidPay = uuidPay;
		this.valuePay = valuePay;
		this.datePay = datePay;
		this.dueDatePay = dueDatePay;
		this.user = user;
		this.bookkeeper = bookkeeper;
	}
	
	public Pay() {
		this.uuidPay = null;
		this.valuePay = null;
		this.datePay = null;
		this.dueDatePay = null;
		this.user = null;
		this.bookkeeper = null;
	}

	//Metódos getters and setters
	public UUID getUuidPay() {
		return uuidPay;
	}

	public void setUuidPay(UUID uuidPay) {
		this.uuidPay = uuidPay;
	}

	public Float getValuePay() {
		return valuePay;
	}

	public void setValuePay(Float valuePay) {
		this.valuePay = valuePay;
	}

	public String getDatePay() {
		return datePay;
	}

	public void setDatePay(String datePay) {
		this.datePay = datePay;
	}

	public String getDueDatePay() {
		return dueDatePay;
	}

	public void setDueDatePay(String dueDatePay) {
		this.dueDatePay = dueDatePay;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bookkeeper getBookkeeper() {
		return bookkeeper;
	}

	public void setBookkeeper(Bookkeeper bookkeeper) {
		this.bookkeeper = bookkeeper;
	}
}
