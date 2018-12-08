package com.slokam.FirstProgram.Pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "student")
public class StudentPojo {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotBlank   //This annotation is used for validation while saving the record.Not blank means not null(null) notempty("  ")and not blank("").
	//@Email
	//@Pattern(regexp="^[A-Z a-z][A-Z a-z 0-9]{5,20}@[A-Z a-z]{3,10}\\.[a-z]{2,8}$")
	private String name; 
	
	
	private Integer age;
	
	//@Length(max=10)
	//@Pattern(regexp="^[0-9]{8}$")
	//@Pattern(regexp="^[A-Z][0-9]{7}$")
	//@Pattern(regexp="^[A-Z]{2}-[0-9]{13}$")
	//@Pattern(regexp="^[A-Z]{5}[0-9]{4}[A-Z]$")
	private String qual;
	
	
	private Long phone;
	
	private Integer pin;
	
	public StudentPojo() {
		
	}

	public StudentPojo(String name, Integer age, String qual, Long phone) 
	{
		super();
		this.name = name;
		this.age = age;
		this.qual = qual;
		this.phone = phone;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getQual() {
		return qual;
	}
	public void setQual(String qual) {
		this.qual = qual;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public Integer getPin() {
		return pin;
	}
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	
	

}
