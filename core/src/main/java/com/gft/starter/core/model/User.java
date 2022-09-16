package com.gft.starter.core.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Declaração de entidade e nome da tabela do banco de dados
@Entity
@Table(name="tb_user")
public class User extends Person {
	
	@NotNull
	private boolean statusUser;
	
	@NotNull
	private String objectiveUser;
	
	@NotNull
	private int heightUser;
	
	@NotNull
	private Float weightUser;
	
	@NotNull
	private Float bmrUser;

	@NotNull
	private String restrictionUser;
	
	@NotNull
	private String costUser;

	@ManyToOne
	@JsonIgnoreProperties("user")
	private Group group;
	

	public User(@NotNull boolean statusUser, @NotNull String objectiveUser, @NotNull int heightUser,
			@NotNull Float weightUser, @NotNull Float bmrUser, @NotNull String restrictionUser,
			@NotNull String costUser) {
		super();
		this.statusUser = statusUser;
		this.objectiveUser = objectiveUser;
		this.heightUser = heightUser;
		this.weightUser = weightUser;
		this.bmrUser = bmrUser;
		this.restrictionUser = restrictionUser;
		this.costUser = costUser;
	}

	//getters e setters
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public boolean isStatusUser() {
		return statusUser;
	}

	public void setStatusUser(boolean statusUser) {
		this.statusUser = statusUser;
	}

	public String getObjectiveUser() {
		return objectiveUser;
	}

	public void setObjectiveUser(String objectiveUser) {
		this.objectiveUser = objectiveUser;
	}

	public int getHeightUser() {
		return heightUser;
	}

	public void setHeightUser(int heightUser) {
		this.heightUser = heightUser;
	}

	public Float getWeightUser() {
		return weightUser;
	}

	public void setWeightUser(Float weightUser) {
		this.weightUser = weightUser;
	}

	public Float getBmrUser() {
		return bmrUser;
	}

	public void setBmrUser(Float bmrUser) {
		this.bmrUser = bmrUser;
	}

	public String getRestrictionUser() {
		return restrictionUser;
	}

	public void setRestrictionUser(String restrictionUser) {
		this.restrictionUser = restrictionUser;
	}

	public String getCostUser() {
		return costUser;
	}

	public void setCostUser(String costUser) {
		this.costUser = costUser;
	}

	
	
}
