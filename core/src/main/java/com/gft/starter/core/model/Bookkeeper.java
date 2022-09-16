package com.gft.starter.core.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


//Declaração de entidade e nome da tabela do banco de dados
@Entity
@Table(name="tb_bookkeeper")
public class Bookkeeper extends Person{

	@NotNull
	private boolean statusBookkeeper;
	
	@NotNull
	private String registerBookkeeper;

	public Bookkeeper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bookkeeper(@NotNull boolean statusBookkeeper, @NotNull String registerBookkeeper) {
		super();
		this.statusBookkeeper = statusBookkeeper;
		this.registerBookkeeper = registerBookkeeper;
	}

	public boolean isStatusBookkeeper() {
		return statusBookkeeper;
	}

	public void setStatusBookkeeper(boolean statusBookkeeper) {
		this.statusBookkeeper = statusBookkeeper;
	}

	public String getRegisterBookkeeper() {
		return registerBookkeeper;
	}

	public void setRegisterBookkeeper(String registerBookkeeper) {
		this.registerBookkeeper = registerBookkeeper;
	}
	
	
}
