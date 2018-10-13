package com.proaktiv.io.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bank")
public class Bank {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="bic_number")
	private String bicNumber;
	
	@Column(name="city")
	private String city;
	
	@Column(name="card_value")
	private String cardValue;
	
	public Bank() {
		super();
	}
	public Bank(String name, String bicNumber, String city, String cardValue) {
		super();
		this.name = name;
		this.bicNumber = bicNumber;
		this.city = city;
		this.cardValue = cardValue;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String bankName) {
		this.name = bankName;
	}
	public String getBicNumber() {
		return bicNumber;
	}
	public void setBicNumber(String bicNumber) {
		this.bicNumber = bicNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCardValue() {
		return cardValue;
	}
	public void setCardValue(String cardValue) {
		this.cardValue = cardValue;
	}
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Bank [id=").append(id)
				.append(", bankName=").append(name)
				.append(", bicNumber=").append(bicNumber)
				.append(", city=").append(city)
				.append("]");
		return builder.toString();
	}
}
