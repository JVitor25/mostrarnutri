package com.gft.starter.core.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Definindo que é uma model e o nome da tabela
@Entity
@Table(name = "tb_group")
public class Group {

	// Gerando chave primária, que será o UUID
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "uuidGroup", columnDefinition = "char(36)", nullable = false, unique = true)
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID uuidGroup;

	@Column(name = "costGroup", columnDefinition = "varchar(255)", nullable = false)
	private String costGroup;

	@Column(name = "restrictionGroup", columnDefinition = "varchar(255)", nullable = false)
	private String restrictionGroup;

	@Column(name = "minKcalGroup", columnDefinition = "decimal(15,2)", nullable = false)
	private Float minKcalGroup;

	@Column(name = "maxKcalGroup", columnDefinition = "decimal(15,2)", nullable = false)
	private Float maxKcalGroup;
	
	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("group")
	private List<User> user;
	
	//Relacionamento entre tabela de Group e diet
	@ManyToOne
	@JsonIgnoreProperties("group")
	private Diet diet;

	//Metódos construtores
	public Group(UUID uuidGroup, String costGroup, String restrictionGroup, Float minKcalGroup, Float maxKcalGroup,
			Diet diet) {
		this.uuidGroup = uuidGroup;
		this.costGroup = costGroup;
		this.restrictionGroup = restrictionGroup;
		this.minKcalGroup = minKcalGroup;
		this.maxKcalGroup = maxKcalGroup;
		this.diet = diet;
	}

	public Group() {
		this.uuidGroup = null;
		this.costGroup = null;
		this.restrictionGroup = null;
		this.minKcalGroup = null;
		this.maxKcalGroup = null;
		this.diet = null;
	}

	//Gerando getter and setters
	
	
	public UUID getUuidGroup() {
		return uuidGroup;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public void setUuidGroup(UUID uuidGroup) {
		this.uuidGroup = uuidGroup;
	}

	public String getCostGroup() {
		return costGroup;
	}

	public void setCostGroup(String costGroup) {
		this.costGroup = costGroup;
	}

	public String getRestrictionGroup() {
		return restrictionGroup;
	}

	public void setRestrictionGroup(String restrictionGroup) {
		this.restrictionGroup = restrictionGroup;
	}

	public Float getMinKcalGroup() {
		return minKcalGroup;
	}

	public void setMinKcalGroup(Float minKcalGroup) {
		this.minKcalGroup = minKcalGroup;
	}

	public Float getMaxKcalGroup() {
		return maxKcalGroup;
	}

	public void setMaxKcalGroup(Float maxKcalGroup) {
		this.maxKcalGroup = maxKcalGroup;
	}

	public Diet getDiet() {
		return diet;
	}

	public void setDiet(Diet diet) {
		this.diet = diet;
	}

}