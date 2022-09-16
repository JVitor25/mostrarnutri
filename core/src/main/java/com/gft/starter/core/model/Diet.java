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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_diet")
public class Diet {

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuidDiet", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID uuidDiet;
	
	private Double kcalDiet;
	
	@NotNull
	private String foodsDiet;

	@OneToMany(mappedBy="diet", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("diet")
	private List<Group> group;
	
	@NotNull
	@ManyToOne
	@JsonIgnoreProperties("diet")
	private Nutritionist nutritionist;

	public UUID getUuidDiet() {
		return uuidDiet;
	}

	public void setUuidDiet(UUID uuidDiet) {
		this.uuidDiet = uuidDiet;
	}

	public Double getKcalDiet() {
		return kcalDiet;
	}

	public void setKcalDiet(Double kcalDiet) {
		this.kcalDiet = kcalDiet;
	}

	public String getFoodsDiet() {
		return foodsDiet;
	}

	public void setFoodsDiet(String foodsDiet) {
		this.foodsDiet = foodsDiet;
	}

	public List<Group> getGroup() {
		return group;
	}

	public void setGroup(List<Group> group) {
		this.group = group;
	}

	public Nutritionist getNutritionist() {
		return nutritionist;
	}

	public void setNutritionist(Nutritionist nutritionist) {
		this.nutritionist = nutritionist;
	}

}
