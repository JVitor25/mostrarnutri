package com.gft.starter.core.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

//MappedSuperclass informa ao programa que esta classe se trata de um superclass e que a função dela é só atribuir
//Inheritance informa como vai funcionar a herança
//Usando o joined, fará a tabela de pessoa e as outras vinculadas
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
@Table(name = "tb_person")
public class Person {

	// Gerador UUID
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "uuidPerson", columnDefinition = "char(36)")
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID uuidPerson;

	@NotNull
	private String namePerson;

	@NotNull
	@Size(min = 11, max = 11)
	private String cpfPerson;

	@NotNull
	private int agePerson;

	@NotNull
	@Size(min = 5, max = 100)
	private String loginPerson;

	@NotNull
	@Size(min = 5, max = 100)
	private String passwordPerson;
	
	private boolean role;

	// getters e setters
	public UUID getUuidPerson() {
		return uuidPerson;
	}

	public void setUuidPerson(UUID uuidPerson) {
		this.uuidPerson = uuidPerson;
	}

	public String getNamePerson() {
		return namePerson;
	}

	public void setNamePerson(String namePerson) {
		this.namePerson = namePerson;
	}

	public String getCpfPerson() {
		return cpfPerson;
	}

	public void setCpfPerson(String cpfPerson) {
		this.cpfPerson = cpfPerson;
	}

	public int getAgePerson() {
		return agePerson;
	}

	public void setAgePerson(int agePerson) {
		this.agePerson = agePerson;
	}

	public String getLoginPerson() {
		return loginPerson;
	}

	public void setLoginPerson(String loginPerson) {
		this.loginPerson = loginPerson;
	}

	public String getPasswordPerson() {
		return passwordPerson;
	}

	public void setPasswordPerson(String passwordPerson) {
		this.passwordPerson = passwordPerson;
	}

	public boolean isRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}
}
