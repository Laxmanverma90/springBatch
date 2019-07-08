package com.hcl.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonBean  implements Serializable{

	private static final long serialVersionUID = -6402068923614583448L;
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	
	public PersonBean(String firstName, String lastName, String email, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
	}
	
}
